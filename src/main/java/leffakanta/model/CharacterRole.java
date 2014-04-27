package leffakanta.model;

public class CharacterRole {    
    private int characterId;
    private String characterName;
    
    // add a character
    public int addCharacter(String characterName){
        String sql = "INSERT INTO character VALUES (DEFAULT,?) RETURNING character_id";
        int retVal = Database.queryForInt(sql, characterName);
        return retVal;
    }
    
    // delete a character
    public void deleteCharacter(int characterId){
        String sql = "DELETE FROM \"character\" WHERE character_id = ?";
        Database.update(sql, characterId);        
    }
    
    // get character
    public CharacterRole getCharacter(int characterId){
        String sql = "SELECT * FROM \"character\" WHERE character_id = ?";
        CharacterRole character = Database.queryForObject(sql, characterId, CharacterRole.class);
        return character;
    }

    //getters & setters
    public int getCharacterId(){ return this.characterId; }
    public String getCharacterName(){ return this.characterName; }
    public void setCharacterId(int value){ this.characterId = value; }
    public void setCharacterName(String value){ this.characterName = value; }
}
