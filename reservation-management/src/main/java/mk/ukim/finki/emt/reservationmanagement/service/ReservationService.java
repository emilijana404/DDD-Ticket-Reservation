package mk.ukim.finki.emt.reservationmanagement.service;

import mk.ukim.finki.emt.reservationmanagement.domain.exceptions.ReservationIdNotExistException;
import mk.ukim.finki.emt.reservationmanagement.domain.exceptions.ReservationTicketIdNotExistException;
import mk.ukim.finki.emt.reservationmanagement.domain.models.Reservation;
import mk.ukim.finki.emt.reservationmanagement.domain.models.ReservationId;
import mk.ukim.finki.emt.reservationmanagement.domain.models.ReservationTicketId;
import mk.ukim.finki.emt.reservationmanagement.service.forms.ReservationForm;
import mk.ukim.finki.emt.reservationmanagement.service.forms.ReservationTicketForm;

import java.util.List;
import java.util.Optional;

public interface ReservationService {

    ReservationId makeReservation(ReservationForm reservationForm);

    List<Reservation> findAll();

    Optional<Reservation> findById(ReservationId id);

    void addReservationTicket(ReservationId reservationId, ReservationTicketForm reservationTicketForm) throws ReservationIdNotExistException;

    void deleteReservationTicket(ReservationId reservationId, ReservationTicketId reservationTicketId) throws ReservationIdNotExistException, ReservationTicketIdNotExistException;
}
