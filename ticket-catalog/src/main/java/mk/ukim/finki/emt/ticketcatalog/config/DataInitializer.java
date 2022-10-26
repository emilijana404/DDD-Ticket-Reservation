package mk.ukim.finki.emt.ticketcatalog.config;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import mk.ukim.finki.emt.ticketcatalog.domain.models.Ticket;
import mk.ukim.finki.emt.ticketcatalog.domain.repository.TicketRepository;
import mk.ukim.finki.emt.ticketcatalog.domain.valueobjects.MovieFormat;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
@AllArgsConstructor
public class DataInitializer {

    private final TicketRepository ticketRepository;

    @PostConstruct
    public void initData() {
        Ticket t1 = Ticket.build("Inside out", 10, MovieFormat.DIGITAL2D, "15:00", "05/12", Money.valueOf(Currency.MKD,500));
        Ticket t2 = Ticket.build("Lion King", 5, MovieFormat.DIGITAL2D, "20:30", "28/10", Money.valueOf(Currency.MKD,100));

        if (ticketRepository.findAll().isEmpty()) {
            ticketRepository.saveAll(Arrays.asList(t1,t2));
        }
    }
}
