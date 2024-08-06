package com.example.video_store.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "movies")
@Data
public class Movie {
    @Id
    private String id;
    private int movieId;  
    private String title;
    private String year;
    private List<String> genres;
    private double rating;
    private String plot;
    private String posterUrl;
    private double rent;
    //
    private double buy;
    private String trailerUrl;
    private boolean isFeatured;
}
