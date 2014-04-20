package leffakanta.model;

public class Role extends Person {
    private String productionRole;    
    private String characterName;

    //getters & setters
    public String getProductionRole(){ return this.productionRole; }
    public String getCharacterName(){ return this.characterName; }

    public void setProductionRole(String value){ this.productionRole = value; }    
    public void setCharacterName(String value){ this.characterName = value; }
}
