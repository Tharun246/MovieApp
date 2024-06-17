package com.example.Movie.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MovieInfo")
public class Movie
{
        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        private Long movieId;

        @Column(name = "Name")
        private String movieName;

        @Column(name="Genre")
        private String movieGenre;

        private String director;

        @Column(name ="Year")
        private String releaseYear;

        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "poster_id", referencedColumnName = "PosterId")
        private Poster poster;

}
