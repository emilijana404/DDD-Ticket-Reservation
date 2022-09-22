package mk.ukim.finki.emt.ticketcatalog.services;

import mk.ukim.finki.emt.ticketcatalog.domain.models.Ticket;
import mk.ukim.finki.emt.ticketcatalog.domain.models.TicketId;
import mk.ukim.finki.emt.ticketcatalog.domain.valueobjects.MovieFormat;
import mk.ukim.finki.emt.ticketcatalog.services.form.TicketForm;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

public interface TicketService {

    Optional<Ticket> findById(TicketId id);

    Optional<Ticket> createTicket(TicketForm form);

    Optional<Ticket> editTicket(TicketId id, TicketForm form);

    void deleteTicket(TicketId id);

    Ticket ReservationTicketCreated(TicketId ticketId, int quantity);

    Ticket ReservationTicketRemoved(TicketId ticketId, int quantity);

    List<Ticket> getAll();

    List<Ticket> findAllByMovieFormat(MovieFormat movieFormat);

    List<MovieFormat> listAllMovieFormats();
}
