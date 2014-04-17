package leffakanta.model;

import leffakanta.service.DbService;

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
       
    // update person details
    public void updatePerson(Person person){
        String sql = "UPDATE people SET person_name=?, date_of_birth=?, image_url=? WHERE person_id=?";
        DbService.update(sql, new Object[]{ person.getPerson_name(), person.getDate_of_birth(), person.getImage_url(), person.getPerson_id()}); 
    }
    
    //getters & setters
    public int getPerson_id(){ return this.person_id; }
    public String getPerson_name(){ return this.person_name; }
    public String getDate_of_birth(){ return null; }
    public String getImage_url(){ return this.image_url; }

    public void setPerson_id(int value){ this.person_id = value; }
    public void setPerson_name(String value){ this.person_name = value; }
    public void setDate_of_birth(String value){ this.date_of_birth = value; }
    public void setImage_url(String value){ this.image_url = value; }
}
