package musala.drones.monitoring.dto;

import jakarta.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static musala.drones.monitoring.configuration.ConstantProperies.*;
import static musala.drones.monitoring.configuration.ServiceExceptionMessages.*;
import static musala.drones.monitoring.dto.DroneState.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DroneDto
{
    @NotBlank(message = DRONE_ID_WRONG_LENGTH_MESSAGE)
    @Size(max = DRONE_ID_LENGTH_MAX, message = DRONE_ID_WRONG_LENGTH_MESSAGE)
    String serialNumber;// (100 characters max);

    @NotNull(message = WRONG_DRONE_MODEL_MESSAGE)
    DroneModel model;

    @NotNull(message = WRONG_WEIGHT_LIMIT_MESSAGE)
    @PositiveOrZero(message = WRONG_WEIGHT_LIMIT_MESSAGE)
    @Max(value = WEIGHT_LIMIT_MAX, message = WRONG_WEIGHT_LIMIT_MESSAGE)
    int weightLimit; //grams

    @NotNull(message = WRONG_BATTERY_CAPACITY_MESSAGE)
    @PositiveOrZero(message = WRONG_BATTERY_CAPACITY_MESSAGE)
    @Max(value = 100, message = WRONG_BATTERY_CAPACITY_MESSAGE)
    int batteryCapacity;// percentage;

    DroneState state = IDLE;
}
