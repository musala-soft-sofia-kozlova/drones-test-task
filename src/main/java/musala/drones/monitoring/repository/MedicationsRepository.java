package musala.drones.monitoring.repository;

import musala.drones.monitoring.entities.MedicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationsRepository extends JpaRepository<MedicationEntity, String> {
}
