package leffakanta.model;

public class Genre {
    private int genreId;
    private String genreName;
    
    // add genre
    public int addGenre(String genreName, int movieId){
        Genre genre = getGenre(genreName);
        int genreId;
        String sql;
        
        if (genre != null){
            // if old genre found, use it's id
            genreId = genre.getGenreId();
        } else {
            // otherwise make new genre and get new genreId in return
            sql = "INSERT INTO genre VALUES (DEFAULT,?) RETURNING genre_id";    
            genreId = Database.queryForInt(sql, genreName);
        }            
        sql = "INSERT INTO movie_genre VALUES (?,?)";    
        Database.update(sql, new Object[]{ movieId, genreId});        
        return genreId;
    }

    public Genre getGenre(String genreName){
        String sql = "SELECT * FROM genre WHERE genre_name = ?";
        Genre genre = Database.queryForObject(sql, genreName, Genre.class);
        return genre;        
    }

    // remove genre from movie
    public void removeGenre(int genreId, int movieId){
        String sql = "DELETE FROM movie_genre WHERE genre_id = ? AND movie_id = ?";
        Database.update(sql, new Object[]{ genreId, movieId});
        
        sql = "SELECT COUNT(*) FROM movie_genre WHERE genre_id = ?";
        int count = Database.queryForInt(sql, genreId);
        
        // if genre doesn't exist in any movie anymore, delete it
        if (count == 0){
            sql = "DELETE FROM genre WHERE genre_id = ?";
            Database.update(sql, genreId);
        }
    }
    
    
    //getters & setters
    public int getGenreId(){ return this.genreId; }
    public String getGenreName(){ return this.genreName; }
    public void setGenreId(int value){ this.genreId = value; }
    public void setGenreName(String value){ this.genreName = value; }
}
