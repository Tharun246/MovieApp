package com.example.Movie.Controller;

import com.example.Movie.Dto.MovieDto;
import com.example.Movie.Model.Movie;
import com.example.Movie.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
@CrossOrigin("http://localhost:5173/")
public class MovieController
{
    @Autowired
    private MovieService movieService;

        @PostMapping("/addMovie")
    public ResponseEntity<?> addMovie(
            @RequestParam("title") String title,
            @RequestParam("director") String director,
            @RequestParam("genre") String genre,
            @RequestParam("releaseYear") String releaseYear,
            @RequestParam("image") MultipartFile image) throws IOException {

        MovieDto moviePosterDTO = new MovieDto();

        moviePosterDTO.setMovieName(title);
        moviePosterDTO.setDirector(director);
        moviePosterDTO.setMovieGenre(genre);
        moviePosterDTO.setReleaseYear(releaseYear);
        moviePosterDTO.setImageBase64(Base64.getEncoder().encodeToString(image.getBytes()));

        return movieService.addMovie(moviePosterDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateMovie(@RequestParam("title") String title,
               @RequestParam("director") String director,
               @RequestParam("genre") String genre,
               @RequestParam("releaseYear") String releaseYear,
               @RequestParam("image") MultipartFile image) throws IOException)
    {
        MovieDto moviePosterDTO = new MovieDto();
        moviePosterDTO.setMovieName(title);


    }
    @GetMapping(path = "/movies")
    public List<MovieDto> getAllMovies()
    {
        return movieService.getAllMovies();
    }

    @GetMapping(path = "/movies/{name}")
    public MovieDto getMovie(@PathVariable String name)
    {
        return movieService.getMovie(name);
    }

    @GetMapping("/data/{name}")
    public String sayHello(@PathVariable String name)
    {
        return "Hello "+name;
    }

    @DeleteMapping(path = "/movies")
    public String deleteMovie(@RequestParam String movie)
    {
        return movieService.delete(movie);
    }

}
