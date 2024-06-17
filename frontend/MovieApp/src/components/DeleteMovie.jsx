import { React,useState } from 'react';
import '../Styles/Delete.css'
const DeleteMovie = () => {
  
  const [Name,setName]=useState("");
  const [Selected,setSelected]=useState(false);
  const [Response,setResponse]=useState("");
  const handleForm=async(event)=>
  {

    event.preventDefault(); 
    
    const formData=new FormData();
    formData.append("movie",Name);
    
      try
      {
        {console.log(Selected)}
        if(Selected=="Yes")
          {
            const resp=await fetch("http://localhost:8080/api/movies",
              { 
              method:"DELETE",
              body:formData
            })
            const resp_data=await resp.text();
            console.log(resp_data);
            setResponse(resp_data);
          }
          else 
          { 
           setResponse("Delete operation is stopped ")
          }
      }
      catch(error)
      { 
        console.log("cannot process the request");
      }
  }
  return (
    <div className="delete-body" >

        <p className="component-title">Delete Movie</p>
        <form className='delete-form' onSubmit={handleForm}>

            <div className="movie-name-field">
              <label htmlFor="movieName">Movie name</label>
              <input type="text" placeholder='enter movie name' name='movieName' onChange={(e)=>setName(e.target.value)}/>
            </div>

            <div className="confirmation">
                <label htmlFor="confirm">Are you sure you want to delete the movie</label>
                <div className="icons">
                    <input type="radio" name="confirm" id="Yes" onClick={(e)=>setSelected("Yes")} />
                    <label htmlFor="Yes">Yes</label>
                    <input type="radio" name="confirm" id="No" onClick={(e)=>setSelected("No")}/>
                    <label htmlFor="No">No</label>
                </div>
            </div>
            <button className="delete">Delete</button>
            <p>You enetered : {Name}</p>
            <p>And clicked on : {Selected}</p>
            <p>Response after you click delete : {Response}</p>
        </form>
    </div>
  )
}

export default DeleteMovie