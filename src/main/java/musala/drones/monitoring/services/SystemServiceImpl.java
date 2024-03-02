package musala.drones.monitoring.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import musala.drones.monitoring.dto.DroneDto;
import musala.drones.monitoring.dto.DroneState;
import musala.drones.monitoring.entities.DroneEntity;
import musala.drones.monitoring.exceptions.DroneNotFoundException;
import musala.drones.monitoring.repository.DronesRepository;
import static musala.drones.monitoring.dto.DroneState.*;

@Service
@Slf4j
public class SystemServiceImpl implements SystemService {

    @Autowired
    DronesRepository dronesRepo;
    
    @Autowired
	Map<DroneState, DroneState> droneStatesMap;
    
    @Override
    @Transactional
    public DroneDto changeDroneState(DroneDto droneDto) {
    	DroneEntity droneEntity = dronesRepo.findById(droneDto.getSerialNumber()).orElseThrow(() -> new DroneNotFoundException());

    	DroneState statePrev = droneDto.getState();
    	DroneState stateNew = statePrev;
		
		if (statePrev != IDLE) {
			stateNew = droneStatesMap.get(statePrev);
			droneEntity.setState(stateNew);
			
			if (stateNew == IDLE)
				droneEntity.setCurrent_flight(null);
		}
		
		log.debug("SystemServiceImpl::changeDroneState: {} from {} to {}", 
				droneDto.getSerialNumber(), statePrev, stateNew);
		
        dronesRepo.save(droneEntity);
        return DroneEntity.to(droneEntity);
    }
}
