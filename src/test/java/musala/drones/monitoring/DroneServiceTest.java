package musala.drones.monitoring;

import com.fasterxml.jackson.databind.ObjectMapper;

import musala.drones.monitoring.services.DroneService;

import static musala.drones.monitoring.TestDisplayNames.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DroneServiceTest {
    @Autowired
    DroneService droneService;

    @Autowired
    ObjectMapper mapper;

    @Test
    @DisplayName(SERVICE_TEST + REGISTER_DRONE_TEST)
    void registerDroneTest() {
    }

    @Test
    @DisplayName(SERVICE_TEST + LOAD_DRONE_WITH_MEDICATION_TEST)
    void loadDroneWithMedicationsTest() {
    }

    @Test
    @DisplayName(SERVICE_TEST + GET_LOADED_MEDICATIONS_TEST)
    void getLoadedMedicationsTest() {
    }

    @Test
    @DisplayName(SERVICE_TEST + GET_AVAILABLE_DRONES_TEST)
    void getAvailableDronesTest() {
    }

    @Test
    @DisplayName(SERVICE_TEST + GET_BATTERY_LEVEL_TEST)
    void getBatteryLevelTest() {
    }
}