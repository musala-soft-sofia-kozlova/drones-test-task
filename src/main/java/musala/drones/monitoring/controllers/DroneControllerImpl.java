package musala.drones.monitoring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import musala.drones.monitoring.dto.DroneDto;
import musala.drones.monitoring.dto.MedicationDto;

import java.util.List;

@RestController
public class DroneControllerImpl implements DroneController {

//    @Autowired
//    private DroneServiceImpl droneService;

	@Override
	public int getBatteryLevel(String droneId) {
		return 0;
	}

	@Override
	public void registerDrone(DroneDto drone) {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadDroneWithMedications(String droneId, List<MedicationDto> medications) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<MedicationDto> getLoadedMedications(String droneId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getAvailableDrones() {
		// TODO Auto-generated method stub
		return null;
	}
}
