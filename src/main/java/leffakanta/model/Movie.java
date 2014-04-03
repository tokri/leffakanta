package leffakanta.model;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class Movie {
    private int id;
    private String title;
    private int year;
    private int runtime;
    private float rating;
    private String plotText;
    private String posterUrl;
    private String backgroundUrl;
    private String trailerUrl;
    private String formatType;
    private String availability;
    private List<String> directors;
    private List<String> writers;
    private List<String> producers;

    private static DataSource dataSource;
    
    @Autowired
    public void setDataSource(DataSource dataSource) {
	this.dataSource = dataSource;
    }    

    // get jdbcTemplate for handling db connection and sql injection attempts
    private JdbcTemplate getJdbcTemplate(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate;
    }
    
    // get movies details
    public Movie getMovie(int movie_id){        
        String sql = "SELECT * FROM movies WHERE movie_id = ?";
        Movie movie = (Movie) getJdbcTemplate().queryForObject(sql, new Object[] { movie_id },
                        new BeanPropertyRowMapper(Movie.class));
        return movie;
    }
    
    //getters & setters
    public int getMovie_id(){ return this.id; }
    public String getMovie_title(){ return this.title; }
    public int getYear(){ return this.year; }
    public int getRuntime(){ return this.runtime; }
    public float getRating(){ return this.rating; }
    public String getPlot_text(){ return this.plotText; }
    public String getPoster_url(){ return this.posterUrl; }
    public String getBackground_url(){ return this.backgroundUrl; }
    public String getTrailer_url(){ return this.trailerUrl; }
    public String getFormatType(){ return this.formatType; }
    public String getAvailability(){ return this.availability; }
    
    public void setMovie_id(int value){ this.id = value; }
    public void setMovie_title(String value){ this.title = value; }
    public void setYear(int value){ this.year = value; }
    public void setRuntime(int value){ this.runtime = value; }
    public void setRating(float value){ this.rating = value; }
    public void setPlot_text(String value){ this.plotText = value; }
    public void setPoster_url(String value){ this.posterUrl = value; }
    public void setBackground_url(String value){ this.backgroundUrl = value; }
    public void setTrailer_url(String value){ this.trailerUrl = value; }
    public void setFormatType(String value){ this.formatType = value; }
    public void setAvailability(String value){ this.availability = value; }
}
