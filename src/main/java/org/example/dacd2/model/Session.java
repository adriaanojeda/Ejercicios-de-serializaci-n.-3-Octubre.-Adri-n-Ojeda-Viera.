package org.example.dacd2.model;

import java.io.Serializable;

public class Session implements Serializable {
    private Movie movie;
    private Theater theater;
    private String start;

    public Session(Movie movie, Theater theater, String start) {
        this.movie = movie;
        this.theater = theater;
        this.start = start;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    @Override
    public String toString() {
        return "Session{" +
                "movie=" + movie +
                ", theater=" + theater +
                ", start='" + start + '\'' +
                '}';
    }
}