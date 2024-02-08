package com.hexaware.controller;

import com.hexaware.model.Artist;
import com.hexaware.model.Artwork;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtworkDAOImpl implements ArtworkDAO {
    private Connection connection;

    public ArtworkDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean addArtwork(Artwork artwork) {
        String sql = "INSERT INTO Artwork (Title, Description, CreationDate, Medium, ImageURL, ArtistID) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, artwork.getTitle());
            statement.setString(2, artwork.getDescription());
            statement.setDate(3, new java.sql.Date(artwork.getCreationDate().getTime()));
            statement.setString(4, artwork.getMedium());
            statement.setString(5, artwork.getImageURL());
            statement.setInt(6, artwork.getArtist().getArtistID());

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                    return true;
                }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean updateArtwork(Artwork artwork) {
        String sql = "UPDATE Artwork SET Title=?, Description=?, CreationDate=?, Medium=?, ImageURL=?, ArtistID=? WHERE ArtworkID=?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, artwork.getTitle());
            statement.setString(2, artwork.getDescription());
            statement.setDate(3, new java.sql.Date(artwork.getCreationDate().getTime()));
            statement.setString(4, artwork.getMedium());
            statement.setString(5, artwork.getImageURL());
            statement.setInt(6, artwork.getArtist().getArtistID());
            statement.setInt(7, artwork.getArtworkID());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override 
    public boolean removeArtwork(int artworkID) {
        String sql = "DELETE FROM Artwork WHERE ArtworkID=?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, artworkID);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }



    @Override
    public List<Artwork> searchArtworks(String keyword) {
        String sql = "SELECT * FROM Artwork WHERE Title LIKE ? OR Description LIKE ?";

        List<Artwork> artworks = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + keyword + "%");
            statement.setString(2, "%" + keyword + "%");

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                artworks.add(mapResultSetToArtwork(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            
        }

        return artworks;
    }

    private Artwork mapResultSetToArtwork(ResultSet resultSet) throws SQLException {
        Artwork artwork = new Artwork();
        artwork.setArtworkID(resultSet.getInt("ArtworkID"));
        artwork.setTitle(resultSet.getString("Title"));
        artwork.setDescription(resultSet.getString("Description"));
        artwork.setCreationDate(resultSet.getDate("CreationDate"));
        artwork.setMedium(resultSet.getString("Medium"));
        artwork.setImageURL(resultSet.getString("ImageURL"));

        // Fetching associated artist
        int artistID = resultSet.getInt("ArtistID");
        Artist artist = new ArtistDAOImpl(connection).getArtistById(artistID);
        artwork.setArtist(artist);

        return artwork;
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
