package musala.drones.monitoring.repository;

import musala.drones.monitoring.dto.DroneState;
import musala.drones.monitoring.entities.DroneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DronesRepository extends JpaRepository<DroneEntity, String> {

	List<DroneEntity> findByStateAndBatteryCapacityGreaterThanEqual(DroneState state, int lowBatteryCapacity);

}
