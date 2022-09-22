package mk.ukim.finki.emt.ticketcatalog.domain.repository;

import mk.ukim.finki.emt.ticketcatalog.domain.models.Ticket;
import mk.ukim.finki.emt.ticketcatalog.domain.models.TicketId;
import mk.ukim.finki.emt.ticketcatalog.domain.valueobjects.MovieFormat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Book;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, TicketId> {

    List<Ticket> findAllByMovieFormat(MovieFormat movieFormat);
}
