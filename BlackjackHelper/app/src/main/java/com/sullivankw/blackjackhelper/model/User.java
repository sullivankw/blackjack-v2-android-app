package com.sullivankw.blackjackhelper.model;

public class User {

    private String username;
    private int highScore;
    private String created;
    private String id;
    private String displayCreated;

    public User(String username, int highScore, String created, String id, String displayCreated) {
        this.username = username;
        this.highScore = highScore;
        this.created = created;
        this.id = id;
        this.displayCreated = displayCreated;
    }

    //firebase requirement
    public User() { }

    public String getUsername() {
        return username;
    }

    public int getHighScore() {
        return highScore;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayCreated() {
        return displayCreated;
    }

}
