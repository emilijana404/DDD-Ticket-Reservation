package mk.ukim.finki.emt.reservationmanagement.service.forms;

import lombok.Data;
import lombok.Getter;
import mk.ukim.finki.emt.reservationmanagement.domain.valueobjects.Ticket;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Getter
public class ReservationTicketForm {

    @NotNull
    private Ticket ticket;

    @Min(1)
    private int quantity = 1;
}
