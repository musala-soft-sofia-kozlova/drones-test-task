package musala.drones.monitoring.services;

import musala.drones.monitoring.dto.DroneDto;

public interface SystemService {
    /*
     * receiving current state and current battery capacity from drone when it finished its current phase and start new one 
     */
	DroneDto changeDroneState(DroneDto droneDto);
}
