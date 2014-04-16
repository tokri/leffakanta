package leffakanta.model;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private int person_id;
    private String person_name;
    private String date_of_birth;
    private String image_url;
    
    // get person's details
    public Person getPerson(int person_id){        
        // get basic movie details
        String sql = "SELECT * FROM people WHERE person_id = ?";
        Person person = DbService.queryForObject(sql, person_id, Person.class);
        return person;
    }
       
    //getters & setters
    public int getPerson_id(){ return this.person_id; }
    public String getPerson_name(){ return this.person_name; }
    public String getDate_of_birth(){ return this.date_of_birth; }
    public String getImage_url(){ return this.image_url; }

    public void setPerson_Id(int value){ this.person_id = value; }
    public void setPerson_name(String value){ this.person_name = value; }
    public void setDate_of_birth(String value){ this.date_of_birth = value; }
    public void setImage_url(String value){ this.image_url = value; }
}
