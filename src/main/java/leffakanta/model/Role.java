package leffakanta.model;

public class Role extends Person {
    private String production_role;    
    private String character_name;

    //getters & setters
    public String getProduction_role(){ return this.production_role; }
    public String getCharacter_name(){ return this.character_name; }

    public void setProduction_role(String value){ this.production_role = value; }    
    public void setCharacter_name(String value){ this.character_name = value; }
}
