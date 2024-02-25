package musala.drones.monitoring;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import musala.drones.monitoring.controllers.DroneController;
import musala.drones.monitoring.services.DroneService;

import static musala.drones.monitoring.TestDisplayNames.*;


@WebMvcTest(DroneController.class)
class DroneControllerTest {
    private static final String HOST = "http://localhost:8080/";
    @Autowired
    MockMvc mockMvc;

    @MockBean
    DroneService droneService;

 //   @Autowired
 //   ObjectMapper mapper;

    @Test
    @DisplayName(CONTROLLER_TEST + REGISTER_DRONE_TEST)
    void registerDroneTest() throws Exception {
    }

    @Test
    @DisplayName(CONTROLLER_TEST + LOAD_DRONE_WITH_MEDICATION_TEST)
    void loadDroneWithMedicationsTest() throws Exception {
    }

    @Test
    @DisplayName(CONTROLLER_TEST + GET_LOADED_MEDICATIONS_TEST)
    void getLoadedMedicationsTest() throws Exception {
    }

    @Test
    @DisplayName(CONTROLLER_TEST + GET_AVAILABLE_DRONES_TEST)
    void getAvailableDronesTest() throws Exception {
    }

    @Test
    @DisplayName(CONTROLLER_TEST + GET_BATTERY_LEVEL_TEST)
    void getBatteryLevelTest() throws Exception {
        int capacity = 0;
        when(droneService.getBatteryLevel("aaa")).thenReturn(capacity);

        ResultActions result = mockMvc.perform(get("/drones/aaa/battery")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
        // Then
        String resultAsString = result
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertEquals(Integer.toString(capacity), resultAsString);
        //verify(droneService, times(1)).getBatteryLevel(any());
    }
}
