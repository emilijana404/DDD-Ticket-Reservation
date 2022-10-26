import React from 'react';

const TicketAdd = (props) => {

    const [formData, updateFormData] = React.useState({
        movieName: "",
        sales: 0,
        format: 1,
        time: "",
        date: "",
        currency: "",
        amount: 0
    });

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    };

    const onFormSubmit = (e) => {
        e.preventDefault();
        const movieName = formData.movieName;
        const sales = formData.sales;
        const format = formData.format;
        const time = formData.time;
        const date = formData.date;
        const currency = formData.currency;
        const amount = formData.amount;

        props.onAddTicket(movieName, sales, format, time, date, currency, amount);
    }

    return (
        <div className="row mt-4"
             style={{
                 alignItems: 'center',
                 justifyContent: 'center',
             }}
        >
            <div className="col-md-6">
                <form onSubmit={onFormSubmit}>
                    <div className="form-group mt-3">
                        <label htmlFor="name">Movie name</label>
                        <input type="text"
                               className="form-control"
                               id="name"
                               name="movieName"
                               required
                               placeholder="Enter movie name"
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group mt-3">
                        <label htmlFor="sales">Sales</label>
                        <input type="text"
                               className="form-control"
                               id="sales"
                               name="sales"
                               placeholder="Enter sales"
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group mt-3">
                        <label>Format</label>
                        <select name="format" className="form-control" onChange={handleChange}>
                            {props.movieFormat.map((term) =>
                                <option value={String(term)}>{term}</option>
                            )}
                        </select>
                    </div>
                    <div className="form-group mt-3">
                        <label htmlFor="time">Time</label>
                        <input type="text"
                               className="form-control"
                               id="time"
                               name="time"
                               placeholder="21:00"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group mt-3">
                        <label htmlFor="date">Date</label>
                        <input type="text"
                               className="form-control"
                               id="date"
                               name="date"
                               placeholder="21/05"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group mt-3">
                        <label>Currency</label>
                        <select name="currency" className="form-control" onChange={handleChange}>
                            {props.currency.map((term) =>
                                <option value={term}>{term}</option>
                            )}
                        </select>
                    </div>
                    <div className="form-group mt-3">
                        <label htmlFor="time">Amount</label>
                        <input type="text"
                               className="form-control"
                               id="amount"
                               name="amount"
                               required
                               placeholder="Enter amount"
                               onChange={handleChange}
                        />
                    </div>
                    <br/>
                    <button id="submit" type="submit" className="btn btn-outline-success">Submit</button>
                </form>
            </div>
        </div>
    )
};

export default TicketAdd;