package com.mycompany.apui;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity

public class Moive implements java.io.Serializable {

    @Id
    @Column(name = "MoiveName")
    private String moiveName;
  
    @Column(name = "CinemaLocation")
    private String cinemaLocation;
  
    @Column(name = "TimeSelectMovie")
    private String TimeSelectMovie;

   

  

    public Moive() {
        // Default constructor
    }

   

    public Moive(String moiveName, String cinemaLocation,String TimeSelectMovie) {
        this.moiveName = moiveName;
        this.cinemaLocation = cinemaLocation;
        this.TimeSelectMovie=TimeSelectMovie;
    }

    public String getMoiveName() {
        return moiveName;
    }

    public void setMoiveName(String moiveName) {
        this.moiveName = moiveName;
    }

    public String getCinemaLocation() {
        return cinemaLocation;
    }

    public void setCinemaLocation(String cinemaLocation) {
        this.cinemaLocation = cinemaLocation;
    }

    public String getTimeSelectMovie() {
        return TimeSelectMovie;
    }

    public void setTimeSelectMovie(String TimeSelectMovie) {
        this.TimeSelectMovie = TimeSelectMovie;
    }

    



   

 
}