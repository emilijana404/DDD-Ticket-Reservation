import React from 'react';
import {Link} from 'react-router-dom';

const Tickets = (props) => {

    return (
        <div className={"container mm-4 mt-5"}>
            <div className={"row"}>
                <div className={"row"}>
                    <table className={"table table-striped"}>
                        <thead>
                        <tr>
                            <th scope={"col"}>Movie Name</th>
                            <th scope={"col"}>Movie Format</th>
                            <th scope={"col"}>Date</th>
                            <th scope={"col"}>Time</th>
                            <th scope={"col"}>Price</th>
                            <th scope={"col"}>Sales</th>
                            <th scope={"col"}></th>
                        </tr>
                        </thead>
                        <tbody>
                        {props.ticket.map((term) => {
                            return (
                                <tr>
                                    <td>{term.movieName}</td>
                                    <td>{term.movieFormat}</td>
                                    <td>{term.date}</td>
                                    <td>{term.time} H</td>
                                    <td>{term.price.amount} {term.price.currency}</td>
                                    <td>{term.sales}</td>
                                    <td scope={"col"} className={"text-right"}>
                                        <a title={"Reserve"} className={"btn btn-outline-dark"}
                                           onClick={() => props.onAdd(term.id)}>
                                            Reserve
                                        </a>
                                    </td>
                                </tr>
                            )
                        })}
                        </tbody>
                    </table>
                    <div className="col mb-3">
                        <div className="row">
                            <div className="col-sm-12 col-md-12">
                                <Link className={"btn btn-outline-info"} to={"/tickets/add"}>Add new ticket</Link>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Tickets;