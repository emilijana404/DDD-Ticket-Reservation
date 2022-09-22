package mk.ukim.finki.emt.reservationmanagement.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.reservationmanagement.domain.exceptions.ReservationIdNotExistException;
import mk.ukim.finki.emt.reservationmanagement.domain.exceptions.ReservationTicketIdNotExistException;
import mk.ukim.finki.emt.reservationmanagement.domain.models.Reservation;
import mk.ukim.finki.emt.reservationmanagement.domain.models.ReservationId;
import mk.ukim.finki.emt.reservationmanagement.domain.models.ReservationTicketId;
import mk.ukim.finki.emt.reservationmanagement.domain.repository.ReservationRepository;
import mk.ukim.finki.emt.reservationmanagement.service.ReservationService;
import mk.ukim.finki.emt.reservationmanagement.service.forms.ReservationForm;
import mk.ukim.finki.emt.reservationmanagement.service.forms.ReservationTicketForm;
import mk.ukim.finki.emt.sharedkernel.domain.events.reservations.ReservationTicketCreated;
import mk.ukim.finki.emt.sharedkernel.infra.DomainEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
@RequestMapping("/api/reservation")
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final Validator validator;
    private final DomainEventPublisher domainEventPublisher;

    @Override
    public ReservationId makeReservation(ReservationForm reservationForm) {
        Objects.requireNonNull(reservationForm,"reservation must not be null.");
        var constraintViolations = validator.validate(reservationForm);
        if (constraintViolations.size()>0) {
            throw new ConstraintViolationException("The reservation form is not valid", constraintViolations);
        }
        var newReservation = reservationRepository.saveAndFlush(toDomainObject(reservationForm));
        newReservation.getReservationTicketsList().forEach(item->domainEventPublisher.publish(new ReservationTicketCreated(item.getTicketId().getId(),item.getQuantity())));
        return newReservation.getId();
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Optional<Reservation> findById(ReservationId id) {
        return reservationRepository.findById(id);
    }

    @Override
    public void addReservationTicket(ReservationId reservationId, ReservationTicketForm reservationTicketForm) throws ReservationIdNotExistException {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(ReservationIdNotExistException::new);
        reservation.addReservation(reservationTicketForm.getTicket(),reservationTicketForm.getQuantity());
        reservationRepository.saveAndFlush(reservation);
        domainEventPublisher.publish(new ReservationTicketCreated(reservationTicketForm.getTicket().getId().getId(),reservationTicketForm.getQuantity()));
    }

    @Override
    public void deleteReservationTicket(ReservationId reservationId, ReservationTicketId reservationTicketId) throws ReservationIdNotExistException, ReservationTicketIdNotExistException {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(ReservationIdNotExistException::new);
        reservation.removeItem(reservationTicketId);
        reservationRepository.saveAndFlush(reservation);
    }

    private Reservation toDomainObject(ReservationForm reservationForm) {
        var reservation = new Reservation(Instant.now(), reservationForm.getCurrency());
        reservationForm.getTickets().forEach(item->reservation.addReservation(item.getTicket(),item.getQuantity()));
        return reservation;
    }
}
