package musala.drones.monitoring;


import static musala.drones.monitoring.dto.DroneState.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import musala.drones.monitoring.dto.DroneDto;
import musala.drones.monitoring.dto.DroneState;
import musala.drones.monitoring.entities.DroneEntity;
import musala.drones.monitoring.repository.DronesRepository;
import musala.drones.monitoring.services.DroneService;
import musala.drones.monitoring.services.DroneServiceImpl;
import musala.drones.monitoring.services.SystemService;


@Component
@Slf4j
public class DroneServiceDynamicConfiguration {	
	private static final String HOST = "http://localhost:8080";
	
    private final DronesRepository dronesRepo;
    private final SystemService systemService;
    
    private Thread periodicalTaskThread;
    private volatile boolean stopPeriodicalTask = false;
    
    @Value("${app.periodic.log.unit.milliseconds}")
    int logTimePeriod;
    
	Map<DroneState, Integer> droneStatesBatteryCapacityMap() {
		// energy  in percents
		Map<DroneState, Integer> map = new HashMap<>();
		map.put(LOADING, 0);
		map.put(LOADED, 0);
		map.put(DELIVERING, -50);
		map.put(DELIVERED, 0);
		map.put(RETURNING, -40);
		map.put(IDLE, 100);
		return map;
	}
    
    @Autowired
    public DroneServiceDynamicConfiguration(DronesRepository dronesRepo, SystemService systemService) {
        this.dronesRepo = dronesRepo;
        this.systemService = systemService;
    }
    
	@Transactional
	void periodicTask() {
    	List<DroneEntity> drones = dronesRepo.findAll();
    	drones.forEach(this::droneProcessing);
	}
	
	void startPeriodicalTask()	{
		periodicalTaskThread = new Thread(() -> {
	        while (!stopPeriodicalTask) {
	            try {
	                Thread.sleep(logTimePeriod);
	            } catch (InterruptedException e) {
	                Thread.currentThread().interrupt();
	            }
	            log.debug("Test: periodicTask");
	            periodicTask();
	        }
	    });
	    periodicalTaskThread.setDaemon(true);
	    periodicalTaskThread.start();
	}
	
	void stopPeriodicalTask() {
		stopPeriodicalTask = true;
	}

	private void droneProcessing(DroneEntity drone) {
		
		DroneDto droneDto = DroneEntity.to(drone);
		Map<DroneState, Integer> map = droneStatesBatteryCapacityMap();
		
		DroneState currentState = droneDto.getState();
		if (currentState == IDLE)
			droneDto.setBatteryCapacity(100);
		else
		    droneDto.setBatteryCapacity(droneDto.getBatteryCapacity() + map.get(currentState));
		
		systemService.changeDroneState(droneDto);
	}

}
