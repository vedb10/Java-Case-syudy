create database virtualartgallery;
use virtualartgallery;

-- Artwork Table
CREATE TABLE Artwork (
    ArtworkID INT PRIMARY KEY AUTO_INCREMENT,
    Title VARCHAR(255) NOT NULL,
    Description TEXT,
    CreationDate DATE,
    Medium VARCHAR(100),
    ImageURL VARCHAR(255),
    ArtistID INT,
    FOREIGN KEY (ArtistID) REFERENCES Artist(ArtistID)
);

-- Artist Table
CREATE TABLE Artist (
    ArtistID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(100) NOT NULL,
    Biography TEXT,
    BirthDate DATE,
    Nationality VARCHAR(50),
    Website VARCHAR(255),
    ContactInformation VARCHAR(255)
);

-- User Table
CREATE TABLE User (
    UserID INT PRIMARY KEY AUTO_INCREMENT,
    Username VARCHAR(50) NOT NULL,
    Password VARCHAR(255) NOT NULL,
    Email VARCHAR(100) NOT NULL,
    FirstName VARCHAR(50),
    LastName VARCHAR(50),
    DateOfBirth DATE,
    ProfilePicture VARCHAR(255)
    
);

-- Gallery Table
CREATE TABLE Gallery (
    GalleryID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(100) NOT NULL,
    Description TEXT,
    Location VARCHAR(255),
    Curator INT,
    OpeningHours VARCHAR(100),
    FOREIGN KEY (Curator) REFERENCES Artist(ArtistID)
);


CREATE TABLE User_Favorite_Artwork (
    UserID INT,
    ArtworkID INT,
    PRIMARY KEY (UserID, ArtworkID),
    FOREIGN KEY (UserID) REFERENCES User(UserID),
    FOREIGN KEY (ArtworkID) REFERENCES Artwork(ArtworkID)
);


CREATE TABLE Artwork_Gallery (
    ArtworkID INT,
    GalleryID INT,
    PRIMARY KEY (ArtworkID, GalleryID),
    FOREIGN KEY (ArtworkID) REFERENCES Artwork(ArtworkID),
    FOREIGN KEY (GalleryID) REFERENCES Gallery(GalleryID)
);
use virtualartgallery

select * from artist;
select* from gallery;
select * from artwork;
select * from user;
select * from user_favorite_artwork;

use sample2

INSERT INTO Artwork (Title, Description, CreationDate, Medium, ImageURL, ArtistID) VALUES
  ('Starry Night', 'A view of the night sky', '1889-06-18', 'Oil on Canvas', 'starry_night.jpg', 1),
  ('Guernica', 'Response to the bombing of Guernica', '1937-05-11', 'Oil on Canvas', 'guernica.jpg', 2),
  ('Mona Lisa', 'Portrait of Lisa Gherardini', '1503-08-21', 'Oil on Poplar', 'mona_lisa.jpg', 3),
  ('The Two Fridas', 'Depicting two versions of Frida Kahlo', '1939-09-20', 'Oil on Canvas', 'two_fridas.jpg', 4),
  ('Water Lilies', 'Series of impressionist paintings', '1914-06-01', 'Oil on Canvas', 'water_lilies.jpg', 5),
  ('The Persistence of Memory', 'Melting clocks', '1931-01-01', 'Oil on Canvas', 'persistence_of_memory.jpg', 6),
  ('Red Canna', 'Abstract flower painting', '1924-01-01', 'Oil on Canvas', 'red_canna.jpg', 7),
  ('No. 5, 1948', 'Drip painting', '1948-01-01', 'Oil, enamel, and aluminum paint', 'no_5_1948.jpg', 8),
  ('The Scream', 'Expressionist painting', '1893-01-22', 'Oil, Tempera, Pastel on Cardboard', 'the_scream.jpg', 9),
  ('Girl with a Balloon', 'Symbolic street art', '2002-01-01', 'Spray paint on canvas', 'girl_with_a_balloon.jpg', 10);








INSERT INTO Artist (Name, Biography, BirthDate, Nationality, Website, ContactInformation)
VALUES 
('John Doe', 'Abstract artist based in New York.', '1990-05-15', 'American', 'http://www.johndoeart.com', 'john.doe@email.com'),
('Alice Smith', 'Realist painter specializing in landscapes.', '1985-08-22', 'Canadian', 'http://www.alicesmithart.com', 'alice.smith@email.com'),
('Bob Johnson', 'Sculptor creating modern art pieces.', '1978-03-10', 'British', 'http://www.bobjohnsonart.com', 'bob.johnson@email.com'),
('Eva Martinez', 'Photographer capturing urban life.', '1992-11-28', 'Spanish', 'http://www.evamartinezphoto.com', 'eva.martinez@email.com'),
('Michael Chang', 'Portrait artist with a focus on emotions.', '1982-07-03', 'Chinese', 'http://www.michaelchangart.com', 'michael.chang@email.com'),
('Sophie Turner', 'Mixed media artist exploring cultural themes.', '1995-01-18', 'French', 'http://www.sophieturnerart.com', 'sophie.turner@email.com'),
('David Lee', 'Ceramicist blending tradition with modern design.', '1970-09-12', 'Korean', 'http://www.davidleeart.com', 'david.lee@email.com'),
('Emma White', 'Illustrator known for whimsical and colorful designs.', '1987-04-05', 'Australian', 'http://www.emmawhiteart.com', 'emma.white@email.com'),
('Chris Harris', 'Street artist expressing social commentary.', '1998-06-30', 'American', 'http://www.chrisharrisart.com', 'chris.harris@email.com'),
('Linda Rodriguez', 'Abstract expressionist painter.', '1980-12-14', 'Mexican', 'http://www.lindarodriguezart.com', 'linda.rodriguez@email.com');

INSERT INTO User (Username, Password, Email, FirstName, LastName, DateOfBirth, ProfilePicture)
VALUES 
('spongebob_squarepants', 'hashed_password_1', 'spongebob@email.com', 'SpongeBob', 'SquarePants', '1986-05-01', 'spongebob.jpg'),
('pikachu_electric', 'hashed_password_2', 'pikachu@email.com', 'Pikachu', 'Electric', '1995-08-15', 'pikachu.jpg'),
('ironman_avenger', 'hashed_password_3', 'ironman@email.com', 'Tony', 'Stark', '1970-05-29', 'ironman.jpg'),
('homer_simpson', 'hashed_password_4', 'homer@email.com', 'Homer', 'Simpson', '1956-04-19', 'homer.jpg'),
('captain_america', 'hashed_password_5', 'captain@email.com', 'Steve', 'Rogers', '1918-07-04', 'captain_america.jpg'),
('scooby_doo', 'hashed_password_6', 'scooby@email.com', 'Scooby', 'Doo', '1969-09-13', 'scooby_doo.jpg'),
('thor_avenger', 'hashed_password_7', 'thor@email.com', 'Thor', 'Odinson', '965-07-07', 'thor.jpg'),
('bugs_bunny', 'hashed_password_8', 'bugs@email.com', 'Bugs', 'Bunny', '1940-04-30', 'bugs_bunny.jpg'),
('pink_ranger', 'hashed_password_9', 'pink_ranger@email.com', 'Kimberly', 'Hart', '1970-11-12', 'pink_ranger.jpg'),
('spiderman_avenger', 'hashed_password_10', 'spiderman@email.com', 'Peter', 'Parker', '1998-08-10', 'spiderman.jpg');