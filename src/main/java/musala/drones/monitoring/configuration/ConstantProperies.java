package musala.drones.monitoring.configuration;

import org.springframework.beans.factory.annotation.Value;

public interface ConstantProperies {
    int WEIGHT_LIMIT_MAX = 500;
    int DRONE_ID_LENGTH_MAX = 100;

    // allowed only letters, numbers, '-', '_'
    String MEDICATION_NAME_REGEXP = "[\\w-]+";

    // allowed only upper case letters, underscore and numbers
    String MEDICATION_CODE_REGEXP = "[A-Z_\\d]+";
}
