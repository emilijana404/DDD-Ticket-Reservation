package mk.ukim.finki.emt.reservationmanagement.domain.valueobjects;

import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class TicketId extends DomainObjectId {

    public TicketId() {
        super(TicketId.randomId(TicketId.class).getId());
    }

    public TicketId(String uuid) {
        super(uuid);
    }
}
