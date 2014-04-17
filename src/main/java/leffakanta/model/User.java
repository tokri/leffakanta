package leffakanta.model;

import leffakanta.service.DbService;
import leffakanta.service.PasswordHash;
import javax.validation.constraints.AssertTrue;
import org.springframework.stereotype.Service;

@Service
public class User {
    private int user_id;
    private String username;
    private String password_entered;
    private String password_hash;
    private boolean is_admin;
    private String password_new;
    private String password_new_confirm;
    private boolean password_change_valid;


    @AssertTrue(message="entered passwords didn't match")
    private boolean isPassword_change_valid() {
        return this.password_new.equals(this.password_new_confirm);
    }    
    
    // get user's details
    public User getUser(String username){
        String sql = "SELECT * FROM users WHERE username = ?";
        User user = DbService.queryForObject(sql, username, User.class);
        return user;
    }
    
    // get user by id
    public User getUser(int user_id){
        String sql = "SELECT * FROM users WHERE user_id = ?";
        User user = DbService.queryForObject(sql, user_id, User.class);
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
    
    // add a new user
    public boolean newUser(User user){
        try{
            PasswordHash hash = new PasswordHash();
            user.setIs_admin(false);
            user.setPassword_hash(hash.createHash(user.password_new));
            String sql = "INSERT INTO users VALUES (DEFAULT,?,?,false)";
            DbService.update(sql, new Object[]{ user.getUsername(), user.getPassword_hash()});         
            user.setPassword_new("");
            user.setPassword_new_confirm("");
        } catch (Exception e){
            return false;
        }
        return true;
    }


    // update user's account details
    public boolean updateUser(User user){
        String sql;
        try{
            if (user.getPassword_new().isEmpty()){
                sql = "UPDATE users SET username=?, is_admin=? WHERE user_id=?";
                DbService.update(sql, new Object[]{ user.getUsername(), user.getIs_admin(), user.getUser_id()}); 
            } else {
                PasswordHash hash = new PasswordHash();        
                user.setPassword_hash(hash.createHash(user.password_new));
                sql = "UPDATE users SET username=?, password_hash=?, is_admin=? WHERE user_id=?";
                DbService.update(sql, new Object[]{ user.getUsername(), user.getPassword_hash(), user.getIs_admin(), user.getUser_id()}); 
                user.setPassword_new("");
                user.setPassword_new_confirm("");
            }
        } catch (Exception e){
            return false;
        }
        return true;
    }
    
    // delete user
    public void deleteUser(int user_id){
        String sql = "DELETE FROM users WHERE user_id = ?";
        DbService.update(sql, user_id);        
    }

    //getters & setters
    public int getUser_id(){ return this.user_id; }
    public String getUsername(){ return this.username; }
    public String getPassword_hash(){ return this.password_hash; }
    public boolean getIs_admin(){ return this.is_admin; }
    public String getPassword_new(){ return this.password_new; }
    public String getPassword_new_confirm(){ return this.password_new_confirm; }
    public boolean getPassword_change_valid(){ return this.password_change_valid; }
    public String getPassword_entered(){ return this.password_entered; }
    
    public void setUser_id(int value){ this.user_id = value; };
    public void setUsername(String value){ this.username = value; };
    public void setPassword_hash(String value){ this.password_hash = value; };
    public void setIs_admin(boolean value){ this.is_admin = value; };
    public void setPassword_new(String value){ this.password_new = value; };
    public void setPassword_new_confirm(String value){ this.password_new_confirm = value; };
    public void setPassword_change_valid(boolean value){ this.password_change_valid = value; };
    public void setPassword_entered(String value){ this.password_entered = value; };
    
}
