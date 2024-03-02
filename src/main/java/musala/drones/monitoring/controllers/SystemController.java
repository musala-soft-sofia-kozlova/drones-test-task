package musala.drones.monitoring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import musala.drones.monitoring.configuration.ControllerURLs;
import musala.drones.monitoring.dto.DroneDto;
import musala.drones.monitoring.services.SystemService;

@RestController
@RequestMapping(ControllerURLs.DRONES_URL)
public class SystemController {

	@Autowired
	SystemService service;

	/**
	 * drone sends info that it finished its current phase of flight and its current
	 * level of battery capacity
	 */
	@PutMapping("/{droneId}/done")
	public DroneDto changeDroneState(DroneDto drone) {
		return service.changeDroneState(drone);
	}
}
