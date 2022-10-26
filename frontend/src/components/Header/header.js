import React from 'react';
import {Link} from 'react-router-dom';

const header = (props) => {
    return (
        <header>
            <nav className="navbar navbar-expand-lg navbar-dark navbar-fixed bg-secondary">
                <div className="container-fluid">
                    <button className="navbar-toggler" type="button" data-bs-toggle="collapse"
                            data-bs-target="#navbarCollapse"
                            aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"></span>
                    </button>
                    <a className="navbar-brand" href="/">Ticket-Shop Application</a>
                    <div className="collapse navbar-collapse" id="navbarCollapse">
                        <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                            <li className="nav-item active">
                                <Link className="nav-link" to={"/tickets"}>Tickets</Link>
                            </li>
                            <li className="nav-item active">
                                <Link className="nav-link" to={"/reservations"}>Reservations</Link>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </header>
    )
};

export default header;