package leffakanta.model;

import java.util.ArrayList;
import java.util.List;

public class Roles {
    private List<RoleAndMovie> roles;
    
    // get person's details
    public void updatePersonRoles(int personId, int userId){        
        // get basic movie details
        String sql = "SELECT movie.movie_id, movie_title, year, production_role, character_name FROM movie, role, \"character\", collectable WHERE "+
                "role.movie_id = movie.movie_id AND role.character_id = \"character\".character_id AND movie.movie_id = collectable.movie_id AND " +
                "person_id = ? AND user_id = ? UNION SELECT movie.movie_id, movie_title, year, production_role, null as character_name FROM movie, " +
                "role, collectable WHERE character_id IS NULL AND role.movie_id = movie.movie_id AND collectable.movie_id = movie.movie_id AND " +
                "person_id = ? AND user_id = ? ORDER BY year DESC;";
        this.roles = Database.queryForList(sql, new Object[]{ personId, userId, personId, userId }, RoleAndMovie.class);
    }    
    
    private List<RoleAndMovie> getPersonRole(String name){
        List<RoleAndMovie> roleList = new ArrayList<RoleAndMovie>();
        if (this.roles == null){
            return roleList;
        }
        for (RoleAndMovie role : this.roles){
            if (role.getProductionRole().equals(name)){
                roleList.add(role);
            }
        }
        return roleList;
    }

    public List<RoleAndMovie> getActing(){ return getPersonRole("Actor"); }
    public List<RoleAndMovie> getDirecting(){ return getPersonRole("Director"); }
    public List<RoleAndMovie> getWriting(){ return getPersonRole("Writer"); }    
    
}