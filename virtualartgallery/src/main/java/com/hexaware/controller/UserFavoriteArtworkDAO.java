package com.hexaware.controller;

import java.util.List;

import exception.ArtWorkNotFoundException;



public interface UserFavoriteArtworkDAO {
    boolean addArtworkToFavorite(int userID, int artworkID) throws ArtWorkNotFoundException;

    boolean removeArtworkFromFavorite(int userID, int artworkID);
    
    List<String> getUserFavoriteArtworks(int userID);
    
    void close();

	
}
