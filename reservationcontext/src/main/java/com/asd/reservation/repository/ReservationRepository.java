package java.com.asd.reservation.domain.model.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.com.asd.reservation.domain.model.space.SpaceId;
import java.util.List;
import java.util.UUID;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
    List<Reservation> findBySpace(SpaceId id);
}
