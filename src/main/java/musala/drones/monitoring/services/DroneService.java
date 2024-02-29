package musala.drones.monitoring.services;

import java.util.List;

import musala.drones.monitoring.dto.DroneDto;
import musala.drones.monitoring.dto.MedicationDto;


public interface DroneService {
    public DroneDto registerDrone(DroneDto drone);
    public long loadDroneWithMedications(String droneId, List<MedicationDto> medications);
    public List<MedicationDto> getLoadedMedications(String droneId);
    public List<String> getAvailableDrones();
    public int getBatteryLevel(String droneId);
}
