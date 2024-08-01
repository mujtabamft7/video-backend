package com.example.video_store.services;

import com.example.video_store.models.TVShow;
import com.example.video_store.repositories.TVShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TVShowService {
    @Autowired
    private TVShowRepository tvShowRepository;

    public TVShow createTVShow(TVShow tvShow) {
        return tvShowRepository.save(tvShow);
    }

    public List<TVShow> getAllTVShows() {
        return tvShowRepository.findAll();
    }

    public Optional<TVShow> getTVShowById(String id) {
        return tvShowRepository.findById(id);
    }

    public Optional<TVShow> getTVShowByShowId(int showId) {
        return tvShowRepository.findByShowId(showId);
    }

    public List<TVShow> getTVShowsByTitle(String title) {
        return tvShowRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<TVShow> getFeaturedTVShows() {
        return tvShowRepository.findByIsFeatured(true);
    }

    public TVShow updateTVShow(TVShow tvShow) {
        return tvShowRepository.save(tvShow);
    }

    public void deleteTVShow(String id) {
        tvShowRepository.deleteById(id);
    }
}
