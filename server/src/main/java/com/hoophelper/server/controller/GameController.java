package com.hoophelper.server.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hoophelper.server.model.Game;

@RestController
public class GameController {
    // In memory list (for now)
    private ConcurrentHashMap<Integer, Game> tempDB = new ConcurrentHashMap<>();
    private AtomicInteger idCounter = new AtomicInteger(1);

    // Load games
    @GetMapping("/games")
    public ResponseEntity<List<Game>> getPage(@RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "16") int gamesOnPage) {
        // Sanitize data
        if (pageNumber <= 0) 
            pageNumber = 0;
        if (gamesOnPage <= 0)
            gamesOnPage = 16;
        
        List<Game> allGames = new ArrayList<>(tempDB.values()); // snapshot

        // Sort all games by time
        allGames.sort(Comparator.comparing( // Ordered by time, null values at the end
            (Game g) -> g.getTime(), 
            Comparator.nullsLast(Comparator.naturalOrder())
        ));

        // Get the page indices
        int startIndex = pageNumber * gamesOnPage;
        if (startIndex >= allGames.size()) {
            return ResponseEntity.ok(List.of()); // page past end creates an empty list
        }
        int endIndex = Math.min(startIndex + gamesOnPage, allGames.size()); // Math.min() will pick the smallest of the 2 numbers making sure it never exceeds the limit

        // New list with only the needed game (already sorted)
        List<Game> someGames = allGames.subList(startIndex, endIndex);
        
        return ResponseEntity.ok(someGames);
    }


    // Get one game
    @GetMapping("/games/{id}")
    public ResponseEntity<Game> getGame(@PathVariable Integer id) {
        if (tempDB.containsKey(id) == false)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(tempDB.get(id));
    }
    
    // Create game
    @PostMapping("/games")
    public ResponseEntity<Game> newGame(@RequestBody Game game) {
        // Set id
        game.setId(idCounter.getAndIncrement());

        // Add to DB
        tempDB.put(game.getId(), game);

        // Set host?

        return ResponseEntity.status(HttpStatus.CREATED).body(game);
    }

    // Sign up for a game
    @PostMapping("/games/{id}/players")
    public ResponseEntity<Game> joinGame(@PathVariable Integer id, @RequestBody Integer playerID) {
        // Check if id is valid
        Game currGame;
        if (tempDB.containsKey(id)) {
            // Find game and player to list
            currGame = tempDB.get(id);
            currGame.addPlayer(playerID);

            // Return game state
            return ResponseEntity.ok(currGame);
        }
        
        else 
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Delete a game
    @DeleteMapping("/games/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Integer id) {
        // Check if id is valid
        if (tempDB.containsKey(id)) {
            tempDB.remove(id); // Remove player
            return ResponseEntity.noContent().build(); // 204 successfully deleted
        }
        else
            return ResponseEntity.notFound().build(); // 404 not found
        
    }

    // Leave a game
    @DeleteMapping("/games/{id}/players/{playerID}")
    public ResponseEntity<?> leaveGame(@PathVariable Integer id, @PathVariable Integer playerID) {
        // Check if id is valid
        if (tempDB.containsKey(id)) {
            // Get game from db
            Game currGame = tempDB.get(id);

            // Check if player is host. If so, delete the game
            if (Objects.equals(currGame.getHost().getId(), playerID)) {
                deleteGame(id);
                return ResponseEntity.noContent().build(); // successfully deleted
            }

            // Remove player from game
            currGame.removePlayer(playerID);

            return ResponseEntity.ok(currGame);
            
        }
        
        else 
            return ResponseEntity.notFound().build();
    }
}
