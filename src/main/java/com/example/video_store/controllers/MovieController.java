package com.example.video_store.controllers;

import com.example.video_store.models.Movie;
import com.example.video_store.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        movie.setMovieId(movieService.getAllMovies().size() + 1);  // Assign movieId
        return ResponseEntity.ok(movieService.createMovie(movie));
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<Movie> getMovieByMovieId(@PathVariable int movieId) {
        Optional<Movie> movie = movieService.getMovieByMovieId(movieId);
        return movie.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Movie>> getMoviesByTitle(@RequestParam String title) {
        return ResponseEntity.ok(movieService.getMoviesByTitle(title));
    }
    

    @GetMapping("/featured")
    public ResponseEntity<List<Movie>> getFeaturedMovies() {
        return ResponseEntity.ok(movieService.getFeaturedMovies());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable String id, @RequestBody Movie movie) {
        movie.setId(id);
        return ResponseEntity.ok(movieService.updateMovie(movie));
    }

    @DeleteMapping("/{movieId}")
public ResponseEntity<Void> deleteMovieByMovieId(@PathVariable int movieId) {
    Optional<Movie> movie = movieService.getMovieByMovieId(movieId);
    if (movie.isPresent()) {
        movieService.deleteMovie(movie.get().getId());
        return ResponseEntity.noContent().build();
    } else {
        return ResponseEntity.notFound().build();
    }
}

}
