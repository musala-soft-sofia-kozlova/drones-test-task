package musala.drones.monitoring.dto;

import jakarta.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static musala.drones.monitoring.configuration.ConstantProperies.*;
import static musala.drones.monitoring.configuration.ServiceExceptionMessages.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MedicationDto {
    @Pattern(regexp = MEDICATION_NAME_REGEXP, message = WRONG_MEDICATION_NAME_MESSAGE)
    String name;

    @NotNull(message = WRONG_WEIGHT_LIMIT_MESSAGE)
    @PositiveOrZero(message = WRONG_WEIGHT_LIMIT_MESSAGE)
    @Max(value = WEIGHT_LIMIT_MAX, message = WRONG_WEIGHT_LIMIT_MESSAGE)
    int weight;

    @Pattern(regexp = MEDICATION_CODE_REGEXP, message = WRONG_MEDICATION_CODE_MESSAGE)
    String code;

    byte[] image; //picture of the medication case
}
