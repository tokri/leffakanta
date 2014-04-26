INSERT INTO users VALUES 
  (DEFAULT, 'mikkelinmies', '1000:242a8f56d17db30741f0e08e3830b403c6f16bd1173fc3aa:77f9d70bcd8ced5d3954915a728a82c57dc97abf325975da', true),
  (DEFAULT, 'ohdake', '1000:242a8f56d17db30741f0e08e3830b403c6f16bd1173fc3aa:77f9d70bcd8ced5d3954915a728a82c57dc97abf325975da', false);

INSERT INTO movies VALUES 
  (DEFAULT, 'The Matrix', 1999, 136, 7.4, 'Thomas A. Anderson is a man living two lives. By day he is an average computer programmer and by night a malevolent hacker known as Neo, who finds himself targeted by the police when he is contacted by Morpheus, a legendary computer hacker, who reveals the shocking truth about our reality',
	'http://image.tmdb.org/t/p/w185/gynBNzwyaHKtXqlEKKLioNkjKgN.jpg', 'http://image.tmdb.org/t/p/w780/gM3KKiN80qbJgKHjPnmAfwxSicG.jpg', 'http://www.youtube.com/watch?v=m8e-FF8MsqU&hd=1'),
  (DEFAULT, 'Beauty and the Beast', 1991, 84, 6.7, 'Follow the adventures of Belle, a bright young woman who finds herself in the castle of a prince who''s been turned into a mysterious beast. With the help of the castle''s enchanted staff, Belle soon learns the most important lesson of all -- that true beauty comes from within.',
	'http://image.tmdb.org/t/p/w185/b9QJr2oblOu1grgOMUZF1xkUJdh.jpg', 'http://image.tmdb.org/t/p/w780/q8OEC91NiJOpghWI9hXtC27nFX0.jpg', 'http://www.youtube.com/watch?v=tRlzmyveDHE'),
  (DEFAULT, 'The Day the Earth Stood Still', 2008, 103, 5.3, 'A representative of an alien race that went through drastic evolution to survive its own climate change, Klaatu comes to Earth to assess whether humanity can prevent the environmental damage they have inflicted on their own planet. When barred from speaking to the United Nations, he decides humankind shall be exterminated so the planet can survive.',
	'http://image.tmdb.org/t/p/w185/7fNFk4EAGbuRFqrSJprgXEIX5Za.jpg', 'http://image.tmdb.org/t/p/w780/bfRgom0djZB8z7qbtjM542j3xrT.jpg', 'http://www.youtube.com/watch?v=lgeuvDiS1_M&hd=1'),
  (DEFAULT, 'Django Unchained', 2012, NULL, 7.2, 'A representative of an alien race that went through drastic evolution to survive its own climate change, Klaatu comes to Earth to assess whether humanity can prevent the environmental damage they have inflicted on their own planet. When barred from speaking to the United Nations, he decides humankind shall be exterminated so the planet can survive.',
	'http://image.tmdb.org/t/p/w185/5WJnxuw41sddupf8cwOxYftuvJG.jpg', 'http://image.tmdb.org/t/p/w780/qUcmEqnzIwlwZxSyTf3WliSfAjJ.jpg', NULL);

INSERT INTO genres VALUES 
  (DEFAULT, 'Action'),
  (DEFAULT, 'Animation'),
  (DEFAULT, 'Sci-fi'),
  (DEFAULT, 'Drama'),
  (DEFAULT, 'Western');

INSERT INTO movie_genres VALUES 
  (1, 1),
  (1, 3),
  (2, 2),
  (4, 4),
  (4, 5);
	
