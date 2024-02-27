package musala.drones.monitoring.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import musala.drones.monitoring.dto.DroneDto;
import musala.drones.monitoring.dto.MedicationDto;
import musala.drones.monitoring.services.DroneService;

import java.util.List;

@RestController
@Validated
@Slf4j
public class DroneControllerImpl implements DroneController {

    @Autowired
    private DroneService droneService;

	@Override
	public void registerDrone(DroneDto drone) {
		log.debug("DroneControllerImpl::registerDrone: {}", drone);
		droneService.registerDrone(drone);
	}

	@Override
	public void loadDroneWithMedications(String droneId, List<MedicationDto> medications) {
		log.debug("DroneControllerImpl::loadDroneWithMedications: {} {}", droneId, medications);
		droneService.loadDroneWithMedications(droneId, medications);

	}

	@Override
	public List<MedicationDto> getLoadedMedications(String droneId) {
		log.debug("DroneControllerImpl::getLoadedMedications: {}", droneId);
		return droneService.getLoadedMedications(droneId);
	}

	@Override
	public List<String> getAvailableDrones() {
		log.debug("DroneControllerImpl::getAvailableDrones");
		return droneService.getAvailableDrones();
	}
	
	@Override
	public int getBatteryLevel(String droneId) {
		log.debug("DroneControllerImpl::getBatteryLevel: {}", droneId);
		return droneService.getBatteryLevel(droneId);
	}
}
