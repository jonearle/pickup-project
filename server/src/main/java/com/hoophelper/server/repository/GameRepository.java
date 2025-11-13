package com.hoophelper.server.repository;

import com.hoophelper.server.model.Game;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Integer> {
    // Need pageable to do LIMIT and OFFSET
    List<Game> findAllByOrderByGameTimeDesc(Pageable pageable);
}

