import AddMovie from './components/AddMovie.jsx'
import DeleteMovie from './components/DeleteMovie.jsx'
import Movie from './components/Movie.jsx'
import Nav from './components/Nav.jsx'
import UpdateMovie from './components/UpdateMovie.jsx'
import {  BrowserRouter as Router ,Routes,Route} from 'react-router-dom'

function App() {

  return (
    <>
    <Router>
        <div>
            <Nav />
            <Routes>
              <Route exact path="/all" element={<Movie />} />
              <Route exact path="/add" element={<AddMovie />} />
              <Route exact path="/delete" element={<DeleteMovie />} />
              <Route exact path="/update" element={<UpdateMovie />} />
            </Routes>
        </div>
    </Router>
    </>
  ) 
}

export default App
