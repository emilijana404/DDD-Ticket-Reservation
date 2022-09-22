package mk.ukim.finki.emt.reservationmanagement.domain.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.base.ValueObject;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

@Getter
public class Ticket implements ValueObject {

    private final TicketId id;

    private final String name;

    private final Integer sales;

    private final MovieFormat format;

    private final String time;

    private final String date;

    private final Money price;

    private Ticket() {
        this.id = TicketId.randomId(TicketId.class);
        this.name = "";
        this.sales = 0;
        this.format = MovieFormat.DIGITAL2D;
        this.time = "";
        this.date = "";
        this.price = Money.valueOf(Currency.MKD,0);
    }

    @JsonCreator
    public Ticket(@JsonProperty("id") TicketId id,
                  @JsonProperty("movieName") String name,
                  @JsonProperty("sales") Integer sales,
                  @JsonProperty("format") MovieFormat format,
                  @JsonProperty("time") String time,
                  @JsonProperty("date") String date,
                  @JsonProperty("price") Money price) {
        this.id = id;
        this.name = name;
        this.sales = sales;
        this.format = format;
        this.time = time;
        this.date = date;
        this.price = price;
    }
}
