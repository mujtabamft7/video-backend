package com.example.video_store.repositories;

import com.example.video_store.models.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {
    List<Movie> findByTitleContainingIgnoreCase(String title);
    List<Movie> findByIsFeatured(boolean isFeatured);
    Optional<Movie> findByMovieId(int movieId); 
}
