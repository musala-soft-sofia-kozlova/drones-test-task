package musala.drones.monitoring.exceptions;

import musala.drones.monitoring.configuration.ServiceExceptionMessages;
import musala.exceptions.NotFoundException;

public class MedicationNotFoundException extends NotFoundException {
    public MedicationNotFoundException() {
        super(ServiceExceptionMessages.MEDICATION_NOT_FOUND_MESSAGE);
    }
}
