package com.example.video_store.repositories;

import com.example.video_store.models.TVShow;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TVShowRepository extends MongoRepository<TVShow, String> {
    List<TVShow> findByTitleContainingIgnoreCase(String title);
    List<TVShow> findByIsFeatured(boolean isFeatured);
    Optional<TVShow> findByShowId(int showId); 
}
