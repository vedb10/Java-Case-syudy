package com.hexaware.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.Date;

import org.junit.After;
import org.junit.Before;

import org.junit.Test;

import com.hexaware.controller.ArtistDAO;
import com.hexaware.controller.ArtistDAOImpl;
import com.hexaware.controller.ArtworkDAO;
import com.hexaware.controller.ArtworkDAOImpl;
import com.hexaware.controller.UserFavoriteArtworkDAO;
import com.hexaware.controller.UserFavoriteArtworkDAOImpl;
import com.hexaware.model.Artist;
import com.hexaware.model.Artwork;

import exception.ArtWorkNotFoundException;
import util.DBConnection;

public class VirtualArtGalleryTest {
	private static ArtistDAO artistDAO;
	private static Connection connection;
    private static ArtworkDAO artworkDAO;
    private static UserFavoriteArtworkDAO ufa;
	
	@Before
	public void setupClass() {
		connection = DBConnection.getConnection();
		artworkDAO = new ArtworkDAOImpl(connection);
		artistDAO = new ArtistDAOImpl(connection);
		ufa = new UserFavoriteArtworkDAOImpl(connection);
		
	}
	
	
//	@Test
//	public void addArtwork() {
//		
//		Artist artist = artistDAO.getArtistById(10);
//	    assertNotNull(artist);
//		Artwork artwork = new Artwork("Test Artwork", "Test Description", new Date(2022-02-02), "Test Medium", "Test URL",artist);
//		assertTrue(artworkDAO.addArtwork(artwork));	}
	
//	@Test
//	public void updateArtwork() {
//		Artist artist = artistDAO.getArtistById(10);
//	    assertNotNull(artist);
//		Artwork artwork = new Artwork("Test Artwork two", "Test Description two", new Date(2022-02-02), "Test Medium two", "Test URL",artist);
//		artwork.setArtworkID(26);
//		assertTrue(artworkDAO.updateArtwork(artwork));
//	}
	
//	@Test
//	public void removeArtwork() {
//		assertTrue(artworkDAO.removeArtwork(26));
//	}
//	
//	@Test
//	public void searchArtwork() {
//		String keyword = "Mona";
//		assertNotNull(artworkDAO.searchArtworks(keyword));
//	}
//	
//	@Test
//	public void addArtworkToFavorite() throws ArtWorkNotFoundException{
//		assertNotNull(ufa.addArtworkToFavorite(10,10));
//	}
//	
//	@Test
//	public void removeArtworkFromFavorite() throws ArtWorkNotFoundException{
//		assertNotNull(ufa.removeArtworkFromFavorite(10,10));
//	}
//	
	
	
	@After
	public void tearDown() {
		System.out.println("Tear Down called after the test case");
		artworkDAO=null;
	}
	}

