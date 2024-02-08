package com.hexaware.controller;

import com.hexaware.model.Artist;

import java.sql.*;


public class ArtistDAOImpl implements ArtistDAO {
    private Connection connection;

    public ArtistDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Artist getArtistById(int artistID) {
        String sql = "SELECT * FROM Artist WHERE ArtistID=?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, artistID);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToArtist(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }



    private Artist mapResultSetToArtist(ResultSet resultSet) throws SQLException {
        Artist artist = new Artist();
        artist.setArtistID(resultSet.getInt("ArtistID"));
        artist.setName(resultSet.getString("Name"));
        artist.setBiography(resultSet.getString("Biography"));
        artist.setBirthDate(resultSet.getDate("BirthDate"));
        artist.setNationality(resultSet.getString("Nationality"));
        artist.setWebsite(resultSet.getString("Website"));
        artist.setContactInformation(resultSet.getString("ContactInformation"));

        return artist;
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
