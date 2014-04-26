package leffakanta.model;

import leffakanta.service.Database;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class Users {   
    
    // get list of users
    public List<User> getUserList(int page, int pageSize){
        String sql = "SELECT user_id, username, is_admin FROM users ORDER BY username ASC OFFSET ? LIMIT ?";
        List<User> users = Database.queryForList(sql, new Object[]{ (page-1)*pageSize, pageSize}, User.class);
        return users;
    }

    // get user count
    public int getUserCount(){
        String sql = "SELECT COUNT(*) FROM users";
        int count = Database.queryForInt(sql, null);
        return count;
    }    
    
    // get users for given search value
    public List<User> searchUserList(String searchValue){
        String sql = "SELECT user_id, username, is_admin FROM users WHERE LOWER(username) LIKE ? ORDER BY username ASC";
        List<User> users = Database.queryForList(sql, "%"+searchValue.toLowerCase()+"%", User.class);
        return users;
    }

    // get user count for given search value
    public int searchUserCount(String searchValue){
        String sql = "SELECT COUNT(*) FROM users WHERE LOWER(username) LIKE ?";
        int count = Database.queryForInt(sql, "%"+searchValue.toLowerCase()+"%");
        return count;
    }    
}