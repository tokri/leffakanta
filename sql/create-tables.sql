CREATE TYPE POSITION_GROUP AS ENUM ('Director', 'Writer', 'Producer');
CREATE TYPE FORMAT_TYPE AS ENUM ('Blu-ray', 'DVD', 'TV', 'Download');
CREATE TYPE AVAILIBILITY AS ENUM ('Available', 'Loaned', 'Missing');

CREATE TABLE users (
	user_id			SERIAL PRIMARY KEY,
	username		VARCHAR(48) UNIQUE NOT NULL,
	password_hash	VARCHAR(128) NOT NULL,
	is_admin		BOOL NOT NULL
);

CREATE TABLE movies (
	movie_id		SERIAL PRIMARY KEY,
	movie_title		VARCHAR(128) NOT NULL,
	"year"			DECIMAL(4,0),
	runtime			INTEGER,
	rating			DECIMAL(3,1),
	plot_text		VARCHAR(4096),
	poster_url		VARCHAR(128),
	background_url	VARCHAR(128),
	trailer_url		VARCHAR(128)
);

CREATE TABLE genres (
	genre_id		SERIAL PRIMARY KEY,
	genre_name		VARCHAR(128) NOT NULL
);

CREATE TABLE movie_genres (
	movie_id		INTEGER REFERENCES movies (movie_id) ON DELETE CASCADE ON UPDATE CASCADE,
	genre_id		INTEGER REFERENCES genres (genre_id) ON DELETE CASCADE ON UPDATE CASCADE,
	PRIMARY KEY(movie_id, genre_id)
);

CREATE TABLE people (
	person_id		SERIAL PRIMARY KEY,
	person_name		VARCHAR(48) NOT NULL,
	date_of_birth	DATE,
	image_url		VARCHAR(256)
);

CREATE TABLE characters (
	character_id	SERIAL PRIMARY KEY,
	character_name	VARCHAR(48) NOT NULL
);

CREATE TABLE "cast" (
	movie_id		INTEGER REFERENCES movies (movie_id) ON DELETE CASCADE ON UPDATE CASCADE,
	list_order		INTEGER,
	person_id		INTEGER REFERENCES people (person_id) ON DELETE CASCADE ON UPDATE CASCADE,
	character_id	INTEGER REFERENCES characters (character_id) ON DELETE CASCADE ON UPDATE CASCADE,
	PRIMARY KEY(movie_id, person_id, character_id)
);

CREATE TABLE crew (
	movie_id		INTEGER REFERENCES movies (movie_id) ON DELETE CASCADE ON UPDATE CASCADE,
	list_order		INTEGER,
	person_id		INTEGER REFERENCES people (person_id) ON DELETE CASCADE ON UPDATE CASCADE,
	position		POSITION_GROUP NOT NULL,
	PRIMARY KEY(movie_id, person_id, position)
);

CREATE TABLE collections (
	item_id			SERIAL PRIMARY KEY,
	owner_id		INTEGER REFERENCES users (user_id) ON DELETE CASCADE ON UPDATE CASCADE,
	movie_id		INTEGER REFERENCES movies (movie_id) ON DELETE CASCADE ON UPDATE CASCADE,
	format_type		FORMAT_TYPE NOT NULL,
	availability	AVAILIBILITY NOT NULL
);
