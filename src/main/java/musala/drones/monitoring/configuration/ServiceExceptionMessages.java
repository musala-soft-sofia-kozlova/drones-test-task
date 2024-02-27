package musala.drones.monitoring.configuration;

public interface ServiceExceptionMessages {
	String DRONE_NOT_FOUND = "Drone Not Found";
	String DRONE_ID_WRONG_LENGTH = "Drone ID is wrong (incorrect length)";

	String WRONG_DRONE_MODEL = "Drone Model is wrong";
	String WRONG_WEIGHT_LIMIT = "Weight Limit is unacceptable. Set weight limit in grams";
	String WRONG_BATTERY_CAPACITY = "Set battery capacity in percents";
}
