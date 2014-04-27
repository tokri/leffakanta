package leffakanta.model;

public class Role extends Person {
    private int roleId;
    private String productionRole;
    private String characterName;
    
    // add a role
    public void addRole(String productionRole, int movieId, int personId, int characterId){
        String sql;
        if (productionRole.equals("Actor")){
            sql = "INSERT INTO role VALUES (DEFAULT,'Actor',?,?,?)";
            Database.update(sql, new Object[]{movieId, personId, characterId});
            return;
        }         
        if (productionRole.equals("Writer")){
            sql = "INSERT INTO role VALUES (DEFAULT,'Writer',?,?,?)";
        } else {
            sql = "INSERT INTO role VALUES (DEFAULT,'Director',?,?,?)";
        }
        Database.update(sql, new Object[]{movieId, personId, null});
    }    

    // remove a role, returns the count of roles with same person after removal
    public void removeRole(int role_id){
        String sql = "DELETE FROM role WHERE role_id = ?";
        Database.update(sql, role_id);        
    }
    
    public int getRoleCount(int person_id){
        // if last user with the same movie, delete movie records too
        String sql = "SELECT COUNT(*) FROM role WHERE person_id = ?";
        int count = Database.queryForInt(sql, person_id);
        return count;        
    }
    
    //getters & setters
    public int getRoleId(){ return this.roleId; }
    public String getProductionRole(){ return this.productionRole; }
    public String getCharacterName(){ return this.characterName; }

    public void setRoleId(int value){ this.roleId = value; }    
    public void setProductionRole(String value){ this.productionRole = value; }    
    public void setCharacterName(String value){ this.characterName = value; }
}
