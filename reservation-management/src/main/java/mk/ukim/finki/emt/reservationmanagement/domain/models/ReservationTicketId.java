package mk.ukim.finki.emt.reservationmanagement.domain.models;

import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

public class ReservationTicketId extends DomainObjectId {

    private ReservationTicketId() {
        super(ReservationTicketId.randomId(ReservationTicketId.class).getId());
    }

    public ReservationTicketId(String uuid) {
        super(uuid);
    }
}
