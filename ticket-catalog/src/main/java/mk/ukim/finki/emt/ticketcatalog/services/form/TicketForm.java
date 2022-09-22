package mk.ukim.finki.emt.ticketcatalog.services.form;

import lombok.Data;
import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import mk.ukim.finki.emt.ticketcatalog.domain.valueobjects.MovieFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Data
@Getter
public class TicketForm {

    @Valid
    @NotEmpty
    private String movieName;

    private int sales;

    @Valid
    @NotEmpty
    private MovieFormat format;

    @Valid
    @NotEmpty
    private String time;

    @Valid
    @NotEmpty
    private String date;

    private Money price;

    public TicketForm(String movieName, int sales, MovieFormat format, String time, String date, Money price) {
        this.movieName = movieName;
        this.sales = sales;
        this.format = format;
        this.time = time;
        this.date = date;
        this.price = price;
    }
}
