package leffakanta.model;

public class User {
    private int user_id;
    private String username;
    private String password_hash;
    private boolean is_admin;
    
    
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
