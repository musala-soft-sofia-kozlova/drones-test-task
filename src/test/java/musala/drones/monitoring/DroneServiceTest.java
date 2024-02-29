package musala.drones.monitoring;

import musala.drones.monitoring.dto.DroneDto;
import musala.drones.monitoring.dto.DroneState;
import musala.drones.monitoring.dto.MedicationDto;
import musala.drones.monitoring.entities.DroneEntity;
import musala.drones.monitoring.entities.FlightRecordEntity;
import musala.drones.monitoring.entities.MedicationEntity;
import musala.drones.monitoring.repository.DronesRepository;
import musala.drones.monitoring.repository.FlightRecordRepository;
import musala.drones.monitoring.repository.MedicationsRepository;
import musala.drones.monitoring.services.DroneService;

import static musala.drones.monitoring.TestDisplayNames.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@Sql(scripts = "classpath:test_medications.sql")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DroneServiceTest {
    private static final String DRONE_ID = "DRONE-1";
    private static final String DRONE_ID_NOT_FOUND = "DRONE-111";
    private static final String MED_ID1 = "MED_CODE_1";
    private static final String MED_ID2 = "MED_CODE_2";
    
    DroneDto droneDto;
    MedicationDto medicationDto1;
    MedicationDto medicationDto2;
    List<MedicationDto> medicationList;

    @Autowired
    DroneService droneService;

    @Autowired
    DronesRepository dronesRepo;
    @Autowired
    MedicationsRepository medicationsRepo;
    @Autowired
    FlightRecordRepository flightRecordRepo;

    @BeforeEach
    public void setUp() throws Exception {
        droneDto = new DroneDto(DRONE_ID, 500, 100, DroneState.IDLE);
        
        MedicationEntity med1 =  medicationsRepo.findById(MED_ID1).orElse(null);
        if (med1 != null)
          medicationDto1 = MedicationEntity.to(med1);

        MedicationEntity med2 =  medicationsRepo.findById(MED_ID2).orElse(null);
        if (med2 != null)
            medicationDto2 = MedicationEntity.to(med2);

        medicationList = new ArrayList<>(Arrays.asList(medicationDto1, medicationDto2));
    }

    @Test
    @Order(1)
    @DisplayName(SERVICE_TEST + REGISTER_DRONE_TEST + " good")
    void registerDroneTestGood() {
        DroneDto res = droneService.registerDrone(droneDto);
        assertEquals(droneDto, res);
        assertNotNull(dronesRepo.findById(DRONE_ID).orElse(null));
        assertNull(dronesRepo.findById(DRONE_ID_NOT_FOUND).orElse(null));
    }

    @Test
    @Order(2)
    @Transactional
    @DisplayName(SERVICE_TEST + LOAD_DRONE_WITH_MEDICATION_TEST + " good")
    void loadDroneWithMedicationsTestGood() {
    	droneService.registerDrone(droneDto);
        droneService.loadDroneWithMedications(DRONE_ID, medicationList);
        List<FlightRecordEntity> log = flightRecordRepo.findAll();
        assertEquals(1, log.size());
        FlightRecordEntity flightRec = log.get(0);
        DroneEntity drone = flightRec.getDrone();
        assertEquals(DRONE_ID, drone.getSerialNumber());
        assertEquals(DroneState.LOADING, drone.getState());
        assertEquals(MED_ID1, flightRec.medications.get(0).getCode());
        assertEquals(2, flightRec.medications.size());
        DroneEntity drone2 = dronesRepo.findById(DRONE_ID).orElse(null);
        assertNotNull(drone2);
        assertEquals(drone2.getCurrent_flight().flight_id, flightRec.flight_id);
    }

    @Test
    @Order(3)
    @Transactional
    @DisplayName(SERVICE_TEST + GET_LOADED_MEDICATIONS_TEST + " good")
    void getLoadedMedicationsTestGood() {
    	droneService.registerDrone(droneDto);
        droneService.loadDroneWithMedications(DRONE_ID, medicationList);
        List<MedicationDto> listMed = droneService.getLoadedMedications(DRONE_ID);
        assertEquals(2, listMed.size());
        assertEquals(MED_ID1, listMed.get(0).getCode());
    }

    @Test
    @Order(4)
    @Transactional
    @DisplayName(SERVICE_TEST + GET_AVAILABLE_DRONES_TEST + " good")
    void getAvailableDronesTestGood() {
    	List<String> res = droneService.getAvailableDrones();
    	assertEquals(0, res.size());
    	droneService.registerDrone(droneDto);
    	res = droneService.getAvailableDrones();
    	assertEquals(1, res.size());
    	assertEquals(DRONE_ID, res.get(0));
    	droneService.loadDroneWithMedications(DRONE_ID, medicationList);
    	res = droneService.getAvailableDrones();
    	assertEquals(0, res.size());
    }

    @Test
    @Order(5)
    @DisplayName(SERVICE_TEST + GET_BATTERY_LEVEL_TEST + " good")
    void getBatteryLevelTestGood() {
    	droneService.registerDrone(droneDto);
    	int res = droneService.getBatteryLevel(DRONE_ID);
    	assertEquals(droneDto.getBatteryCapacity(), res);
    }
}