package mk.ukim.finki.emt.sharedkernel.domain.events.reservations;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.config.TopicHolder;
import mk.ukim.finki.emt.sharedkernel.domain.events.DomainEvent;

@Getter
public class ReservationTicketRemoved extends DomainEvent {

    private String ticketId;
    private int quantity;

    public ReservationTicketRemoved(String topic) {
        super(TopicHolder.TOPIC_RESERVATION_TICKET_REMOVED);
    }

    public ReservationTicketRemoved(String topic, String ticketId, int quantity) {
        super(TopicHolder.TOPIC_RESERVATION_TICKET_REMOVED);
        this.ticketId = ticketId;
        this.quantity = quantity;
    }
}
