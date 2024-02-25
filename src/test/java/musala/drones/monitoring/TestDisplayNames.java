package musala.drones.monitoring;

import java.util.List;

import musala.drones.monitoring.dto.DroneDto;
import musala.drones.monitoring.dto.MedicationDto;

public interface TestDisplayNames {
    String SERVICE_TEST = "Service: ";
    String CONTROLLER_TEST = "Controller: ";
    String REGISTER_DRONE_TEST = "registerDrone";
    String LOAD_DRONE_WITH_MEDICATION_TEST = "loadDroneWithMedications";
    String GET_LOADED_MEDICATIONS_TEST = "getLoadedMedications";
    String GET_AVAILABLE_DRONES_TEST = "getAvailableDrones";
    String GET_BATTERY_LEVEL_TEST = "getBatteryLevel";
}