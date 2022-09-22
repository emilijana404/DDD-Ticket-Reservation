package mk.ukim.finki.emt.reservationmanagement.domain.models;

import lombok.Getter;
import mk.ukim.finki.emt.reservationmanagement.domain.valueobjects.TicketId;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import org.springframework.lang.NonNull;


import javax.persistence.*;

@Entity
@Table(name = "reservation_ticket")
@Getter
public class ReservationTicket extends AbstractEntity<ReservationTicketId> {

    @Embedded
    private Money ticketPrice;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Embedded
    @AttributeOverride(name="id",column = @Column(name="ticket_id",nullable = false))
    private TicketId ticketId;

    public ReservationTicket() {
        super(DomainObjectId.randomId(ReservationTicketId.class));
    }

    public ReservationTicket(@NonNull TicketId ticketId, @NonNull Money ticketPrice, int quantity) {
        super(DomainObjectId.randomId(ReservationTicketId.class));
        this.ticketId = ticketId;
        this.ticketPrice = ticketPrice;
        this.quantity = quantity;
    }

    public Money subtotal() {
        return ticketPrice.multiply(quantity);
    }
}
