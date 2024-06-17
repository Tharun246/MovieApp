package com.example.Movie.Dto;

import com.example.Movie.Model.Poster;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class MovieDto
{
        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        private Long id;

        private String MovieName;
        private String MovieGenre;
        private String Director;
        private String ReleaseYear;

        private String imageBase64;

}
