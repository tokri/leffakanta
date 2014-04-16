package leffakanta.model;

import static org.springframework.web.util.HtmlUtils.htmlEscape;
import static org.springframework.web.util.HtmlUtils.htmlUnescape;

public class RoleAndMovie extends Role {
    private int movie_id;
    private String movie_title;
    private String year;

    //getters & setters
    public int getMovie_id(){ return this.movie_id; }
    public String getMovie_title(){ return htmlEscape(this.movie_title); }
    public String getYear(){ return this.year; }

    public void setMovie_id(int value){ this.movie_id = value; }    
    public void setMovie_title(String value){ this.movie_title = htmlUnescape(value); }
    public void setYear(String value){ this.year = value; }    
}
