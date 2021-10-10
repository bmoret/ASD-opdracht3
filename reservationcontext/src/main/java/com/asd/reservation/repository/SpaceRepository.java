package main.java.com.asd.reservation.repository;
import main.java.com.asd.reservation.domain.model.space.Space;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface SpaceRepository extends JpaRepository<Space, UUID> {
}
