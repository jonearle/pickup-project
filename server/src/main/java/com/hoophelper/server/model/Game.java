package com.hoophelper.server.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="game_id", nullable=false, unique=true)
    private Integer game_id;

    @Column(name="format", nullable=false)
    private Integer format;

    @Column(name="time", nullable=false)
    private LocalDateTime time;

    @Column(name="status", nullable=false)
    private Integer status; 
    // 0 = Waiting for players
    // 1 = full
    // 2 = complete

    @Column(name="latitude", nullable=false)
    private Double latitude;

    @Column(name="longitude", nullable=false)
    private Double longitude;

    // *** 

    public Game() {}

    public Integer getGame_id() {
        return game_id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Integer getFormat() {
        return format;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public Integer getStatus() {
        return status;
    }

    public void setGame_id(Integer game_id) {
        this.game_id = game_id;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setFormat(Integer format) {
        this.format = format;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public void setStatus(Integer status) {
        if (status >= 0 && status <= 2) { // Must be 0, 1, 2 (for the corresponding status above)
            this.status = status;
        }
    }
}
