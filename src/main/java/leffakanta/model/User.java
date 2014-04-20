package leffakanta.model;

import leffakanta.service.DbService;
import leffakanta.service.PasswordHash;
import javax.validation.constraints.AssertTrue;
import org.springframework.stereotype.Service;

@Service
public class User {
    private int userId;
    private String username;
    private String passwordEntered;
    private String passwordHash;
    private boolean isAdmin;
    private String passwordNew;
    private String passwordNewConfirm;
    private boolean passwordChangeValid;


    @AssertTrue(message="entered passwords didn't match")
    private boolean isPasswordChangeValid() {
        return this.passwordNew.equals(this.passwordNewConfirm);
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
        User dbUser = getUser(username);
        
        // if no user found, return null
        if (dbUser==null){
            return null;
        }               
        String dbUsername = dbUser.getUsername();
        String dbPasswordHash = dbUser.getPasswordHash();
        
        try{
            // if username and password matches, return user-object
            if (dbUsername.equals(username) && hash.validatePassword(password, dbPasswordHash)){        
                return dbUser;
            }
        } catch (Exception e){};
        
        // return null if username and password not ok or exception is thrown
        return null;        
    }
    
    // add a new user
    public boolean newUser(User user){
        try{
            PasswordHash hash = new PasswordHash();
            user.setIsAdmin(false);
            user.setPasswordHash(hash.createHash(user.passwordNew));
            String sql = "INSERT INTO users VALUES (DEFAULT,?,?,false)";
            DbService.update(sql, new Object[]{ user.getUsername(), user.getPasswordHash()});
            user.setPasswordNew("");
            user.setPasswordNewConfirm("");
        } catch (Exception e){
            return false;
        }
        return true;
    }


    // update user's account details
    public boolean updateUser(User user){
        String sql;
        try{
            if (user.getPasswordNew().isEmpty()){
                sql = "UPDATE users SET username=?, is_admin=? WHERE user_id=?";
                DbService.update(sql, new Object[]{ user.getUsername(), user.getIsAdmin(), user.getUserId()}); 
            } else {
                PasswordHash hash = new PasswordHash();        
                user.setPasswordHash(hash.createHash(user.passwordNew));
                sql = "UPDATE users SET username=?, password_hash=?, is_admin=? WHERE user_id=?";
                DbService.update(sql, new Object[]{ user.getUsername(), user.getPasswordHash(), user.getIsAdmin(), user.getUserId()}); 
                user.setPasswordNew("");
                user.setPasswordNewConfirm("");
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
    public int getUserId(){ return this.userId; }
    public String getUsername(){ return this.username; }
    public String getPasswordHash(){ return this.passwordHash; }
    public boolean getIsAdmin(){ return this.isAdmin; }
    public String getPasswordNew(){ return this.passwordNew; }
    public String getPasswordNewConfirm(){ return this.passwordNewConfirm; }
    public boolean getPasswordChangeValid(){ return this.passwordChangeValid; }
    public String getPasswordEntered(){ return this.passwordEntered; }
    
    public void setUserId(int value){ this.userId = value; };
    public void setUsername(String value){ this.username = value; };
    public void setPasswordHash(String value){ this.passwordHash = value; };
    public void setIsAdmin(boolean value){ this.isAdmin = value; };
    public void setPasswordNew(String value){ this.passwordNew = value; };
    public void setPasswordNewConfirm(String value){ this.passwordNewConfirm = value; };
    public void setPasswordChangeValid(boolean value){ this.passwordChangeValid = value; };
    public void setPasswordEntered(String value){ this.passwordEntered = value; };
    
}
