package com.hoophelper.server.repository;

import com.hoophelper.server.model.Game;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;


public interface GameRepository extends JpaRepository<Game, Integer> {
    // Need pageable to do LIMIT and OFFSET
    Page<Game> findAllByOrderByGameTimeDesc(Pageable pageable);
}

