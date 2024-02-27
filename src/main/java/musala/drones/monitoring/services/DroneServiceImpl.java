package musala.drones.monitoring.services;

import org.springframework.stereotype.Service;

import musala.drones.monitoring.dto.DroneDto;
import musala.drones.monitoring.dto.MedicationDto;

import java.util.List;

@Service
public class DroneServiceImpl implements DroneService {

    @Override
    public DroneDto registerDrone(DroneDto drone) {
       return drone;
    }
    @Override
    public void loadDroneWithMedications(String droneId, List<MedicationDto> medications) {

    }
    @Override
    public List<MedicationDto> getLoadedMedications(String droneId) {
        return null;
    }
    @Override
    public List<String> getAvailableDrones() {
        return null;
    }
    @Override
    public int getBatteryLevel(String droneId) {
        return 0;
    }
}
