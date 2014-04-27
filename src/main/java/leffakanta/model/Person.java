package leffakanta.model;

import leffakanta.service.Database;
import org.hibernate.validator.constraints.URL;

public class Person {
    private int personId;
    private String personName;
    @URL
    private String imageUrl;
    
    // get person's details
    public Person getPerson(int personId){        
        // get basic movie details
        String sql = "SELECT * FROM person WHERE person_id = ?";
        Person person = Database.queryForObject(sql, personId, Person.class);
        return person;
    }
       
    // update person details
    public void updatePerson(Person person){
        String sql = "UPDATE person SET person_name=?, image_url=? WHERE person_id=?";
        Database.update(sql, new Object[]{ person.getPersonName(), person.getImageUrl(), person.getPersonId()}); 
    }
    
    //getters & setters
    public int getPersonId(){ return this.personId; }
    public String getPersonName(){ return this.personName; }
    public String getImageUrl(){ return this.imageUrl; }

    public void setPersonId(int value){ this.personId = value; }
    public void setPersonName(String value){ this.personName = value; }
    public void setImageUrl(String value){ this.imageUrl = value; }
}
