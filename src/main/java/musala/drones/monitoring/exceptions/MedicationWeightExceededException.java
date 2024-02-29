package musala.drones.monitoring.exceptions;

import musala.drones.monitoring.configuration.ServiceExceptionMessages;

public class MedicationWeightExceededException extends IllegalStateException {

    public MedicationWeightExceededException() {
        super(ServiceExceptionMessages.MEDICATION_WEIGHT_EXEEDED_MESSAGE);
    }
}