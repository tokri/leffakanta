package leffakanta.model;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class User {
    private int user_id;
    private String username;
    private String password_hash;
    private boolean is_admin;
    
    // get user's details
    public User getUser(String username){
        String sql = "SELECT * FROM users WHERE username = ?";
        User user = DbService.queryForObject(sql, username, User.class);
        return user;
    }
    
    // verify user's login details and return user object if login ok
    public User checkLogin(String username, String password){
        PasswordHash hash = new PasswordHash();
        User db_user = getUser(username);
        
        // if no user found, return null
        if (db_user==null){
            return null;
        }       
        
        String db_username = db_user.getUsername();
        String db_password_hash = db_user.getPassword_hash();
        
        try{
            // if username and password matches, return user-object
            if (db_username.equals(username) && hash.validatePassword(password, db_password_hash)){        
                return db_user;
            }
        } catch (Exception e){};
        
        // return null if username and password not ok or exception is thrown
        return null;        
    }
    
    //getters & setters
    public int getUser_id(){ return this.user_id; }
    public String getUsername(){ return this.username; }
    public String getPassword_hash(){ return this.password_hash; }
    public boolean getIs_admin(){ return this.is_admin; }
    
    public void setUser_id(int value){ this.user_id = value; };
    public void setUsername(String value){ this.username = value; };
    public void setPassword_hash(String value){ this.password_hash = value; };
    public void setIs_admin(boolean value){ this.is_admin = value; };
}
