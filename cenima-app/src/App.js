import { BrowserRouter, Routes, Route } from "react-router-dom";
import MovieDetails from "./screens/home/movie_detail"; // Gọi thêm MovieDetails thật
import Header from "./components/Header"; 
import Footer from "./components/Footer"; // Gọi thêm Footer thật
import Home from "./screens/home/Home"; // Gọi thêm Home thật
import Login from "./screens/User/Login";
import Register from "./screens/User/Register";
import 'bootstrap/dist/css/bootstrap.min.css';
import  {MyUserContext}  from "./configs/context";
import  MyUserReducer  from "./reducers/MyUserReducers";
import cookies from "react-cookies";
import { useEffect, useReducer } from "react";
const App = () => {
  const [user, dispatch] = useReducer(MyUserReducer, cookies.load('user') || null);
  return (
     <MyUserContext.Provider value={[user, dispatch]}>
    <BrowserRouter>
      <Header />

      <Routes>
        <Route 
          path="/" 
          element={<Home/>
          } 
        /> 
          <Route path="/Home" element={<Home />} />
          <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
      
        <Route path="/movies/:movieId" element={<MovieDetails/>} />
      </Routes>
        

      <Footer />

    </BrowserRouter>
    </MyUserContext.Provider>
  );
};

export default App;