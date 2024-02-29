package musala.drones.monitoring.exceptions;


import musala.drones.monitoring.configuration.ServiceExceptionMessages;

public class IllegalDroneStateException extends IllegalStateException {

    public IllegalDroneStateException() {
        super(ServiceExceptionMessages.WRONG_DRONE_STATE_MESSAGE);

    }

}