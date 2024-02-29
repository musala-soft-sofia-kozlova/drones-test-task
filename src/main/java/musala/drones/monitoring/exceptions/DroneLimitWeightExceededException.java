package musala.drones.monitoring.exceptions;

import musala.drones.monitoring.configuration.ServiceExceptionMessages;

public class DroneLimitWeightExceededException extends IllegalStateException {

    public DroneLimitWeightExceededException() {
        super(ServiceExceptionMessages.WRONG_WEIGHT_LIMIT_MESSAGE);
    }
}