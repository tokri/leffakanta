package leffakanta.model;

import leffakanta.service.Database;
import java.util.ArrayList;
import java.util.List;

public class Roles {
    private List<RoleAndMovie> roles;
    
    // get person's details
    public void updatePersonRoles(int personId, int userId){        
        // get basic movie details
        String sql = "SELECT movies.movie_id, movie_title, year, production_role, character_name FROM movies, roles, characters, collections WHERE "+
                "roles.movie_id = movies.movie_id AND roles.character_id = characters.character_id AND movies.movie_id = collections.movie_id AND " +
                "person_id = ? AND owner_id = ? UNION SELECT movies.movie_id, movie_title, year, production_role, null as character_name FROM movies, " +
                "roles, collections WHERE character_id IS NULL AND roles.movie_id = movies.movie_id AND collections.movie_id = movies.movie_id AND " +
                "person_id = ? AND owner_id = ? ORDER BY year DESC;";
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