package musala.drones.monitoring;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static musala.drones.monitoring.configuration.ServiceExceptionMessages.*;

import musala.drones.monitoring.dto.DroneState;
import musala.drones.monitoring.dto.MedicationDto;

import musala.drones.monitoring.exceptions.DroneAlreadyRegisteredException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.PutMapping;

import com.fasterxml.jackson.databind.ObjectMapper;

import musala.drones.monitoring.controllers.DroneController;
import musala.drones.monitoring.dto.DroneDto;
import musala.drones.monitoring.dto.DroneModel;
import musala.drones.monitoring.services.DroneService;
import musala.exceptions.GlobalExceptionsHandler;
import musala.drones.monitoring.exceptions.DroneNotFoundException;

import static musala.drones.monitoring.TestDisplayNames.*;
import static musala.drones.monitoring.configuration.ControllerURLs.*;
import static musala.drones.monitoring.configuration.ConstantProperies.*;


@WebMvcTest(DroneController.class)
class DroneControllerTest {
    private static final String HOST = "http://localhost:8080";
    private static final String DRONE_ID = "DRONE-1";
    private static final String DRONE_ID_NOT_FOUND = "DRONE-111";
    
    DroneDto droneDto;
    MedicationDto medicationDto1;
    MedicationDto medicationDto2;
    List<MedicationDto> medicationList;
    
    @Autowired
    MockMvc mockMvc;

    @MockBean
    DroneService droneService;

    @Autowired
    ObjectMapper mapper;
    
    @BeforeEach
    public void setUp() throws Exception {
    	droneDto = new DroneDto(DRONE_ID, DroneModel.Heavyweight, 100, 100, DroneState.IDLE);
    	medicationDto1 = new MedicationDto("MED-NAME-1", 100, "MED_CODE_1", null);
    	medicationDto2 = new MedicationDto("MED-NAME-2", 100, "MED_CODE_2", new byte[100]);
    	medicationList = new ArrayList<>(Arrays.asList(medicationDto1, medicationDto2));
    }

    ////////////////////// registerDroneTest
    @Test
    @DisplayName(CONTROLLER_TEST + REGISTER_DRONE_TEST + " good")
    void registerDroneTestGood() throws Exception {
        when(droneService.registerDrone(droneDto)).thenReturn(droneDto);
    	String droneJSON = mapper.writeValueAsString(droneDto);
        String response = postRequest(HOST + DRONES_URL + REGISTER_DRONE, droneJSON, 200);
        assertEquals(droneJSON, response);
        verify(droneService, times(1)).registerDrone(any());
    }

    //@Test
    @DisplayName(CONTROLLER_TEST + REGISTER_DRONE_TEST + " drone ID already registered")
    void registerDroneTestAlreadyExists() throws Exception{
        when(droneService.registerDrone(droneDto)).thenThrow(new DroneAlreadyRegisteredException());
        String droneJSON = mapper.writeValueAsString(droneDto);
        String response = postRequest(HOST + DRONES_URL + REGISTER_DRONE, droneJSON, 400);
        assertEquals(DRONE_ALREADY_REGISTERED_MESSAGE, response);
        verify(droneService, times(0)).registerDrone(any());
    }

    @Test
    @DisplayName(CONTROLLER_TEST + REGISTER_DRONE_TEST + " wrong drone Id")
    void registerDroneTestWrongDroneId() throws Exception {
        droneDto.setSerialNumber("");
        String droneJSON = mapper.writeValueAsString(droneDto);
        String response = postRequest(HOST + DRONES_URL + REGISTER_DRONE, droneJSON, 400);
        assertEquals(DRONE_ID_WRONG_LENGTH_MESSAGE, response);
        
        droneDto.setSerialNumber(null);
        droneJSON = mapper.writeValueAsString(droneDto);
        response = postRequest(HOST + DRONES_URL + REGISTER_DRONE, droneJSON, 400);
        assertEquals(DRONE_ID_WRONG_LENGTH_MESSAGE, response);
        
        droneDto.setSerialNumber(new String(new char[10000]));
        droneJSON = mapper.writeValueAsString(droneDto);
        response = postRequest(HOST + DRONES_URL + REGISTER_DRONE, droneJSON, 400);
        assertErrorMessage(response, DRONE_ID_WRONG_LENGTH_MESSAGE);

        verify(droneService, times(0)).registerDrone(any());
    }

