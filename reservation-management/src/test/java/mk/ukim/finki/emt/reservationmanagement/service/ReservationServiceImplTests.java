package mk.ukim.finki.emt.reservationmanagement.service;

import mk.ukim.finki.emt.reservationmanagement.domain.exceptions.ReservationIdNotExistException;
import mk.ukim.finki.emt.reservationmanagement.domain.models.Reservation;
import mk.ukim.finki.emt.reservationmanagement.domain.models.ReservationId;
import mk.ukim.finki.emt.reservationmanagement.domain.valueobjects.MovieFormat;
import mk.ukim.finki.emt.reservationmanagement.domain.valueobjects.Ticket;
import mk.ukim.finki.emt.reservationmanagement.domain.valueobjects.TicketId;
import mk.ukim.finki.emt.reservationmanagement.service.forms.ReservationForm;
import mk.ukim.finki.emt.reservationmanagement.service.forms.ReservationTicketForm;
import mk.ukim.finki.emt.reservationmanagement.xport.client.TicketClient;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class ReservationServiceImplTests {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private TicketClient ticketClient;

    private static Ticket newTicket(String name, Money price) {
        return new Ticket(TicketId.randomId(TicketId.class), name, 0, MovieFormat.DIGITAL2D, "12", "12", price);
    }

    @Test
    public void testMakeReservation() {

        ReservationTicketForm rt1 = new ReservationTicketForm();
        rt1.setTicket(newTicket("Inside out", Money.valueOf(Currency.MKD,1500)));
        rt1.setQuantity(1);

        ReservationTicketForm rt2 = new ReservationTicketForm();
        rt2.setTicket(newTicket("Lion King",Money.valueOf(Currency.MKD,500)));
        rt2.setQuantity(2);

        ReservationForm reservationForm = new ReservationForm();
        reservationForm.setCurrency(Currency.MKD);
        reservationForm.setTickets(Arrays.asList(rt1,rt2));

        ReservationId newReservationId = reservationService.makeReservation(reservationForm);
        Reservation newReservation = reservationService.findById(newReservationId).orElseThrow(ReservationIdNotExistException::new);
        Assertions.assertEquals(newReservation.total(),Money.valueOf(Currency.MKD,2500));
    }

    @Test
    public void testMakeReservationWithRealData() {
        List<Ticket> ticketList = ticketClient.findAll();
        System.out.println(ticketList.toString());
        Ticket t1 = ticketList.get(0);
        Ticket t2 = ticketList.get(1);

        ReservationTicketForm tf1 = new ReservationTicketForm();
        tf1.setTicket(t1);
        tf1.setQuantity(1);

        ReservationTicketForm tf2 = new ReservationTicketForm();
        tf2.setTicket(t2);
        tf2.setQuantity(2);

        ReservationForm reservationForm = new ReservationForm();
        reservationForm.setCurrency(Currency.MKD);
        reservationForm.setTickets(Arrays.asList(tf1,tf2));

        ReservationId newReservationId = reservationService.makeReservation(reservationForm);
        Reservation newReservation = reservationService.findById(newReservationId).orElseThrow(ReservationIdNotExistException::new);

        Money outMoney = t1.getPrice().multiply(tf1.getQuantity()).add(t2.getPrice().multiply(tf2.getQuantity()));
        Assertions.assertEquals(newReservation.total(),outMoney);
    }
}
