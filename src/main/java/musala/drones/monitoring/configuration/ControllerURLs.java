package musala.drones.monitoring.configuration;

public interface ControllerURLs {
    String DRONES_URL = "/drones";

    String REGISTER_DRONE = "/register";
    String LOAD_DRONE_WITH_MEDICATION = "/load";
    String GET_LOADED_MEDICATIONS = "/medications";
    String GET_AVAILABLE_DRONES = "/available";
    String GET_BATTERY_LEVEL = "/battery";
}
