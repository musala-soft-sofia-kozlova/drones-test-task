package musala.drones.monitoring.exceptions;

import musala.drones.monitoring.configuration.ServiceExceptionMessages;

public class LowBatteryCapacityException extends IllegalStateException {

    public LowBatteryCapacityException() {
        super(ServiceExceptionMessages.LOW_BATTERY_CAPACITY_MESSAGE);
    }
}