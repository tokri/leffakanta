package leffakanta.model;

import static org.springframework.web.util.HtmlUtils.htmlEscape;
import static org.springframework.web.util.HtmlUtils.htmlUnescape;

public class RoleAndMovie extends Role {
    private int movieId;
    private String movieTitle;
    private String year;

    //getters & setters
    public int getMovieId(){ return this.movieId; }
    public String getMovieTitle(){ return htmlEscape(this.movieTitle); }
    public String getYear(){ return this.year; }

    public void setMovieId(int value){ this.movieId = value; }    
    public void setMovieTitle(String value){ this.movieTitle = htmlUnescape(value); }
    public void setYear(String value){ this.year = value; }    
}
