package com.sullivankw.blackjackhelper.model;

public class User {

    private String username;
    private int highScore;
    private String created;

//    String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
//        profile.setCreated(timeStamp);


    public User(String username, int highScore, String created) {
        this.username = username;
        this.highScore = highScore;
        this.created = created;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
