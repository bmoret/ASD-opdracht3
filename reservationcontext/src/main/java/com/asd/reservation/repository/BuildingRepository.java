package java.com.asd.reservation.domain.model.building;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BuildingRepository extends JpaRepository<BuildingRepository, UUID> {
}
