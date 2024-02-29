package musala.drones.monitoring.repository;

import musala.drones.monitoring.entities.LogDroneStateRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogDroneStateRecordsRepository extends JpaRepository<LogDroneStateRecordEntity, Long> {
}

