package leffakanta.model;

import leffakanta.service.DbService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class Movies {   
    
    // get all movies
    public List<Movie> getMovieList(){
        String sql = "SELECT m.movie_id, movie_title, year, rating, c.owner_count FROM movies m " +
                "JOIN (SELECT movie_id, COUNT(movie_id) as owner_count FROM collections GROUP BY movie_id) c "+
                "ON m.movie_id = c.movie_id ORDER BY movie_title, year ASC;";
        List<Movie> movies = DbService.queryForList(sql, null, Movie.class);
        return movies;
    }

    // get users movie collection
    public List<Movie> getMovieList(int userId){
        String sql = "SELECT DISTINCT movies.movie_id, movie_title, year, rating, owner_id, format_type, " +
                "availability FROM movies, collections WHERE movies.movie_id = collections.movie_id AND owner_id = ? " +
                "ORDER BY movie_title, year ASC";
        List<Movie> movies = DbService.queryForList(sql, userId, Movie.class);
        return movies;
    }

    // get movie amount for all users
    public int getMovieCount(){
        String sql = "SELECT COUNT(*) FROM collections";
        int count = DbService.queryForInt(sql, null);
        return count;
    }    

    // get movie amount for user
    public int getMovieCount(int userId){
        String sql = "SELECT COUNT(*) FROM collections WHERE owner_id = ?";
        int count = DbService.queryForInt(sql, userId);
        return count;
    }    
}