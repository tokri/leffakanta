package leffakanta.model;

import leffakanta.service.Database;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;
import static org.springframework.web.util.HtmlUtils.htmlEscape;
import static org.springframework.web.util.HtmlUtils.htmlUnescape;

public class Movie {
    private int movieId;
    
    @Size(min=1, max=50) @NotNull 
    private String movieTitle;
    
    //regexp number between 1900 and 2099
    @Pattern(regexp = "^(19|20)\\d{2}$") @NotNull
    private String year;    
    
    //regexp null or 0>= and <1000
    @Pattern(regexp = "^$|\\d{1,3}(?:\\.\\d{1,5})?$")
    private String runtime;    
    
    //regexp null or number between 0 and 10 with optional 1 digit
    @Pattern(regexp = "^$|10([.,]0)?|(\\d([.,]\\d{1})?)")
    private String rating;    
    
    @Size(min=0, max=500) 
    private String plotText;
    
    @URL 
    private String posterUrl;
    
    @URL
    private String backgroundUrl;
    
    @URL
    private String trailerUrl;
    
    private String formatType;
    private int collectionID;
    private int ownerCount;
    private boolean newMovie;
    private List<Genre> genres;
    private List<Role> roles;

    // add movie into users collection
    // return -1 if unsuccessful, 0 if existing movie added into collection and return movie_id if new movie added
    public int addMovie(Movie movie, int user_id){
        int retVal = -1;
        String sql = "SELECT movie_id FROM movie WHERE movie_title = ? AND year = ?";
        int movie_id = Database.queryForInt(sql, new Object[]{ movie.getMovieTitle(), parseInt(movie.getYear())}); 
        if (movie_id==-1){
            sql = "INSERT INTO movie VALUES (DEFAULT,?,?,null,null,null,null,null) RETURNING movie_id";           
            movie_id = Database.queryForInt(sql, new Object[]{ movie.getMovieTitle(), parseInt(movie.getYear())});
            retVal = movie_id;
        }
        if (movie_id!=-1){
            sql = "INSERT INTO collectable VALUES (DEFAULT, "+user_id+","+movie_id+",'"+movie.getFormatType()+"')";
            Database.update(sql, null);
            if (retVal == -1){
                retVal = 0;
            }
        }
        return retVal;
    }
    
    // update movie details
    public void updateMovie(Movie movie){
        String sql = "UPDATE movie SET movie_title=?, year=?, runtime=?, rating=?, plot_text=?, poster_url=?, background_url=? " +
                " WHERE movie_id=?";
        Database.update(sql, new Object[]{ movie.getMovieTitle(), parseInt(movie.getYear()), parseInt(movie.getRuntimeForSql()), 
            parseFloat(movie.getRatingForSql()), movie.getPlotText(), movie.getPosterUrl(), movie.getBackgroundUrl(), movie.getMovieId()}); 
    }
    
    
    // remove movie from selected users collection
    public void removeMovie(int movie_id, int collection_id, int user_id){
        String sql = "DELETE FROM collectable WHERE item_id = ? AND user_id = ?";
        Database.update(sql, new Object[] {collection_id, user_id});
        
        // if last user with the same movie, delete movie records too
        sql = "SELECT COUNT(*) FROM collectable WHERE movie_id = ?";
        int count = Database.queryForInt(sql, movie_id);
        if (count == 0){            
            deleteMovie(movie_id);
        }
    }
    
    // delete movie alltogether
    public void deleteMovie(int movie_id){
        String sql = "DELETE FROM movie WHERE movie_id = ?";
        Database.update(sql, movie_id);
    }
    
    // get movie's details
    public Movie getMovie(int movie_id){        
        // get basic movie details
        String sql = "SELECT * FROM movie WHERE movie_id = ?";
        Movie movie = Database.queryForObject(sql, movie_id, Movie.class);

        // get movie's genres
        sql = "SELECT genre_name FROM movie_genre, genre WHERE movie_genre.genre_id=genre.genre_id AND movie_id = ?";
        movie.genres = Database.queryForList(sql, movie_id, Genre.class);

        sql = "SELECT role_id, person.person_id, production_role, person_name, image_url, null as character_name FROM " +
                "person, role WHERE production_role != 'Actor' AND role.person_id = person.person_id AND movie_id = ? " +
                "UNION SELECT role_id, person.person_id, production_role, person_name, image_url, character_name FROM " +
                "person, role, \"character\" WHERE production_role = 'Actor' AND role.character_id = \"character\".character_id " +
                "AND role.person_id = person.person_id AND movie_id = ? ORDER BY role_id ASC;";
        movie.roles = Database.queryForList(sql, new Object[]{ movie_id, movie_id }, Role.class);        
        return movie;
    }
    
