import './App.css';
import React, {Component} from "react";
import {BrowserRouter as Router, Route, Routes, Navigate} from "react-router-dom";
import Tickets from "../Ticket/TicketList/tickets";
import Header from '../Header/header';
import TicketAdd from '../Ticket/TicketAdd/ticketAdd';
import ticketShopService from "../../repository/ticketShopRepository";

class App extends Component {

    constructor(props) {
        super(props);

        this.state = {
            tickets: [],
            formatTypes: [],
            currency: [],
            selectedTicket: {}
        }
    }

    render() {
        return (
            <Router>
                <Header/>
                <main>
                    <Routes>
                        <Route path={"/tickets"} exact element={<Tickets ticket={this.state.tickets}/>}/>
                        <Route path={"/tickets/add"} exact
                               element={<TicketAdd ticket={this.state.tickets} onAddTicket={this.addTicket}
                                                   movieFormat={this.state.movieFormat}
                                                   currency={this.state.currency}/>}/>
                        <Route path={"/"} element={<Navigate replace to={"/tickets"}/>}/>
                    </Routes>
                </main>
            </Router>
        );
    }

    componentDidMount() {
        this.loadTicket();
        this.loadFormatType();
        this.loadCurrency();
    }

    loadTicket = () => {
        ticketShopService.fetchTicket()
            .then((data) => {
                this.setState({
                    tickets: data.data
                })
            });
    };

    loadFormatType = () => {
        ticketShopService.fetchFormatType()
            .then((data) => {
                this.setState({
                    movieFormat: data.data
                })
            });
    };

    loadCurrency = () => {
        ticketShopService.fetchCurrency()
            .then((data) => {
                this.setState({
                    currency: data.data
                })
            });
    };

    addTicket = (movieName, sales, movieFormat, time, date, currency, amount) => {
        ticketShopService.addTicket(movieName, sales, movieFormat, time, date, currency, amount).then(() => {
            window.location.href = "/tickets"
        });
    };

    getTicket = (id) => {
        ticketShopService.getTicket(id)
            .then((data) => {
                this.setState({
                    selectedTicket: data.data
                })
            })
    };
}

export default App;