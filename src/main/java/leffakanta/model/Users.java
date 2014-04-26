package leffakanta.model;

import leffakanta.service.Database;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class Users {   
    
    // get users movie collection
    public List<Movie> getUserList(){
        String sql = "SELECT user_id, username, is_admin FROM users ORDER BY username ASC";
        List<Movie> movies = Database.queryForList(sql, null, User.class);
        return movies;
    }

    // get movie amount
    public int getUserCount(){
        String sql = "SELECT COUNT(*) FROM users";
        int count = Database.queryForInt(sql, null);
        return count;
    }    
    
    // get users movie collection for given search value
    public List<Movie> searchUserList(String searchValue){
        String sql = "SELECT user_id, username, is_admin FROM users WHERE LOWER(username) LIKE ? ORDER BY username ASC";
        List<Movie> movies = Database.queryForList(sql, "%"+searchValue.toLowerCase()+"%", User.class);
        return movies;
    }

    // get movie amount for given search value
    public int searchUserCount(String searchValue){
        String sql = "SELECT COUNT(*) FROM users WHERE LOWER(username) LIKE ?";
        int count = Database.queryForInt(sql, "%"+searchValue.toLowerCase()+"%");
        return count;
    }    
}