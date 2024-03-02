package musala.drones.monitoring.periodicLog;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import lombok.extern.slf4j.Slf4j;
import musala.drones.monitoring.dto.DroneState;
import musala.drones.monitoring.entities.DroneEntity;
import musala.drones.monitoring.entities.LogDroneStateRecordEntity;
import musala.drones.monitoring.repository.DronesRepository;
import musala.drones.monitoring.repository.LogDroneStateRecordsRepository;

import static musala.drones.monitoring.dto.DroneState.*;

@EnableScheduling
@Configuration
@Slf4j
public class PeriodicLog {

    @Autowired
    DronesRepository dronesRepo;
    
    @Autowired
    LogDroneStateRecordsRepository logRepo;
    
	@Bean
	Map<DroneState, DroneState> droneStatesMap() {
		Map<DroneState, DroneState> res = new HashMap<>();
		res.put(LOADING, LOADED);
		res.put(LOADED, DELIVERING);
		res.put(DELIVERING, DELIVERED);
		res.put(DELIVERED, RETURNING);
		res.put(RETURNING, IDLE);
		return res;
	}

    @Scheduled(fixedDelayString = "${app.periodic.log.unit.milliseconds:5000}")
    public void logBatterLevelPeriodicTask() {
    	
    	List<DroneEntity> drones = dronesRepo.findAll();
    	drones.forEach(this::createLogRecord);
    }
    
	private void createLogRecord(DroneEntity drone) {
		LogDroneStateRecordEntity eventLog = LogDroneStateRecordEntity.builder()
                .drone(drone)
                .batteryCapacity(drone.getBatteryCapacity())
                .state(drone.getState())
                .timestamp(LocalDateTime.now())
                .build();

		log.debug("PeriodicLog::createLogRecord: {} {}", drone.getSerialNumber(), drone.getState());
		logRepo.save(eventLog);
	}
}