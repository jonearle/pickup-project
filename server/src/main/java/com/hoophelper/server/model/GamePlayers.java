package com.hoophelper.server.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="game_players")
public class GamePlayers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer gpID;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name="game_id", nullable=false)
    private Game game;

    @Column(name="is_host")
    private boolean isHost;

    // ***

    public GamePlayers() {}

    public Integer getGpID() {
        return gpID;
    }

    public void setGpID(Integer gpID) {
        this.gpID = gpID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Game getGame() {
        return game;
    }   

    public void setGame(Game game) {
        this.game = game;
    }

    public boolean checkHost(int gameID) {
        return isHost;
    }

    public void setHost(boolean status) {
        isHost = status;
    }
}
