package mk.ukim.finki.emt.ticketcatalog.domain.models;

import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;
import org.springframework.lang.NonNull;

public class TicketId extends DomainObjectId {

    private TicketId() {
        super(TicketId.randomId(TicketId.class).getId());
    }

    public TicketId(@NonNull String uuid) {
        super(uuid);
    }

    public static TicketId of(String uuid) {
        TicketId t = new TicketId(uuid);
        return t;
    }
}
