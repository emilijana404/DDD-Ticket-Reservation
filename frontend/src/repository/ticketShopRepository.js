import axios from '../custom-axios/axios';

const ticketShopService = {

    fetchTicket: () => {
        return axios.ticketAxios.get("/ticket");
    },
    fetchFormatType: () => {
        return axios.ticketAxios.get("/ticket/formatType");
    },
    fetchCurrency: () => {
        return axios.ticketAxios.get("/ticket/currency");
    },
    addTicket: (movieName, sales, movieFormat, time, date, currency, amount) => {
        return axios.ticketAxios.post("/ticket/add", {
            "movieName": movieName,
            "sales": sales,
            "movieFormat": movieFormat,
            "time": time,
            "date": date,
            "price": {
                "currency": currency,
                "amount": amount
            }
        });
    },
    getTicket: (id) => {
        return axios.ticketAxios.get(`/ticket/${id}`);
    }
};

export default ticketShopService;