package leffakanta.model;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

@Service
public class Movie {
    private int id;
    
    @Size(min=2, max=30) 
    @NotNull
    private String movieTitle;
    
    @DateTimeFormat(pattern="yyyy")
    @NotNull
    private int year;
    
    @Min(1) @Max(999)
    private int runtime;
    
    @Min(0) @Max(10)
    private float rating;
    
    @Size(min=10, max=300)
    private String plotText;
    private String posterUrl;
    private String backgroundUrl;
    private String trailerUrl;
    private String formatType;
    private String availability;
    private List<Genre> genres;
    private List<CrewMember> directors;
    private List<CrewMember> writers;
    private List<CastMember> cast;

    // basic escape handling (replace with better one later)
    private String filter(String text){
        if (text == null)
            return null;
        return "'"+text.replace("'","''").replace("\"", "\\\"")+"'";
    }
    
    // add movie into users collection
    public void addMovie(Movie movie, int owner_id){
        String sql = "INSERT INTO movies VALUES (DEFAULT, "+ filter(movie.getMovie_title())+", "+
                movie.getYear()+","+movie.getRuntime()+","+movie.getRating()+","+filter(movie.getPlot_text())+","+
                filter(movie.getPoster_url())+","+filter(movie.getBackground_url())+")";               
        if (DbService.update(sql, null)!=-1){
            sql = "SELECT COUNT(*) FROM movies";
            int movie_id = DbService.queryForInt(sql, null);
            sql = "INSERT INTO collections VALUES (DEFAULT, "+owner_id+","+movie_id+",'Blu-ray','Available')";
            DbService.update(sql, null);
        }
    }
    
    // update movie details
    public void updateMovie(Movie movie, int owner_id){
        String sql = "UPDATE movies SET movie_title="+ filter(movie.getMovie_title())+", year="+
                movie.getYear()+",runtime="+movie.getRuntime()+",rating="+movie.getRating()+",plot_text="+filter(movie.getPlot_text())+
                ",poster_url="+filter(movie.getPoster_url())+",background_url="+filter(movie.getBackground_url())+" WHERE movie_id="+movie.getMovie_id();
        DbService.update(sql, null);
    }
    
    
    // delete movie from selected users collection
    public void deleteMovie(int movie_id, int owner_id){
        String sql = "DELETE FROM collection WHERE movie_id = ? AND owner_id = ? ";               
        DbService.update(sql, new Object[] {movie_id, owner_id});
    }
    
    // get movies details
    public Movie getMovie(int movie_id){        
        // get basic movie details
        String sql = "SELECT * FROM movies WHERE movie_id = ?";
        Movie movie = DbService.queryForObject(sql, movie_id, Movie.class);

        // get movie genres
        sql = "SELECT genre_name FROM movie_genres, genres WHERE movie_genres.genre_id=genres.genre_id AND movie_id = ?";
        movie.genres = DbService.queryForList(sql, movie_id, Genre.class);

        // get movie cast
        sql = "SELECT person_name, date_of_birth, image_url, character_name FROM people, \"cast\", characters " + 
                     "WHERE people.people_id = \"cast\".person_id AND \"cast\".character_id = characters.character_id AND movie_id = ?";
        movie.cast = DbService.queryForList(sql, movie_id, CastMember.class);
        
        // get rest of the movie crew and sort directors and writers to own lists
        sql = "SELECT person_name, date_of_birth, image_url, position FROM people, crew WHERE people.people_id = crew.people_id " +
                "AND movie_id = ?";
        List<CrewMember> crew = DbService.queryForList(sql, movie_id, CrewMember.class);    
        movie.directors = new ArrayList<CrewMember>();
        movie.writers = new ArrayList<CrewMember>();
        for (CrewMember member : crew){
            if (member.getPosition().equals("Director")){
                movie.directors.add(member);
            } else if (member.getPosition().equals("Writer")){
                movie.writers.add(member);
            }
        }        
        return movie;
    }
    
    //getters & setters
    public int getMovie_id(){ return this.id; }
    public String getMovie_title(){ return this.movieTitle; }
    public int getYear(){ return this.year; }
    public int getRuntime(){ return this.runtime; }
    public float getRating(){ return this.rating; }
    public String getPlot_text(){ return this.plotText; }
    public String getPoster_url(){ return this.posterUrl; }
    public String getBackground_url(){ return this.backgroundUrl; }
    public String getTrailer_url(){ return this.trailerUrl; }
    public String getFormat_type(){ return this.formatType; }
    public String getAvailability(){ return this.availability; }
    public List<Genre> getGenres(){ return this.genres; }
    public List<CastMember> getCast(){ return this.cast; }
    public List<CrewMember> getDirectors(){ return this.directors; }
    public List<CrewMember> getWriters(){ return this.writers; }
    
    public void setMovie_id(int value){ this.id = value; }
    public void setMovie_id(String value) { this.id = Integer.parseInt(value); }
    public void setMovie_title(String value){ this.movieTitle = value; }
    public void setYear(int value){ this.year = value; }
    public void setYear(String value) { this.year = Integer.parseInt(value); }
    public void setRuntime(int value){ this.runtime = value; }
    public void setRuntime(String value) { this.runtime = Integer.parseInt(value); }
    public void setRating(float value){ this.rating = value; }
    public void setRating(String value) { this.rating = Float.parseFloat(value); }
    public void setPlot_text(String value){ this.plotText = value; }
    public void setPoster_url(String value){ this.posterUrl = value; }
    public void setBackground_url(String value){ this.backgroundUrl = value; }
    public void setTrailer_url(String value){ this.trailerUrl = value; }
    public void setFormatType(String value){ this.formatType = value; }
    public void setAvailability(String value){ this.availability = value; }
}
