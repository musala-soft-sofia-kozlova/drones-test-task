package musala.drones.monitoring.configuration;

public interface ControllerURLs {
    String DRONES_URL = "/drones";
    String DRONE_ID = "drone_id";

    String REGISTER_DRONE = "/register";
    String LOAD_DRONE_WITH_MEDICATION = "/{" + DRONE_ID + "}/load";
    String GET_LOADED_MEDICATIONS = "/{" + DRONE_ID + "}/medications";
    String GET_AVAILABLE_DRONES = "/available";
    String GET_BATTERY_LEVEL = "/{" + DRONE_ID + "}/battery";
}