    @Test
    @DisplayName(CONTROLLER_TEST + REGISTER_DRONE_TEST + " wrong drone model")
    void registerDroneTestWrongDroneModel() throws Exception {
    	droneDto.setModel(null);
        String droneJSON = mapper.writeValueAsString(droneDto);
        String response = postRequest(HOST + DRONES_URL + REGISTER_DRONE, droneJSON, 400);
        assertEquals(WRONG_DRONE_MODEL_MESSAGE, response);
        verify(droneService, times(0)).registerDrone(any());
    }

    @Test
    @DisplayName(CONTROLLER_TEST + REGISTER_DRONE_TEST + " wrong weight limit")
    void registerDroneTestWrongWeightLimit() throws Exception {
    	droneDto.setWeightLimit(WEIGHT_LIMIT_MAX + 100);
        String droneJSON = mapper.writeValueAsString(droneDto);
        String response = postRequest(HOST + DRONES_URL + REGISTER_DRONE, droneJSON, 400);
        assertEquals(WRONG_WEIGHT_LIMIT_MESSAGE, response);
        
        droneDto.setWeightLimit(-1);
        droneJSON = mapper.writeValueAsString(droneDto);
        response = postRequest(HOST + DRONES_URL + REGISTER_DRONE, droneJSON, 400);
        assertEquals(WRONG_WEIGHT_LIMIT_MESSAGE, response);

        verify(droneService, times(0)).registerDrone(any());
    }

    @Test
    @DisplayName(CONTROLLER_TEST + REGISTER_DRONE_TEST + " wrong battery capacity")
    void registerDroneTestWrongBatteryCapacity() throws Exception {
    	droneDto.setBatteryCapacity(200);
        String droneJSON = mapper.writeValueAsString(droneDto);
        String response = postRequest(HOST + DRONES_URL + REGISTER_DRONE, droneJSON, 400);
        assertEquals(WRONG_BATTERY_CAPACITY_MESSAGE, response);
        
        droneDto.setBatteryCapacity(-1);
        droneJSON = mapper.writeValueAsString(droneDto);
        response = postRequest(HOST + DRONES_URL + REGISTER_DRONE, droneJSON, 400);
        assertEquals(WRONG_BATTERY_CAPACITY_MESSAGE, response);

        verify(droneService, times(0)).registerDrone(any());
    }
    
    @Test
    @DisplayName(CONTROLLER_TEST + REGISTER_DRONE_TEST + " all wrong")
    void registerDroneTestAllWrong() throws Exception {
    	droneDto.setSerialNumber("");
    	droneDto.setModel(null);
    	droneDto.setWeightLimit(WEIGHT_LIMIT_MAX + 100);
    	droneDto.setBatteryCapacity(200);
        String droneJSON = mapper.writeValueAsString(droneDto);
        String response = postRequest(HOST + DRONES_URL + REGISTER_DRONE, droneJSON, 400);
        assertErrorMessage(response, DRONE_ID_WRONG_LENGTH_MESSAGE);
        assertErrorMessage(response, WRONG_DRONE_MODEL_MESSAGE);
        assertErrorMessage(response, WRONG_WEIGHT_LIMIT_MESSAGE);
        assertErrorMessage(response, WRONG_BATTERY_CAPACITY_MESSAGE);
        verify(droneService, times(0)).registerDrone(any());
    }

    ////////////////////// loadDroneWithMedicationsTest
    @Test
    @DisplayName(CONTROLLER_TEST + LOAD_DRONE_WITH_MEDICATION_TEST + " good")
    void loadDroneWithMedicationsTesGood() throws Exception {
    	String medicationListJSON = mapper.writeValueAsString(medicationList);
    	String response = putRequest(HOST + DRONES_URL + "//" + DRONE_ID + LOAD_DRONE_WITH_MEDICATION, medicationListJSON, 200);
        assertEquals("", response);
        verify(droneService, times(1)).loadDroneWithMedications(any(), any());
    }

