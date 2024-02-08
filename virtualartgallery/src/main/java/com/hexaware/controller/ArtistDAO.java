package com.hexaware.controller;

import com.hexaware.model.Artist;



public interface ArtistDAO {

    Artist getArtistById(int artistID);



    
    void close();
}
