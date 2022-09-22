package mk.ukim.finki.emt.reservationmanagement.domain.models;

import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.emt.reservationmanagement.domain.valueobjects.Ticket;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Table(name = "reservations")
public class Reservation extends AbstractEntity<ReservationId> {

    private Instant reservedOn;

    private String code;

    @Column(name="reservation_currency")
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<ReservationTicket> reservationTicketsList = new HashSet<>();

    public Reservation() {
        super(ReservationId.randomId(ReservationId.class));
    }

    public Reservation(Instant now, Currency currency) {
        super(ReservationId.randomId(ReservationId.class));
        this.reservedOn = now;
        this.currency = currency;
    }

    public Money total() {
        return reservationTicketsList.stream().map(ReservationTicket::subtotal).reduce(new Money(currency, 0), Money::add);
    }

    public ReservationTicket addReservation(@NonNull Ticket ticket, int quantity) {
        Objects.requireNonNull(ticket,"ticket must not be null");
        var reservation  = new ReservationTicket(ticket.getId(),ticket.getPrice(),quantity);
        reservationTicketsList.add(reservation);
        return reservation;
    }

    public void removeItem(@NonNull ReservationTicketId reservationTicketId) {
        Objects.requireNonNull(reservationTicketId,"Reservation Ticket must not be null");
        reservationTicketsList.removeIf(v->v.getId().equals(reservationTicketId));
    }

    public Set<ReservationTicket> getReservationList() {
        return reservationTicketsList;
    }
}
