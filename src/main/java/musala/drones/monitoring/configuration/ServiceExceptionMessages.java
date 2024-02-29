package musala.drones.monitoring.configuration;

public interface ServiceExceptionMessages {
	String DRONE_NOT_FOUND_MESSAGE = "Drone Not Found";
	String DRONE_ID_WRONG_LENGTH_MESSAGE = "Drone ID is wrong (incorrect length)";
	String DRONE_ALREADY_REGISTERED_MESSAGE = "Drone ID already registered";
	String WRONG_WEIGHT_LIMIT_MESSAGE = "Weight Limit is unacceptable. Set weight limit in grams";
	String WRONG_BATTERY_CAPACITY_MESSAGE = "Set battery capacity in percents";
	String LOW_BATTERY_CAPACITY_MESSAGE = "Battery capacity is too low";
	String WRONG_DRONE_STATE_MESSAGE = "Drone state is wrong";

	String MEDICATION_NOT_FOUND_MESSAGE = "Medication Not Found";
	String WRONG_MEDICATION_NAME_MESSAGE = "Medication name is wrong (allowed only letters, numbers, ‘-‘, ‘_’)";
	String WRONG_MEDICATION_CODE_MESSAGE = "Medication code is wrong (allowed only upper case letters, underscore and numbers)";
	String MEDICATION_WEIGHT_EXEEDED_MESSAGE = "Medication weight exeeded";
}
