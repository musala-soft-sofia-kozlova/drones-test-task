package musala.drones.monitoring.exceptions;

import musala.drones.monitoring.configuration.ServiceExceptionMessages;
import musala.exceptions.NotFoundException;

public class DroneNotFoundException extends NotFoundException {
	public DroneNotFoundException() {
		super(ServiceExceptionMessages.DRONE_NOT_FOUND_MESSAGE);
	}
}
