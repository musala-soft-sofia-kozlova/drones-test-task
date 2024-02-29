package musala.drones.monitoring.repository;

import musala.drones.monitoring.entities.FlightRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRecordRepository extends JpaRepository<FlightRecordEntity, Long> {
}