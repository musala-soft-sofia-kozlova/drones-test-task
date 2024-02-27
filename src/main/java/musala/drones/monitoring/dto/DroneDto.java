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
    @NotBlank(message = DRONE_ID_WRONG_LENGTH)
    @Size(max = DRONE_ID_LENGTH_MAX, message = DRONE_ID_WRONG_LENGTH)
    String serialNumber;// (100 characters max);

    @NotNull(message = WRONG_DRONE_MODEL)
    DroneModel model;

    @NotNull(message = WRONG_WEIGHT_LIMIT)
    @Positive(message = WRONG_WEIGHT_LIMIT)
    @Max(value = WEIGHT_LIMIT_MAX, message = WRONG_WEIGHT_LIMIT)
    int weightLimit; //grams

    @NotNull(message = WRONG_BATTERY_CAPACITY)
    @Positive(message = WRONG_BATTERY_CAPACITY)
    @Max(value = 100, message = WRONG_BATTERY_CAPACITY)
    int batteryCapacity;// percentage;

    DroneState state = IDLE;
}
