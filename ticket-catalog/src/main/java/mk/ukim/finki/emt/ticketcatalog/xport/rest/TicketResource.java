package mk.ukim.finki.emt.ticketcatalog.xport.rest;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.ticketcatalog.domain.models.Ticket;
import mk.ukim.finki.emt.ticketcatalog.domain.models.TicketId;
import mk.ukim.finki.emt.ticketcatalog.domain.valueobjects.MovieFormat;
import mk.ukim.finki.emt.ticketcatalog.services.TicketService;
import mk.ukim.finki.emt.ticketcatalog.services.form.TicketForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/ticket")
@AllArgsConstructor
public class TicketResource {

    private final TicketService ticketService;

    @GetMapping
    public List<Ticket> getAll() {
        return ticketService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> findById(@PathVariable String id)
    {
        return this.ticketService.findById(TicketId.of(id))
                .map(ticket -> ResponseEntity.ok().body(ticket))
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public  ResponseEntity<Ticket> save(@RequestBody TicketForm form)
    {
        return this.ticketService.createTicket(form)
                .map(ticket -> ResponseEntity.ok().body(ticket))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public  ResponseEntity<Ticket> edit(@PathVariable String id, @RequestBody TicketForm form)
    {
        return this.ticketService.editTicket(TicketId.of(id),form)
                .map(ticket -> ResponseEntity.ok().body(ticket))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable String id) {
        this.ticketService.deleteTicket(TicketId.of(id));
        if(this.ticketService.findById(TicketId.of(id)).isEmpty()) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/formatType")
    public List<MovieFormat> findAllFormatTypes(){
        return this.ticketService.listAllMovieFormats();
    }

    @GetMapping("/currency")
    public List<Currency> findAll()
    {
        return Arrays.asList(Currency.values());
    }
}
