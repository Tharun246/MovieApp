package com.example.Movie.Repository;

import com.example.Movie.Dto.MovieDto;
import com.example.Movie.Model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepo extends JpaRepository<Movie,Long>
{
    Optional<Movie> findByMovieName(String movieName);

}
