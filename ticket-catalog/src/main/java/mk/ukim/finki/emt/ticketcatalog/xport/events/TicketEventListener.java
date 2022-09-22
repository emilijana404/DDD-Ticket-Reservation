package mk.ukim.finki.emt.ticketcatalog.xport.events;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.sharedkernel.domain.config.TopicHolder;
import mk.ukim.finki.emt.sharedkernel.domain.events.DomainEvent;
import mk.ukim.finki.emt.sharedkernel.domain.events.reservations.ReservationTicketCreated;
import mk.ukim.finki.emt.sharedkernel.domain.events.reservations.ReservationTicketRemoved;
import mk.ukim.finki.emt.ticketcatalog.domain.models.TicketId;
import mk.ukim.finki.emt.ticketcatalog.services.TicketService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TicketEventListener {

    private final TicketService ticketService;

    @KafkaListener(topics = TopicHolder.TOPIC_RESERVATION_TICKET_CREATED, groupId = "ticketCatalog")
    public void consumeReservationTicketCreatedEvent(String jsonMessage) {
        try {
            ReservationTicketCreated event = DomainEvent.fromJson(jsonMessage,ReservationTicketCreated.class);
            ticketService.ReservationTicketCreated(TicketId.of(event.getTicketId()), event.getQuantity());
            System.out.println("Listener");
        } catch (Exception e){
            System.out.println("listener exception");
        }
    }

    @KafkaListener(topics = TopicHolder.TOPIC_RESERVATION_TICKET_REMOVED, groupId = "ticketCatalog")
    public void consumeReservationTicketRemovedEvent(String jsonMessage) {
        try {
            ReservationTicketRemoved event = DomainEvent.fromJson(jsonMessage,ReservationTicketRemoved.class);
            ticketService.ReservationTicketRemoved(TicketId.of(event.getTicketId()), event.getQuantity());
        } catch (Exception e){

        }
    }
}