    @Test
    @DisplayName(CONTROLLER_TEST + LOAD_DRONE_WITH_MEDICATION_TEST + " wrong medication data")
    void loadDroneWithMedicationsTestWrongMedicationData() throws Exception {
        //medicationDto1.setName("123$%&");
        //medicationDto2.setCode("abc-2");
        medicationDto2.setWeight(WEIGHT_LIMIT_MAX + 100);
        String medicationListJSON = mapper.writeValueAsString(medicationList);
        String response = putRequest(HOST + DRONES_URL + "//" + DRONE_ID + LOAD_DRONE_WITH_MEDICATION, medicationListJSON, 400);
        assertErrorMessage(response, WRONG_WEIGHT_LIMIT_MESSAGE);
        assertErrorMessage(response, WRONG_MEDICATION_NAME_MESSAGE);
        assertErrorMessage(response, WRONG_MEDICATION_CODE_MESSAGE);
        verify(droneService, times(0)).loadDroneWithMedications(any(), any());
    }
    
    ////////////////////// getLoadedMedicationsTest

    @Test
    @DisplayName(CONTROLLER_TEST + GET_LOADED_MEDICATIONS_TEST)
    void getLoadedMedicationsTest() throws Exception {
    }

    ////////////////////// getAvailableDronesTest
    @Test
    @DisplayName(CONTROLLER_TEST + GET_AVAILABLE_DRONES_TEST)
    void getAvailableDronesTest() throws Exception {
		String[] returnedDrones = { "Drone-1", "Drone-2" };
		when(droneService.getAvailableDrones()).thenReturn(Arrays.asList(returnedDrones));
		String returnedJSON = mapper.writeValueAsString(returnedDrones);
		String response = getRequest(HOST + DRONES_URL + "//" + GET_AVAILABLE_DRONES, 200);
		assertEquals(returnedJSON, response);
    }

    ////////////////////// getBatteryLevelTest
    @Test
    @DisplayName(CONTROLLER_TEST + GET_BATTERY_LEVEL_TEST)
    void getBatteryLevelTest() throws Exception {
        int capacity = 100;
        when(droneService.getBatteryLevel(DRONE_ID)).thenReturn(capacity);

        String resultAsString = getRequest(HOST + DRONES_URL + "//" + DRONE_ID + GET_BATTERY_LEVEL, 200);
        assertEquals(Integer.toString(capacity), resultAsString);
        verify(droneService, times(1)).getBatteryLevel(any());
    }
    
    //@Test
	@DisplayName(CONTROLLER_TEST + GET_BATTERY_LEVEL_TEST + " Drone Not Found")
	void getBatteryLevelTest_DroneNotFound() throws Exception {
		when(droneService.getBatteryLevel(DRONE_ID_NOT_FOUND))
		.thenThrow(new DroneNotFoundException());
		String response = getRequest(HOST + DRONES_URL + "//" + DRONE_ID + GET_BATTERY_LEVEL, 404);
		assertEquals(DRONE_NOT_FOUND_MESSAGE, response);
		verify(droneService, times(0)).getBatteryLevel(any());
	}
    
	
    ////////////////////// auxiliary methods
	private String getRequest(String url, int status) throws Exception {
		String response = mockMvc.perform(get(url)).andDo(print())
                .andExpect(status().is(status)).andReturn().getResponse()
				.getContentAsString();
		return response;
	}

    private String postRequest(String url, String contentJSON, int status) throws Exception {
        String response = mockMvc
                .perform(post(url).contentType(MediaType.APPLICATION_JSON).content(contentJSON)).andDo(print())
                .andExpect(status().is(status)).andReturn().getResponse().getContentAsString();
        return response;
    }
    
    private String putRequest(String url, String contentJSON, int status) throws Exception {
        String response = mockMvc
                .perform(put(url).contentType(MediaType.APPLICATION_JSON).content(contentJSON)).andDo(print())
                .andExpect(status().is(status)).andReturn().getResponse().getContentAsString();
        return response;
    }
    
	private void assertErrorMessage(String response, String expectedMessage) {
		String[] actualMessages = response.split(GlobalExceptionsHandler.ERROR_MESSAGES_DELIMITER);
		assertTrue(Arrays.asList(actualMessages).contains(expectedMessage));
	}
}