INSERT INTO people VALUES 
  (DEFAULT, 'Keanu Reeves', NULL, 'http://image.tmdb.org/t/p/w185/cEIZlUQvnlXfoxF0mPJsvDPh2Po.jpg'),
  (DEFAULT, 'Laurence Fishburne', NULL, 'http://image.tmdb.org/t/p/w185/mh0lZ1XsT84FayMNiT6Erh91mVu.jpg'),
  (DEFAULT, 'Carrie-Anne Moss', NULL, 'http://image.tmdb.org/t/p/w185/8iATAc5z5XOKFFARLsvaawa8MTY.jpg'),
  (DEFAULT, 'Hugo Weaving', NULL, 'http://image.tmdb.org/t/p/w185/ysED1kp94bpnweNVaDoVQQ6iy8X.jpg'),
  (DEFAULT, 'Gary Trousdale', NULL, 'http://image.tmdb.org/t/p/w185/naRhdLVuw6a8KtDPM2aJWPhPjsp.jpg'),
  (DEFAULT, 'Kirk Wise', NULL, 'http://image.tmdb.org/t/p/w185/aoh7wnHk3F1HdOUBLI5Yfwd8rnN.jpg'),
  (DEFAULT, 'Linda Woolverton', NULL, NULL),
  (DEFAULT, 'Roger Allers', NULL, 'http://image.tmdb.org/t/p/w185/eUMjdBRyv5gF1m5sGHm15TUVuFP.jpg'),
  (DEFAULT, 'Kelly Asbury', NULL, 'http://image.tmdb.org/t/p/w185/jJAHjXFbjEUJrYjxyuj0AWMPAqW.jpg'),
  (DEFAULT, 'Paige O''Hara', NULL, 'http://image.tmdb.org/t/p/w185/udSmgh3YGPPWpyW31MXj7bG6fuj.jpg'),
  (DEFAULT, 'Robby Benson', NULL, 'http://image.tmdb.org/t/p/w185/wUqGufwQwXn3Y6rFdtZ80HqLDPm.jpg'),
  (DEFAULT, 'Richard White', NULL, 'http://image.tmdb.org/t/p/w185/iiX9ytEkszJGOwcZ0qFVClfjzeJ.jpg'),
  (DEFAULT, 'Jerry Orbach', NULL, 'http://image.tmdb.org/t/p/w185/fL9xNdyyPpJrqzSVyJpefRVWcx1.jpg'),
  (DEFAULT, 'Jennifer Connelly', NULL, 'http://image.tmdb.org/t/p/w185/6ZwYn5C08Nd2p9RCtUWfOB3dMiS.jpg'),
  (DEFAULT, 'Mousa Kraish', NULL, NULL),
  (DEFAULT, 'Shaine Jones', NULL, NULL),
  (DEFAULT, 'Quentin Tarantino', NULL, 'http://image.tmdb.org/t/p/w185/6grjDWpEIPL5QdRbUZFxVEp5TCd.jpg'),
  (DEFAULT, 'Jamie Foxx', NULL, 'http://image.tmdb.org/t/p/w185/1yr8Pv1tSWnQkCwDLrzUIzZVurM.jpg'),
  (DEFAULT, 'Christoph Walz', NULL, 'http://image.tmdb.org/t/p/w185/bPtNS4p3CEDt3Uo9khMCLyQUa0W.jpg'),
  (DEFAULT, 'Leonardo DiCaprio', NULL, 'http://image.tmdb.org/t/p/w185/mNRMgj7K5ztvkSqrcdwEYNZIS1M.jpg');

INSERT INTO characters VALUES 
  (DEFAULT, 'Neo'),
  (DEFAULT, 'Morpheus'),
  (DEFAULT, 'Trinity'),
  (DEFAULT, 'Agent Smith'),
  (DEFAULT, 'Belle (voice)'),
  (DEFAULT, 'Beast (voice)'),
  (DEFAULT, 'Gaston (voice)'),
  (DEFAULT, 'Lumiere (voice)'),
  (DEFAULT, 'Klaatu'),
  (DEFAULT, 'Helen Benson'),
  (DEFAULT, 'Yusef'),
  (DEFAULT, 'Solder #3'),
  (DEFAULT, 'Django'),
  (DEFAULT, 'Dr. King Schultz'),
  (DEFAULT, 'Calvin Candie'),
  (DEFAULT, 'The LeQuint Dickey Mining Co. Employee');
  
INSERT INTO roles VALUES 
  (DEFAULT, 'Actor', 1, 1, 1),
  (DEFAULT, 'Actor', 1, 2, 2),
  (DEFAULT, 'Actor', 1, 3, 3),
  (DEFAULT, 'Actor', 1, 4, 4),
  (DEFAULT, 'Director', 2, 5, null),
  (DEFAULT, 'Director', 2, 6, null),
  (DEFAULT, 'Writer', 2, 7, null),
  (DEFAULT, 'Writer', 2, 8, null),
  (DEFAULT, 'Writer', 2, 9, null),
  (DEFAULT, 'Actor', 2, 10, 5),
  (DEFAULT, 'Actor', 2, 11, 6),
  (DEFAULT, 'Actor', 2, 12, 7),
  (DEFAULT, 'Actor', 2, 13, 8),
  (DEFAULT, 'Actor', 3, 1, 9),
  (DEFAULT, 'Actor', 3, 14, 10),
  (DEFAULT, 'Actor', 3, 15, 11),
  (DEFAULT, 'Actor', 3, 16, 12),
  (DEFAULT, 'Director', 4, 17, null),
  (DEFAULT, 'Writer', 4, 17, null),
  (DEFAULT, 'Actor', 4, 18, 13),
  (DEFAULT, 'Actor', 4, 19, 14),
  (DEFAULT, 'Actor', 4, 20, 15),
  (DEFAULT, 'Actor', 4, 17, 16);
  
INSERT INTO collections VALUES 
  (DEFAULT, 1, 1, 'Blu-ray'),
  (DEFAULT, 2, 1, 'DVD'),
  (DEFAULT, 2, 2, 'Blu-ray'),
  (DEFAULT, 2, 3, 'Blu-ray'),
  (DEFAULT, 1, 4, 'Blu-ray'),
  (DEFAULT, 2, 4, 'Blu-ray');
  