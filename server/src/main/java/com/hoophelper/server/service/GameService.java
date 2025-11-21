package com.hoophelper.server.service;

import java.util.List;

import javax.naming.NameNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.hoophelper.server.model.Game;
import com.hoophelper.server.repository.GameRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    public Page<Game> getPage(int pageNumber, int gamesOnPage) {
        // Sanitize data
        if (pageNumber <= 0) 
            pageNumber = 0;
        if (gamesOnPage <= 0)
            gamesOnPage = 15;
        
        Page<Game> page = gameRepository.findAllByOrderByGameTimeDesc(PageRequest.of(pageNumber, gamesOnPage));
        if (page.isEmpty())
            throw new RuntimeException("Page is empty");
        else 
            return page;
    }

    public Game getGame(int id) {
        try {
            Game game = gameRepository.getReferenceById(id);
            return game;
        } catch (EntityNotFoundException e) {
            System.out.println(e + ": Could not find game/incorrect ID");
            return null;
        }
    }

    public Game createGame(Game game) {
        
    }
}
