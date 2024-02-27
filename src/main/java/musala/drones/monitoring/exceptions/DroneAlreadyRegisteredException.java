package musala.drones.monitoring.exceptions;

import musala.drones.monitoring.configuration.ServiceExceptionMessages;

@SuppressWarnings("serial")
public class DroneAlreadyRegisteredException extends IllegalStateException {
    public DroneAlreadyRegisteredException() {
        super(ServiceExceptionMessages.DRONE_ALREADY_REGISTERED_MESSAGE);
    }
}