    // get movie details by collection id
    public Movie getMovieFromCollection(int collection_id){
        // get basic movie details
        String sql = "SELECT DISTINCT item_id as collection_id, movie.movie_id, movie_title, year, format_type " +
                "FROM movie, collectable WHERE movie.movie_id = collectable.movie_id AND item_id = ?";
        Movie movie = Database.queryForObject(sql, collection_id, Movie.class);
        return movie;
    }
    
    // method to parse & fix forms float values
    private float parseFloat(String value){
        float retVal = 0;
        try {
            retVal = Float.parseFloat(value.replace(",", "."));
        } catch (NumberFormatException e){}
        return retVal;        
    }
    
    // method to parse int values
    private int parseInt(String value){
        int retVal = 0;
        try {
            retVal = Integer.parseInt(value);
        } catch (NumberFormatException e){}
        return retVal;
    }
    
    //change empty value to -1 so it will not default as 0 on database
    private String changeEmptyValue(String value){
        if (value == null || value.isEmpty()){
            return "-1";
        }
        return value;
    }

    //change -1 to empty so it will look normal when editing or showing the value
    private String changeMinusValue(String value){
        if (value == null || value.contains("-1")){
            return "";
        }
        return value;
    }
    
    // retrieve a list of names for enumeration
    public List<String> getEnumValues(String enumName)
    {        
        String sql = "SELECT enum_range(null::" + enumName + ")";
        List<String> enums = Database.queryForCommaSeparatedList(sql);
        return enums;
    }    
    
    // get role list by rolename
    private List<Role> getRoles(String roleName){
        List<Role> roleList = new ArrayList<Role>();
        if (this.roles == null){
            return roleList;
        }
        for (Role role : this.roles){
            if (role.getProductionRole().equals(roleName)){
                roleList.add(role);
            }
        }
        return roleList;
    }
    
    //getters & setters
    public int getMovieId(){ return this.movieId; }
    public String getMovieTitle(){ return htmlEscape(this.movieTitle); }
    public String getYear(){ return this.year; }
    public String getRuntime(){ return changeMinusValue(this.runtime); }
    public String getRuntimeForSql(){ return changeEmptyValue(this.runtime); }
    public String getRating(){ return changeMinusValue(this.rating); }
    public String getRatingForSql(){ return changeEmptyValue(this.rating); }
    public String getPlotText(){ return htmlEscape(this.plotText); }
    public String getPosterUrl(){ return htmlEscape(this.posterUrl); }
    public String getBackgroundUrl(){ return htmlEscape(this.backgroundUrl); }
    public String getTrailerUrl(){ return htmlEscape(this.trailerUrl); }
    public String getFormatType(){ return this.formatType; }
    public int getCollectionId(){ return this.collectionID; }
    public int getOwnerCount(){ return this.ownerCount; }
    public boolean getNewMovie(){ return this.newMovie; }
    public List<Genre> getGenres(){ return this.genres; }
    public List<Role> getCast(){ return this.getRoles("Actor"); }
    public List<Role> getDirectors(){ return this.getRoles("Director"); }
    public List<Role> getWriters(){ return this.getRoles("Writer"); }
    
    public void setMovieId(int value){ this.movieId = value; }
    public void setMovieTitle(String value){ this.movieTitle = htmlUnescape(value); }
    public void setYear(String value){ this.year = value; }
    public void setRuntime(String value){ this.runtime = value; }
    public void setRating(String value){ this.rating = value; }
    public void setPlotText(String value){ this.plotText = htmlUnescape(value); }
    public void setFormatType(String value){ this.formatType = value; }
    public void setPosterUrl(String value){ this.posterUrl = value; }
    public void setBackgroundUrl(String value){ this.backgroundUrl = value; }
    public void setTrailerUrl(String value){ this.trailerUrl = value; }
    public void setCollectionId(int value){ this.collectionID = value; }
    public void setOwnerCount(int value){ this.ownerCount = value; }
    public void setNewMovie(boolean value){ this.newMovie = value; };
}
