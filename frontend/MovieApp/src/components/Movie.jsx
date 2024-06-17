import React from 'react'
import { useState,useEffect } from 'react'
import '../Styles/Movie.css'
const Movie = () => {  
    const[Data,setData]=useState([]);

    let url="http://localhost:8080/api/movies";
    const getAllMovies = async () => {       
        const resp=await fetch(url);
        const resp_data=await resp.json();

        console.log(resp_data);
        setData(resp_data);
    };
    useEffect(()=>{
        getAllMovies();
    },[])
  return (
    
    <div className="body-content" >
        {Data && Data.map(movie=>(
                <div className="movie-card">
                        <div key={movie.movieName} className="movie-poster">
                        <img  src={`data:image/jpeg;base64,${movie.imageBase64}`} alt={`${movie.movieName} poster`} className='movie-poster'/>
                        </div>

                        <div key={movie.movieName} className="data movie-name-director">
                            <p>{movie.movieName}</p>
                            <p>{movie.director}</p>
                        </div>

                        <div key={movie.movieName} className='data movie-year-genre'>
                            <p>{movie.releaseYear}</p>
                            <p>{movie.movieGenre}</p>           
                        </div>
                </div>
        ))}
    </div>
  )
}

export default Movie