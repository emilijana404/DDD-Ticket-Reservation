import axios from "axios";

const ticketAxios = axios.create({
    baseURL: 'http://localhost:9090/api',
    headers: {
        'Access-Control-Allow-Origin' : '*'
    }
});

const reservationAxios = axios.create({
    baseURL: 'http://localhost:9091/api',
    headers: {
        'Access-Control-Allow-Origin' : '*'
    }
});

export default {
    ticketAxios,
    reservationAxios
};