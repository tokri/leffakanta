package leffakanta.model;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {   
    private static DataSource dataSource;
    
    //use jdbcTemplate to handle db connection and sql injection attempts
    private JdbcTemplate jdbcTemplate; 
    
    @Autowired
    public void setDataSource(DataSource dataSource) {
	this.dataSource = dataSource;
    }    

    // get user's details
    public User GetUser(String username){
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "SELECT * FROM users WHERE username = ?";
        List<User> users = jdbcTemplate.query(sql, new Object[] { username  },                
                            new BeanPropertyRowMapper(User.class));
        if (users.isEmpty()){
            return null;
        }
        User user = users.get(0);
        return user;
    }

    // get users movie collection
    public List<Movie> GetMovieList(int owner_id){
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "SELECT DISTINCT movies.movie_id, movie_title, year, rating, owner_id FROM movies, collections WHERE owner_id = ?";
        List<Movie> movies = jdbcTemplate.query(sql, new Object[] { owner_id },
                            new BeanPropertyRowMapper(Movie.class));
        return movies;
    }

    // get movies details
    public Movie GetMovie(int movie_id){
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "SELECT * FROM movies WHERE movie_id = ?";
        Movie movie = (Movie) jdbcTemplate.queryForObject(sql, new Object[] { movie_id },
                        new BeanPropertyRowMapper(Movie.class));
        return movie;
    }
}