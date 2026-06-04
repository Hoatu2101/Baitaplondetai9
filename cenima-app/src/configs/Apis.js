import axios from "axios";
import cookies from "react-cookies";

const BASE_URL = "http://localhost:8080/CenimaApp/api";

export const endpoints = {
    'movies': "/movies",
    'detail_movie': (id) => `/movies/${id}`,
    'categories': "/categories",
    'register': "/users",
    'login': "/login",
    'profile': "/secure/profile",

    'movie_showtimes': (movieId) => `/movies/${movieId}/showtimes`,

    'cenimas': "/cenimas",

    'seats': (showtimeId) => `/showtimes/${showtimeId}/seats`,
    'booking': '/bookings'
};

export const authApis = () => {
    const token = cookies.load("token");

    return axios.create({
        baseURL: BASE_URL,
        headers: token
            ? {
                  Authorization: `Bearer ${token}`,
              }
            : {},
    });
};

export default axios.create({
    baseURL: BASE_URL,
});