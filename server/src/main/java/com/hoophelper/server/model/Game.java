package com.hoophelper.server.model;
import java.util.ArrayList;
import java.util.List;

public class Game {

    // For coords
    private record Point(float x, float y) {}

    // Data
    private Integer id;
    private User host;
    private List<Integer> players = new ArrayList<>();
    private Point location;
    private Integer format;
    private String time; // Should this be string?
    private Integer status; 
    // 0 = Waiting for players
    // 1 = full
    // 2 = complete

    // Getters
    public Integer getId() {
        return id;
    }

    public User getHost() {
        return host;
    }

    public List<Integer> getPlayers() {
        return players;
    }

    public Point getLocation() {
        return location;
    }

    public Integer getFormat() {
        return format;
    }

    public String getTime() {
        return time;
    }

    public Integer getStatus() {
        return status;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setHost(User host) {
        this.host = host;
    }

    public void setPlayers(List<Integer> players) {
        this.players = players;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public void setFormat(Integer format) {
        this.format = format;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setStatus(Integer status) {
        if (status >= 0 && status <= 2) { // Must be 0, 1, 2 (for the corresponding status above)
            this.status = status;
        }
    }

    // Helper methods for the Players ArrayList
    // Add Player
    public void addPlayer(Integer playerID) {
        players.add(playerID);
    }

    // Remove Player
    public void removePlayer(Integer playerID) {
        players.remove(playerID);
    }
}
