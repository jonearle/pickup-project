package com.hoophelper.server.repository;

import com.hoophelper.server.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GamePlayersRepository extends JpaRepository<Game, Integer> {
}