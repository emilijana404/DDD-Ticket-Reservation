package mk.ukim.finki.emt.ticketcatalog.domain.models;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import mk.ukim.finki.emt.ticketcatalog.domain.valueobjects.MovieFormat;

import javax.persistence.*;

@Entity
@Table(name = "ticket")
@Getter
public class Ticket extends AbstractEntity<TicketId> {

    private String movieName;

    private int sales = 0;

    @Enumerated(value = EnumType.STRING)
    private MovieFormat movieFormat;

    private String time;

    private String date;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="amount", column = @Column(name = "price_amount")),
            @AttributeOverride(name="currency", column = @Column(name = "price_currency")),
    })
    private Money price;

    public Ticket() {
        super(TicketId.randomId(TicketId.class));
    }

    public static Ticket build(String movieName, int sales, MovieFormat movieFormat, String time, String date, Money price) {
        Ticket t = new Ticket();
        t.movieName = movieName;
        t.sales = sales;
        t.movieFormat = movieFormat;
        t.time = time;
        t.date = date;
        t.price = price;
        return t;
    }

    public Ticket edit(Ticket t, String movieName, int sales, MovieFormat movieFormat, String time, String date, Money price)
    {
        t.movieName = movieName;
        t.sales = sales;
        t.movieFormat = movieFormat;
        t.time = time;
        t.date = date;
        t.price = price;
        return t;
    }

    public void addSales(int quantity) {
        this.sales = this.sales - quantity;
    }

    public void removeSales(int quantity) {
        this.sales -= quantity;
    }
}
