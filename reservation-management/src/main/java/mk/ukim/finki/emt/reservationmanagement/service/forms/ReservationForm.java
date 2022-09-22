package mk.ukim.finki.emt.reservationmanagement.service.forms;

import lombok.Data;
import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Getter
public class ReservationForm {

    @NotNull
    private Currency currency;

    @Valid
    @NotEmpty
    private List<ReservationTicketForm> tickets = new ArrayList<>();
}
