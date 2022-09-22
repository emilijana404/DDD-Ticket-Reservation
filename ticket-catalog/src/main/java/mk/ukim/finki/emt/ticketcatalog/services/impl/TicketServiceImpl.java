package mk.ukim.finki.emt.ticketcatalog.services.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.ticketcatalog.domain.exceptions.TicketNotFoundException;
import mk.ukim.finki.emt.ticketcatalog.domain.models.Ticket;
import mk.ukim.finki.emt.ticketcatalog.domain.models.TicketId;
import mk.ukim.finki.emt.ticketcatalog.domain.repository.TicketRepository;
import mk.ukim.finki.emt.ticketcatalog.domain.valueobjects.MovieFormat;
import mk.ukim.finki.emt.ticketcatalog.services.TicketService;
import mk.ukim.finki.emt.ticketcatalog.services.form.TicketForm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    @Override
    public Optional<Ticket> findById(TicketId id) {
        return ticketRepository.findById(id);
    }

    @Override
    public Optional<Ticket> createTicket(TicketForm form) {
        Ticket t = Ticket.build(form.getMovieName(), form.getSales(), form.getFormat(), form.getTime(), form.getDate(), form.getPrice());
        ticketRepository.save(t);
        return Optional.of(t);
    }

    @Override
    public Optional<Ticket> editTicket(TicketId id, TicketForm form) {
        Ticket t = ticketRepository.findById(id).orElseThrow(TicketNotFoundException::new);
        t.edit(t, form.getMovieName(), form.getSales(), form.getFormat(), form.getTime(), form.getDate(), form.getPrice());
        this.ticketRepository.save(t);
        return Optional.of(t);
    }

    @Override
    public void deleteTicket(TicketId id) {
        Ticket t = ticketRepository.findById(id).orElseThrow(TicketNotFoundException::new);
        this.ticketRepository.delete(t);
    }

    @Override
    public Ticket ReservationTicketCreated(TicketId ticketId, int quantity) {
        Ticket t = ticketRepository.findById(ticketId).orElseThrow(TicketNotFoundException::new);
        t.addSales(quantity);
        ticketRepository.saveAndFlush(t);
        return t;
    }

    @Override
    public Ticket ReservationTicketRemoved(TicketId ticketId, int quantity) {
        Ticket t = ticketRepository.findById(ticketId).orElseThrow(TicketNotFoundException::new);
        t.removeSales(quantity);
        ticketRepository.saveAndFlush(t);
        return t;
    }

    @Override
    public List<Ticket> getAll() {
        return ticketRepository.findAll();
    }

    @Override
    public List<Ticket> findAllByMovieFormat(MovieFormat movieFormat) {
        return this.ticketRepository.findAllByMovieFormat(movieFormat);
    }

    @Override
    public List<MovieFormat> listAllMovieFormats() {
        return List.of(MovieFormat.values());
    }
}
