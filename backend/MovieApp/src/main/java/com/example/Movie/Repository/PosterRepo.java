package com.example.Movie.Repository;

import com.example.Movie.Model.Poster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PosterRepo extends JpaRepository<Poster,Long>
{
}
