package com.hoophelper.server.model;

public class User {
    // Data fields
    private int id;
    private String username;
    private String password;
    private int ranking;

    // Constructor
    public User() {}

    // Getters and setters
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getRanking() {
        return ranking;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }
}
