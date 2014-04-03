package leffakanta.model;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class Movies {   
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
    
    // get users movie collection
    public List<Movie> getMovieList(int owner_id){
        String sql = "SELECT DISTINCT movies.movie_id, movie_title, year, rating, owner_id, format_type, " +
                "availability FROM movies, collections WHERE movies.movie_id = collections.movie_id AND owner_id = ?";
        List<Movie> movies = getJdbcTemplate().query(sql, new Object[] { owner_id },
                            new BeanPropertyRowMapper(Movie.class));
        return movies;
    }

    // get movie amount
    public int getMovieCount(int owner_id){
        String sql = "SELECT COUNT(*) FROM collections WHERE owner_id = ?";
        int count = getJdbcTemplate().queryForInt(sql, new Object[] { owner_id });
        return count;
    }    
}