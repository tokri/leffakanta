package leffakanta.model;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {   
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate; 
    
    @Autowired
    public void setDataSource(DataSource dataSource) {
	this.dataSource = dataSource;
    }    

    public List<Movie> GetMovieList(){
        String sql = "SELECT movie_id, movie_title, year, rating FROM movies";
        jdbcTemplate = new JdbcTemplate(dataSource);

        // jdbcTemplate handles sql injection attempts, no separate sql injection checks necessary
        List<Movie> movies = jdbcTemplate.query(sql,
                            new BeanPropertyRowMapper(Movie.class));
        return movies;
    }

    public Movie GetMovie(int movie_id){
        String sql = "SELECT * FROM movies WHERE movie_id = ?";
        jdbcTemplate = new JdbcTemplate(dataSource);

        Movie movie = (Movie) jdbcTemplate.queryForObject(sql, new Object[] { movie_id },
                        new BeanPropertyRowMapper(Movie.class));
        return movie;
    }
}