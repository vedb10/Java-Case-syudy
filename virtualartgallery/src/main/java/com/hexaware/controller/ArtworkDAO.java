package com.hexaware.controller;

import com.hexaware.model.Artwork;

import java.util.List;

public interface ArtworkDAO {
    
    boolean addArtwork(Artwork artwork);

    boolean updateArtwork(Artwork artwork);

    boolean removeArtwork(int artworkID);

    List<Artwork> searchArtworks(String keyword);

 
    void close();
}
