package com.example.video_store.controllers;

import com.example.video_store.models.TVShow;
import com.example.video_store.services.TVShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "https://jovial-custard-7ee4c1.netlify.app")
@RequestMapping("/api/tvshows")
public class TVShowController {
    @Autowired
    private TVShowService tvShowService;

    @PostMapping
    public ResponseEntity<TVShow> createTVShow(@RequestBody TVShow tvShow) {
        tvShow.setShowId(tvShowService.getAllTVShows().size() + 1);  
        return ResponseEntity.ok(tvShowService.createTVShow(tvShow));
    }

    @GetMapping
    public ResponseEntity<List<TVShow>> getAllTVShows() {
        return ResponseEntity.ok(tvShowService.getAllTVShows());
    }

    @GetMapping("/{showId}")
    public ResponseEntity<TVShow> getTVShowByShowId(@PathVariable int showId) {
        Optional<TVShow> tvShow = tvShowService.getTVShowByShowId(showId);
        return tvShow.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<TVShow>> getTVShowsByTitle(@RequestParam String title) {
        return ResponseEntity.ok(tvShowService.getTVShowsByTitle(title));
    }

    @GetMapping("/featured")
    public ResponseEntity<List<TVShow>> getFeaturedTVShows() {
        return ResponseEntity.ok(tvShowService.getFeaturedTVShows());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TVShow> updateTVShow(@PathVariable String id, @RequestBody TVShow tvShow) {
        tvShow.setId(id);
        return ResponseEntity.ok(tvShowService.updateTVShow(tvShow));
    }

    @DeleteMapping("/{showId}")
    public ResponseEntity<Void> deleteTVShowByShowId(@PathVariable int showId) {
        Optional<TVShow> tvShow = tvShowService.getTVShowByShowId(showId);
        if (tvShow.isPresent()) {
            tvShowService.deleteTVShow(tvShow.get().getId());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
}
