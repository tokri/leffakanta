INSERT INTO users VALUES 
  (DEFAULT, 'mikkelinmies', '1000:242a8f56d17db30741f0e08e3830b403c6f16bd1173fc3aa:77f9d70bcd8ced5d3954915a728a82c57dc97abf325975da', true),
  (DEFAULT, 'ohdake', '1000:242a8f56d17db30741f0e08e3830b403c6f16bd1173fc3aa:77f9d70bcd8ced5d3954915a728a82c57dc97abf325975da', false);

INSERT INTO movies VALUES 
  (DEFAULT, 'The Matrix', 1999, 136, 7.4, 'Thomas A. Anderson is a man living two lives. By day he is an average computer programmer and by night a malevolent hacker known as Neo, who finds himself targeted by the police when he is contacted by Morpheus, a legendary computer hacker, who reveals the shocking truth about our reality',
	'image.tmdb.org/t/p/w185/gynBNzwyaHKtXqlEKKLioNkjKgN.jpg', 'image.tmdb.org/t/p/w780/gM3KKiN80qbJgKHjPnmAfwxSicG.jpg', 'www.youtube.com/watch%3Fv%3Dm8e-FF8MsqU%26hd%3D1'),
  (DEFAULT, 'Beauty and the Beast', 1991, 84, 6.7, 'Follow the adventures of Belle, a bright young woman who finds herself in the castle of a prince who''s been turned into a mysterious beast. With the help of the castle''s enchanted staff, Belle soon learns the most important lesson of all -- that true beauty comes from within.',
	'image.tmdb.org/t/p/w185/b9QJr2oblOu1grgOMUZF1xkUJdh.jpg ', 'image.tmdb.org/t/p/w780/q8OEC91NiJOpghWI9hXtC27nFX0.jpg', 'www.youtube.com/watch%3Fv%3DtRlzmyveDHE');

INSERT INTO genres VALUES 
  (DEFAULT, 'Action'),
  (DEFAULT, 'Animation'),
  (DEFAULT, 'Sci-fi');

INSERT INTO movie_genres VALUES 
  (1, 1),
  (1, 3),
  (2, 2);
	
INSERT INTO people VALUES 
  (DEFAULT, 'Paige O''Hara', NULL, 'image.tmdb.org/t/p/w185/udSmgh3YGPPWpyW31MXj7bG6fuj.jpg'),
  (DEFAULT, 'Gary Trousdale', NULL, 'image.tmdb.org/t/p/w185/naRhdLVuw6a8KtDPM2aJWPhPjsp.jpg'),
  (DEFAULT, 'Keanu Reeves', NULL, 'image.tmdb.org/t/p/w185/cEIZlUQvnlXfoxF0mPJsvDPh2Po.jpg'),
  (DEFAULT, 'Laurence Fishburne', NULL, 'image.tmdb.org/t/p/w185/mh0lZ1XsT84FayMNiT6Erh91mVu.jpg'),
  (DEFAULT, 'Carrie-Anne Moss', NULL, 'image.tmdb.org/t/p/w185/8iATAc5z5XOKFFARLsvaawa8MTY.jpg'),
  (DEFAULT, 'Hugo Weaving', NULL, 'image.tmdb.org/t/p/w185/ysED1kp94bpnweNVaDoVQQ6iy8X.jpg');								
  
INSERT INTO characters VALUES 
  (DEFAULT, 'Belle'),
  (DEFAULT, 'Neo'),
  (DEFAULT, 'Morpheus'),
  (DEFAULT, 'Trinity'),
  (DEFAULT, 'Agent Smith');

INSERT INTO "cast" VALUES 
  (2, 1, 1, 1),
  (1, 1, 3, 2),
  (1, 2, 4, 3),
  (1, 3, 5, 4),
  (1, 4, 6, 5);
  

INSERT INTO crew VALUES 
  (2, 1, 2, 'Director');

INSERT INTO collections VALUES 
  (DEFAULT, 2, 1, 'DVD', 'Available'),
  (DEFAULT, 2, 2, 'Blu-ray', 'Available');
  