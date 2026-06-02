import axios from "axios";
import cookies from 'react-cookies'

export const endpoints = {
   'movies': '/movies',
   'detail_movie': (id) => `/movies/${id}`,
   'categories': '/categories',
   'register': '/users',
   'login': '/login',
   'current_user':'/secure/profile',
}

export const authApis = () => {
    console.info(cookies.load('token'))
    return axios.create({
        baseURL: "http://localhost:8080/CenimaApp/api",
        headers: {
            'Authorization': `Bearer ${cookies.load('token')}`
        }

    })
}

export default axios.create({
    baseURL: "http://localhost:8080/CenimaApp/api"
})