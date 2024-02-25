package musala.drones.monitoring.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import musala.drones.monitoring.configuration.ControllerURLs;
import musala.drones.monitoring.dto.DroneDto;
import musala.drones.monitoring.dto.MedicationDto;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static musala.drones.monitoring.configuration.ControllerURLs.*;

import java.util.List;

/**
 * Drones API
 */
@RequestMapping(ControllerURLs.DRONES_URL)
public interface DroneController {
    /**
     * registering a drone
     */
    @PostMapping(REGISTER_DRONE)
    void registerDrone(@RequestBody DroneDto drone);

    /**
     * loading a drone with medication items
     */
    @PutMapping(LOAD_DRONE_WITH_MEDICATION)
    void loadDroneWithMedications(@PathVariable(DRONE_ID) String droneId, @RequestBody List<MedicationDto> medications);

    /**
     * checking loaded medication items for a given drone
     */
    @GetMapping(GET_LOADED_MEDICATIONS)
    List<MedicationDto> getLoadedMedications(@PathVariable(DRONE_ID) String droneId);

    /**
     * checking available drones for loading
     * @return list of available drons IDs
     */
    @GetMapping(GET_AVAILABLE_DRONES)
    List<String> getAvailableDrones();

    /**
     * check drone battery level for a given drone
     */
    @GetMapping(GET_BATTERY_LEVEL)
    int getBatteryLevel(@PathVariable(DRONE_ID) String droneId);
}
