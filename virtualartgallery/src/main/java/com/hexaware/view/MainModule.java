package com.hexaware.view;



import exception.ArtWorkNotFoundException;
import exception.UserNotFoundException;
import util.DBConnection;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.hexaware.controller.ArtistDAOImpl;
import com.hexaware.controller.ArtworkDAO;
import com.hexaware.controller.ArtworkDAOImpl;
import com.hexaware.controller.UserDAO;
import com.hexaware.controller.UserDAOImpl;
import com.hexaware.controller.UserFavoriteArtworkDAO;
import com.hexaware.controller.UserFavoriteArtworkDAOImpl;
import com.hexaware.model.Artist;
import com.hexaware.model.Artwork;


public class MainModule {
    private static Connection connection;

    public static void main(String[] args) {
        try {
            connection = DBConnection.getConnection();

            ArtworkDAO artworkDAO = new ArtworkDAOImpl(connection);
            UserFavoriteArtworkDAO userFavoriteArtworkDAO = new UserFavoriteArtworkDAOImpl(connection);
         

            Scanner scanner = new Scanner(System.in);

            int choice;
            do {
                System.out.println("1. Add Artwork");
                System.out.println("2. Update Artwork");
                System.out.println("3. Remove Artwork");
                System.out.println("4. Search Artworks");
                System.out.println("5. Add Artwork to Favorites");
                System.out.println("6. Remove Artwork from Favorites");
                System.out.println("7. Get User's Favorite Artworks");
                System.out.println("0. Exit");

                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        // Add Artwork
                        Artwork newArtwork = createArtworkFromUserInput(scanner);
                        artworkDAO.addArtwork(newArtwork);
                        break;
                    case 2:
                        // Update Artwork
                        int artworkIdToUpdate = promptForArtworkId(scanner);
                        scanner.nextLine();
                        Artwork updatedArtwork = createArtworkFromUserInput(scanner);
                        updatedArtwork.setArtworkID(artworkIdToUpdate);
                        artworkDAO.updateArtwork(updatedArtwork);
                        break;
                    case 3:
                        // Remove Artwork
                        int artworkIdToRemove = promptForArtworkId(scanner);
                        artworkDAO.removeArtwork(artworkIdToRemove);
                        break;
                    case 4:
                        // Search Artworks
                    	 System.out.print("Enter keyword for search: ");
                    	    String keyword = scanner.next();
                    	    List<Artwork> artworks = artworkDAO.searchArtworks(keyword);

                    	    if (artworks.isEmpty()) {
                    	        System.out.println("No artworks found with the given keyword.");
                    	    } else {
                    	        System.out.println("Search Results:");
                    	        for (Artwork artwork : artworks) {
                    	            System.out.println("Artwork ID : " + artwork.getArtworkID() + "  -> " + artwork.getTitle() + " by " + artwork.getArtist().getName());
                    	        }
                    	    }
                        break;
                    case 5:
                        
                        int userIdToAddFavorite = promptForUserId(scanner);
                        int artworkIdToAddFavorite = promptForArtworkId(scanner);
					try {
						userFavoriteArtworkDAO.addArtworkToFavorite(userIdToAddFavorite, artworkIdToAddFavorite);
					} catch (ArtWorkNotFoundException e) {
						System.out.println(e);
						choice=0;
						
						
					}
                        break;
                    case 6:
                        // Remove Artwork from Favorites
                        int userIdToRemoveFavorite = promptForUserId(scanner);
                        int artworkIdToRemoveFavorite = promptForArtworkId(scanner);
                        userFavoriteArtworkDAO.removeArtworkFromFavorite(userIdToRemoveFavorite, artworkIdToRemoveFavorite);
                        break;
                    case 7:
                        int userIdToGetFavorites = promptForUserId(scanner);
                        List<String> favoriteArtworks = userFavoriteArtworkDAO.getUserFavoriteArtworks(userIdToGetFavorites);

                        if (favoriteArtworks.isEmpty()) {
                            System.out.println("No favorite artworks found for the user.");
                        } else {
                            System.out.println("User's Favorite Artworks:");
                            for (String artwork : favoriteArtworks) {
                                System.out.println(artwork);
                            }
                        }break;
                    case 0:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            } while (choice != 0);

        } finally {
            DBConnection.closeConnection();
        }
    }

    // Methods for creating entities from user input
    private static Artwork createArtworkFromUserInput(Scanner scanner) {
        System.out.print("Enter Artwork Title: ");
        String title = scanner.nextLine();

        System.out.print("Enter Artwork Description: ");
        String description = scanner.nextLine();

        System.out.print("Enter Artwork Creation Date (yyyy-MM-dd): ");
        String dateString = scanner.nextLine();
        Date creationDate = parseDate(dateString);

        System.out.print("Enter Artwork Medium: ");
        String medium = scanner.nextLine();

        System.out.print("Enter Artwork Image URL: ");
        String imageURL = scanner.nextLine();

        System.out.print("Enter Artwork Artist ID: ");
        int artistId = scanner.nextInt();

        // Fetch the associated artist from the database (To check wether the artist exists or not)
        Artist artist = new ArtistDAOImpl(connection).getArtistById(artistId);

        // Ensure that the artist is not null before creating the artwork
        if (artist != null) {
            return new Artwork(title, description, creationDate, medium, imageURL, artist);
        } else {
            System.out.println("Error: Artist with ID " + artistId + " not found.");
            return null; 
        }
    }


    private static int promptForUserId(Scanner scanner) {
        int userId;
        while (true) {
            try {
                System.out.print("Enter User ID: ");
                userId = scanner.nextInt();

                
                UserDAO userDAO = new UserDAOImpl(connection);
                if (userDAO.getUserById(userId) != null) {
                    break; 
                } else {
                    throw new UserNotFoundException("User with ID " + userId + " not found.");
                }
            } catch (java.util.InputMismatchException e) {
                
                System.out.println("Invalid input. Please enter a valid integer for User ID.");
                scanner.next(); 
            } catch (UserNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
        return userId;
    }

    private static int promptForArtworkId(Scanner scanner) {
        System.out.print("Enter Artwork ID: ");
        return scanner.nextInt();
    }

    
    private static Date parseDate(String dateString) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter date in the format yyyy-MM-dd.");
            return null;
        }
    }
}
