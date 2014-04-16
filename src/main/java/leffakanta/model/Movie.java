package leffakanta.model;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.springframework.stereotype.Service;
import org.hibernate.validator.constraints.URL;
import static org.springframework.web.util.HtmlUtils.htmlEscape;
import static org.springframework.web.util.HtmlUtils.htmlUnescape;

@Service
public class Movie {
    private int id;   
    @Size(min=1, max=50) @NotNull private String movie_title;
    //regexp number between 1900 and 2099
    @Pattern(regexp = "^(19|20)\\d{2}$") @NotNull private String year;    
    //regexp null or 0>= and <1000
    @Pattern(regexp = "^$|\\d{1,3}(?:\\.\\d{1,5})?$") private String runtime;    
    //regexp null or number between 0 and 10 with optional 1 digit
    @Pattern(regexp = "^$|10([.,]0)?|(\\d([.,]\\d{1})?)") private String rating;    
    @Size(min=0, max=500) private String plot_text;
    @URL private String poster_url;
    @URL private String background_url;
    @URL private String trailer_url;
    private String format_type;
    private String availability;
    private List<Genre> genres;
    private List<Role> roles;

    // add movie into users collection
    public void addMovie(Movie movie, int owner_id){
        String sql = "INSERT INTO movies VALUES (DEFAULT,?,?,?,?,?,?,?) RETURNING movie_id";
        int movie_id = DbService.queryForInt(sql, new Object[]{ movie.getMovie_title(), parseInt(movie.getYear()), parseInt(movie.getRuntimeForSql()), 
            parseFloat(movie.getRatingForSql()), movie.getPlot_text(), movie.getPoster_url(), movie.getBackground_url()}); 
        if (movie_id!=-1){
            sql = "INSERT INTO collections VALUES (DEFAULT, "+owner_id+","+movie_id+",'Blu-ray','Available')";
            DbService.update(sql, null);
        }
    }
    
    // update movie details
    public void updateMovie(Movie movie, int owner_id){
        String sql = "UPDATE movies SET movie_title=?, year=?, runtime=?, rating=?, plot_text=?, poster_url=?, background_url=? " +
                " WHERE movie_id=?";
        DbService.update(sql, new Object[]{ movie.getMovie_title(), parseInt(movie.getYear()), parseInt(movie.getRuntimeForSql()), 
            parseFloat(movie.getRatingForSql()), movie.getPlot_text(), movie.getPoster_url(), movie.getBackground_url(), movie.getMovie_id()}); 
    }
    
    
    // delete movie from selected users collection
    public void deleteMovie(int movie_id, int owner_id){
        String sql = "DELETE FROM collections WHERE movie_id = ? AND owner_id = ?";
        DbService.update(sql, new Object[] {movie_id, owner_id});
        
        // if last user with the same movie, delete movie records too
        sql = "SELECT COUNT(*) FROM collections WHERE movie_id = ?";
        int count = DbService.queryForInt(sql, movie_id);
        if (count == 0){
            sql = "DELETE FROM movies WHERE movie_id = ?";
            DbService.update(sql, movie_id);
        }
    }
    
    // get movie's details
    public Movie getMovie(int movie_id){        
        // get basic movie details
        String sql = "SELECT * FROM movies WHERE movie_id = ?";
        Movie movie = DbService.queryForObject(sql, movie_id, Movie.class);

        // get movie's genres
        sql = "SELECT genre_name FROM movie_genres, genres WHERE movie_genres.genre_id=genres.genre_id AND movie_id = ?";
        movie.genres = DbService.queryForList(sql, movie_id, Genre.class);

        sql = "SELECT role_id, people.person_id, production_role, person_name, image_url, null as character_name FROM " +
                "people, roles WHERE production_role != 'Actor' AND roles.person_id = people.person_id AND movie_id = ? " +
                "UNION SELECT role_id, people.person_id, production_role, person_name, image_url, character_name FROM " +
                "people, roles, characters WHERE production_role = 'Actor' AND roles.character_id = characters.character_id " +
                "AND roles.person_id = people.person_id AND movie_id = ? ORDER BY role_id ASC;";
        movie.roles = DbService.queryForList(sql, new Object[]{ movie_id, movie_id }, Role.class);        
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
    
    private List<Role> getRole(String name){
        List<Role> roleList = new ArrayList<Role>();
        if (this.roles == null){
            return roleList;
        }
        for (Role role : this.roles){
            if (role.getProduction_role().equals(name)){
                roleList.add(role);
            }
        }
        return roleList;
    }
    
    //getters & setters
    public int getMovie_id(){ return this.id; }
    public String getMovie_title(){ return htmlEscape(this.movie_title); }
    public String getYear(){ return this.year; }
    public String getRuntime(){ return changeMinusValue(this.runtime); }
    public String getRuntimeForSql(){ return changeEmptyValue(this.runtime); }
    public String getRating(){ return changeMinusValue(this.rating); }
    public String getRatingForSql(){ return changeEmptyValue(this.rating); }
    public String getPlot_text(){ return htmlEscape(this.plot_text); }
    public String getPoster_url(){ return htmlEscape(this.poster_url); }
    public String getBackground_url(){ return htmlEscape(this.background_url); }
    public String getTrailer_url(){ return htmlEscape(this.trailer_url); }
    public String getFormat_type(){ return this.format_type; }
    public String getAvailability(){ return this.availability; }
    public List<Genre> getGenres(){ return this.genres; }
    public List<Role> getCast(){ return this.getRole("Actor"); }
    public List<Role> getDirectors(){ return this.getRole("Director"); }
    public List<Role> getWriters(){ return this.getRole("Writer"); }
    
    public void setMovie_id(int value){ this.id = value; }
    public void setMovie_title(String value){ this.movie_title = htmlUnescape(value); }
    public void setYear(String value){ this.year = value; }
    public void setRuntime(String value){ this.runtime = value; }
    public void setRating(String value){ this.rating = value; }
    public void setPlot_text(String value){ this.plot_text = htmlUnescape(value); }
    public void setFormatType(String value){ this.format_type = value; }
    public void setAvailability(String value){ this.availability = value; }
    public void setPoster_url(String value){ this.poster_url = value; }
    public void setBackground_url(String value){ this.background_url = value; }
    public void setTrailer_url(String value){ this.trailer_url = value; }
}
