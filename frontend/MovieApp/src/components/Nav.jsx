import React, { useState,useEffect } from 'react'
import { Link } from "react-router-dom";
import '../Styles/Navbar.css'
import { Search } from 'lucide-react'
import Movie from './Movie';

const Nav = () => {

    const[Data,setData]=useState([]);
    const [Search,setSearch]=useState(" ");
    const [resp_data,setResp]=useState([]);
    let url="http://localhost:8080/api/movies";

    const searchTerm=async(e)=>{
            setSearch(e.target.value);
    }

    const getAllMovies = async () => {       
        const resp=await fetch(url);
        resp_data=await resp.json();
        setResp(resp_data);
        console.log(resp_data);
    };
    const handleCall=()=>{
        setData(resp_data);
    }
  

    // useEffect(()=>{ 
    //         getAllMovies();
    // },[])
     
  return (

    <div className="content">

        <div className='navbar-container'>
            <Link className='links'  to="/" >
                <p className='greet'>Welcome Home</p>
            </Link>
            <p className='title'>MovieLand</p>
            <div className="search-area">
                <input type="text" className='searchBar' onChange={searchTerm}/>
                <button className="click-btn">Search</button>
            </div>
        </div>
        <div className="bottom-body">
            <div className="options" >
                <button onClick={()=>handleCall()}><Link className='links' to="/all" style={{textDecoration:"none",color:"black"}}>All movies</Link></button>
                <button><Link className='links' to="/add" style={{textDecoration:"none",color:"black"}}>Add Movie</Link></button>
                <button><Link className='links' to="/delete">Delete Movie</Link></button>
                <button><Link className='links' to="/update">Update Movie</Link></button>
            </div>
        </div>
    </div>
  )
}

export default Nav