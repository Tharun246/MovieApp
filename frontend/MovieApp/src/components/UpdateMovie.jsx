import React from 'react'

const UpdateMovie = () => {
  return (
    <div className='update-movie' style={{margin:"8rem",border:"2px solid black"}}>
        <div className="page-title">
            <p>Enter the movie details</p>
        </div>

        <form className="updated-details">
        <div className="info">
              <input className='field' type="text" placeholder='name' onChange={(e) => setMovie(e.target.value)}/>
              <input className='field' type="text" placeholder='director' onChange={(e) => setDirector(e.target.value)}/>
            </div>

            <div className="info">
                <input className='field' type="date" placeholder='year' onChange={(e) => setYear(e.target.value)}/>
                <input className='field' type="text" placeholder='genre' onChange={(e) => setGenre(e.target.value)}/>
            </div>
            
            <input className='poster' type="file" name="poster" id="" placeholder='upload a file here' onChange={(e) => setPoster(e.target.files[0])}/>
            <button style={{maxWidth:"90px",backgroundColor:"lightGreen",marginLeft:"Auto",marginRight:"Auto",cursor:"pointer",border:"1px solid black",fontFamily:"monospace",fontSize:"15px",fontWeight:"bold"}}>Add Movie</button>

        </form>
    </div>
  )
}

export default UpdateMovie