package mk.ukim.finki.emt.sharedkernel.domain.events.reservations;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.config.TopicHolder;
import mk.ukim.finki.emt.sharedkernel.domain.events.DomainEvent;

@Getter
public class ReservationTicketCreated extends DomainEvent {

    private String ticketId;
    private int quantity;

    public ReservationTicketCreated(String topic) {
        super(TopicHolder.TOPIC_RESERVATION_TICKET_CREATED);
    }

    public ReservationTicketCreated(String ticketId, int quantity) {
        super(TopicHolder.TOPIC_RESERVATION_TICKET_CREATED);
        this.ticketId = ticketId;
        this.quantity = quantity;
    }
}
