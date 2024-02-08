package com.hexaware.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.ArtWorkNotFoundException;



public class UserFavoriteArtworkDAOImpl implements UserFavoriteArtworkDAO {
    private Connection connection;

    public UserFavoriteArtworkDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean addArtworkToFavorite(int userID, int artworkID) throws ArtWorkNotFoundException {
        String sql = "INSERT INTO User_Favorite_Artwork (UserID, ArtworkID) VALUES (?, ?)";
        
        if(checkArtwork(artworkID)) {
        	
        	try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, userID);
                statement.setInt(2, artworkID);
                return statement.executeUpdate() > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else throw new ArtWorkNotFoundException("Artwork Not Found");
        
       

        

        return false;
    }

    @Override
    public boolean removeArtworkFromFavorite(int userID, int artworkID) {
        String sql = "DELETE FROM User_Favorite_Artwork WHERE UserID=? AND ArtworkID=?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userID);
            statement.setInt(2, artworkID);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<String> getUserFavoriteArtworks(int userID) {
        List<String> artworkNames = new ArrayList<>();

        
        String sql = "SELECT A.Title FROM User_Favorite_Artwork UFA JOIN Artwork A ON UFA.ArtworkID = A.ArtworkID WHERE UFA.UserID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userID);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String artworkName = resultSet.getString("Title");
                    artworkNames.add(artworkName);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
          
        }

        return artworkNames;
    }

public boolean checkArtwork(int artoworkId) {
	String sql = "SELECT * FROM Artwork WHERE ArtworkID = ?";
	
	 try (PreparedStatement statement = connection.prepareStatement(sql)) {
         statement.setInt(1, artoworkId);
         
         try (ResultSet resultSet = statement.executeQuery()) {
        	 if(resultSet.next()) {
        		 return true ; 
        	 }
              
         }
     } catch (SQLException e) {
         e.printStackTrace();
     }
	return false;
}
   

    @Override
    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
