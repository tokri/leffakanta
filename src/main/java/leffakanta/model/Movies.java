package leffakanta.model;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class Movies {   
    
    // get users movie collection
    public List<Movie> getMovieList(int owner_id){
        String sql = "SELECT DISTINCT movies.movie_id, movie_title, year, rating, owner_id, format_type, " +
                "availability FROM movies, collections WHERE movies.movie_id = collections.movie_id AND owner_id = ? " +
                "ORDER BY movie_title, year ASC";
        List<Movie> movies = DbService.queryForList(sql, owner_id, Movie.class);
        return movies;
    }

    // get movie amount
    public int getMovieCount(int owner_id){
        String sql = "SELECT COUNT(*) FROM collections WHERE owner_id = ?";
        int count = DbService.queryForInt(sql, owner_id);
        return count;
    }    
}