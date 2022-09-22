package mk.ukim.finki.emt.reservationmanagement.domain.repository;

import mk.ukim.finki.emt.reservationmanagement.domain.models.Reservation;
import mk.ukim.finki.emt.reservationmanagement.domain.models.ReservationId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, ReservationId> {
}
