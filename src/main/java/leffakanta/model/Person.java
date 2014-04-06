package leffakanta.model;

public class Person {
    private int personId;
    private String personName;    
    private String dateOfBirth;
    private String imageURL;
    
   //getters & setters
    public int getPerson_id(){ return this.personId; }
    public String getPerson_name(){ return this.personName; }
    public String getDate_of_birth(){ return this.dateOfBirth; }
    public String getImage_url(){ return this.imageURL; }

    public void setPerson_Id(int value){ this.personId = value; }
    public void setPerson_name(String value){ this.personName = value; }
    public void setDate_of_birth(String value){ this.dateOfBirth = value; }
    public void setImage_url(String value){ this.imageURL = value; }
}
