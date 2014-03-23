package leffakanta.model;

import java.util.List;

public class Movie {
    private int id;
    private String title;
    private int year;
    private int runtime;
    private float rating;
    private String plotText;
    private String posterUrl;
    private String backgroundUrl;
    private String trailerUrl;
    public List<Role> actors;
    public List<String> directors;
    public List<String> writers;
    public List<String> producers;
    
    //getters & setters needed for autowiring
    public int getMovie_id(){ return this.id; }
    public String getMovie_title(){ return this.title; }
    public int getYear(){ return this.year; }
    public int getRuntime(){ return this.runtime; }
    public float getRating(){ return this.rating; }
    public String getPlot_text(){ return this.plotText; }
    public String getPoster_url(){ return this.posterUrl; }
    public String getBackground_url(){ return this.backgroundUrl; }
    public String getTrailer_url(){ return this.trailerUrl; }
    
    public void setMovie_id(int value){ this.id = value; }
    public void setMovie_title(String value){ this.title = value; }
    public void setYear(int value){ this.year = value; }
    public void setRuntime(int value){ this.runtime = value; }
    public void setRating(float value){ this.rating = value; }
    public void setPlot_text(String value){ this.plotText = value; }
    public void setPoster_url(String value){ this.posterUrl = value; }
    public void setBackground_url(String value){ this.backgroundUrl = value; }
    public void setTrailer_url(String value){ this.trailerUrl = value; }
}
