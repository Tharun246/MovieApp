package com.example.Movie.Service;

import com.example.Movie.Dto.MovieDto;
import com.example.Movie.Model.Movie;
import com.example.Movie.Model.Poster;
import com.example.Movie.Repository.MovieRepo;
import com.example.Movie.Repository.PosterRepo;
import com.example.Movie.Utility.PosterUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService
{

    @Autowired
    private MovieRepo movieRepo;
    @Autowired
    private PosterRepo posterRepo;

    private MovieDto convertToMovieDTO(Movie movie) {
        MovieDto movieDto=new MovieDto();

        movieDto.setId(movie.getMovieId());
        movieDto.setMovieName(movie.getMovieName());
        movieDto.setMovieGenre(movie.getMovieGenre());
        movieDto.setDirector(movie.getDirector());
        movieDto.setReleaseYear(movie.getReleaseYear());

        return movieDto;
    }

    public ResponseEntity<?> addMovie(MovieDto moviePosterDTO)  {

        Optional<Movie> m=movieRepo.findByMovieName(moviePosterDTO.getMovieName());
        if(m.isPresent())
        {
          return new ResponseEntity<>("already exists",HttpStatus.CONFLICT);
        }
        Movie movie = new Movie();
        movie.setMovieName(moviePosterDTO.getMovieName());
        movie.setDirector(moviePosterDTO.getDirector());
        movie.setMovieGenre(moviePosterDTO.getMovieGenre());
        movie.setReleaseYear(moviePosterDTO.getReleaseYear());

        Poster poster = new Poster();
        poster.setPosterData(Base64.getDecoder().decode(moviePosterDTO.getImageBase64()));
        movie.setPoster(poster);
        Movie savedMovie = movieRepo.save(movie);

        moviePosterDTO.setId(savedMovie.getMovieId());
        movieRepo.save(movie);

        return new ResponseEntity<>(moviePosterDTO,HttpStatus.OK);
    }

    public List<MovieDto> getAllMovies() {
        return movieRepo.findAll().stream().map(movie -> {
            MovieDto dto = new MovieDto();

            dto.setMovieName(movie.getMovieName());
            dto.setDirector(movie.getDirector());
            dto.setMovieGenre(movie.getMovieGenre());
            dto.setReleaseYear(movie.getReleaseYear());

            if (movie.getPoster() != null) {
                dto.setImageBase64(Base64.getEncoder().encodeToString(movie.getPoster().getPosterData()));
            }
            return dto;
        }).collect(Collectors.toList());
    }


    public MovieDto getMovie(String name)
    {
        Optional<Movie> movie= movieRepo.findByMovieName(name);
        Movie existing=movie.get();

        MovieDto movieDto=new MovieDto();

        movieDto.setId(existing.getMovieId());
        movieDto.setMovieName(existing.getMovieName());
        movieDto.setDirector(existing.getDirector());
        movieDto.setMovieGenre(existing.getMovieGenre());
        movieDto.setImageBase64(Base64.getEncoder().encodeToString(existing.getPoster().getPosterData()));

        return movieDto;
    }

    public String delete(String name)
    {
        Optional<Movie> movieToBeDeleted=movieRepo.findByMovieName(name);
        if(movieToBeDeleted.isPresent()) {
            String movieName = movieToBeDeleted.get().getMovieName();
            movieRepo.delete(movieToBeDeleted.get());
            return movieName+" is deleted successfully";
        }
        return "no such movie exist in the database";
    }

    public ResponseEntity<?> updateMovie(MovieDto movieDto)
    {
        Optional<Movie> existing=movieRepo.findByMovieName(movieDto.getMovieName());
        if(existing.isPresent())
        {
            Movie movie=existing.get();

            movie.setMovieName(movie.getMovieName());
            movie.setDirector(movie.getDirector());
            movie.setReleaseYear(movie.getReleaseYear());
            movie.setMovieGenre(movie.getMovieGenre());

            Poster poster=existing.get().getPoster();
            poster.setPosterData(Base64.getDecoder().decode(movieDto.getImageBase64()));
            movie.setPoster(poster);
            posterRepo.save(poster);

            Movie savedMovie = movieRepo.save(movie);

            movieDto.setId(savedMovie.getMovieId());
            movieRepo.save(movie);

            return new ResponseEntity<>(movieDto,HttpStatus.OK);
        }
        return new ResponseEntity<>("movie does not exist",HttpStatus.NOT_FOUND);
    }
}
