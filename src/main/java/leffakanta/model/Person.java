package leffakanta.model;

import org.hibernate.validator.constraints.URL;

public class Person {
    private int personId;
    private String personName;
    @URL
    private String imageUrl;
    

    // add person
    public int addPerson(String personName){
        String sql = "INSERT INTO person VALUES (DEFAULT,?,null) RETURNING person_id";
        int retVal = Database.queryForInt(sql, personName);
        return retVal;
    }
    
    // get person's details by id
    public Person getPerson(int personId){        
        // get basic movie details
        String sql = "SELECT * FROM person WHERE person_id = ?";
        Person person = Database.queryForObject(sql, personId, Person.class);
        return person;
    }
    
    // get person's details by name
    public Person getPerson(String personName){        
        // get basic movie details
        String sql = "SELECT * FROM person WHERE personName = ?";
        Person person = Database.queryForObject(sql, personName, Person.class);
        return person;
    }
       
    // update person details
    public void updatePerson(Person person){
        String sql = "UPDATE person SET person_name=?, image_url=? WHERE person_id=?";
        Database.update(sql, new Object[]{ person.getPersonName(), person.getImageUrl(), person.getPersonId()}); 
    }

    // delete person
    public void deletePerson(int person_id){
        String sql = "DELETE FROM person WHERE person_id = ?";
        Database.update(sql, person_id);
    }
    
    //getters & setters
    public int getPersonId(){ return this.personId; }
    public String getPersonName(){ return this.personName; }
    public String getImageUrl(){ return this.imageUrl; }

    public void setPersonId(int value){ this.personId = value; }
    public void setPersonName(String value){ this.personName = value; }
    public void setImageUrl(String value){ this.imageUrl = value; }
}
