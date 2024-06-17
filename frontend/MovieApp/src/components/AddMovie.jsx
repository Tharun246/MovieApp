import {React,useState,useEffect} from 'react'
import '../Styles/AddMovie.css'
const AddMovie = () => {
  const[Movie,setMovie]=useState("");
  const[Director,setDirector]=useState("");
  const[Genre,setGenre]=useState("");
  const[Year,setYear]=useState("");
  const[Poster,setPoster]=useState(null);


  const handleSubmit=async(event)=>
  { 
    event.preventDefault();
    const formData=new FormData();
    formData.append("title",Movie);
    formData.append("director",Director);
    formData.append("genre",Genre);
    formData.append("releaseYear",Year.split("-")[0]);
    formData.append("image",Poster);

    try {
      const resp=await fetch("http://localhost:8080/api/addMovie",{ 
        method:"POST",
        body:formData
      });
  
      const data = await resp.json();
      console.log('Movie added:', data);

    } catch (error) {
      console.log("unable to send the post request",error);
    }
  }
  return (
    <>
     <div className='addmovie-body'>

      <div className="page-title">
         <p>Enter the movie details</p>
      </div>

      {/* onSubmit={sendData} */}
      <form className="fields" onSubmit={handleSubmit}>

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
    {console.log(Movie)}
    </>
   
  )
}

export default AddMovie