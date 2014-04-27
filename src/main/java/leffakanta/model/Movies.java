package leffakanta.model;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class Movies {   
    
    // get all movies
    public List<Movie> getMovieList(int page, int pageSize){
        String sql = "SELECT m.movie_id, movie_title, year, rating, c.owner_count FROM movie m " +
                "JOIN (SELECT movie_id, COUNT(movie_id) as owner_count FROM collectable GROUP BY movie_id) c "+
                "ON m.movie_id = c.movie_id ORDER BY movie_title, year ASC OFFSET ? LIMIT ?";
        List<Movie> movies = Database.queryForList(sql, new Object[]{ (page-1)*pageSize, pageSize}, Movie.class);
        return movies;
    }

    // get users movie collection
    public List<Movie> getMovieList(int userId, int page, int pageSize){
        String sql = "SELECT DISTINCT item_id as collection_id, movie.movie_id, movie_title, year, rating, user_id, format_type " +
                "FROM movie, collectable WHERE movie.movie_id = collectable.movie_id AND user_id = ? " +
                "ORDER BY movie_title, year ASC OFFSET ? LIMIT ?";
        List<Movie> movies = Database.queryForList(sql, new Object[]{ userId, (page-1)*pageSize, pageSize}, Movie.class);
        return movies;
    }

    // get all movies for given search value
    public List<Movie> searchMovieList(String searchValue){
        String sql = "SELECT m.movie_id, movie_title, year, rating, c.owner_count FROM movie m " +
                "JOIN (SELECT movie_id, COUNT(movie_id) as owner_count FROM collectable GROUP BY movie_id) c "+
                "ON m.movie_id = c.movie_id WHERE LOWER(movie_title) LIKE ? ORDER BY movie_title, year ASC";
        List<Movie> movies = Database.queryForList(sql, "%"+searchValue.toLowerCase()+"%", Movie.class);
        return movies;
    }

    // get users movie collection for given search value
    public List<Movie> searchMovieList(int userId, String searchValue){
        String sql = "SELECT DISTINCT item_id as collection_id, movie.movie_id, movie_title, year, rating, user_id, format_type " +
                "FROM movie, collectable WHERE movie.movie_id = collectable.movie_id AND user_id = ? AND LOWER(movie_title) LIKE ? " +
                "ORDER BY movie_title, year ASC";
        List<Movie> movies = Database.queryForList(sql, new Object[] { userId, "%"+searchValue.toLowerCase()+"%" }, Movie.class);
        return movies;
    }

    // get movie count for given search value
    public int searchMovieCount(String searchValue){
        String sql = "SELECT COUNT(*) FROM movie WHERE LOWER(movie_title) LIKE ?";
        int count = Database.queryForInt(sql, "%"+searchValue.toLowerCase()+"%");
        return count;        
    }

    // get movie count for given search value
    public int searchMovieCount(int userId, String searchValue){
        String sql = "SELECT COUNT(*) FROM movie, collectable WHERE movie.movie_id = collectable.movie_id "+
                    "AND user_id = ? AND LOWER(movie_title) LIKE ?";
        int count = Database.queryForInt(sql, new Object[] { userId, "%"+searchValue.toLowerCase()+"%" });
        return count;
    }    

    // get movie amount for all users
    public int getMovieCount(){
        String sql = "SELECT COUNT(*) FROM collectable";
        int count = Database.queryForInt(sql, null);
        return count;
    }    

    // get movie amount for user
    public int getMovieCount(int userId){
        String sql = "SELECT COUNT(*) FROM collectable WHERE user_id = ?";
        int count = Database.queryForInt(sql, userId);
        return count;
    }    
}