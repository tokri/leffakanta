package leffakanta.model;

public class Validate {   

    // verify user's login details and return user object if login ok
    public User CheckLogin(String username, String password){
        DatabaseService db = new DatabaseService();
        PasswordHash hash = new PasswordHash();
        User db_user = db.GetUser(username);
        
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
}
