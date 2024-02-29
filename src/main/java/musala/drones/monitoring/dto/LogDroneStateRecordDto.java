package musala.drones.monitoring.dto;

import static musala.drones.monitoring.configuration.ConstantProperies.*;
import static musala.drones.monitoring.configuration.ServiceExceptionMessages.*;

import java.time.LocalDateTime;

import jakarta.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class LogDroneStateRecordDto
{
    @NotBlank(message = DRONE_ID_WRONG_LENGTH_MESSAGE)
    @Size(max = DRONE_ID_LENGTH_MAX, message = DRONE_ID_WRONG_LENGTH_MESSAGE)
    String droneSerialNumber;

    @NotNull(message = WRONG_BATTERY_CAPACITY_MESSAGE)
    @PositiveOrZero(message = WRONG_BATTERY_CAPACITY_MESSAGE)
    @Max(value = 100, message = WRONG_BATTERY_CAPACITY_MESSAGE)
    int batteryCapacity;// percentage;

    @NotNull(message = WRONG_DRONE_STATE_MESSAGE)
    DroneState state;
    
    LocalDateTime timestamp;
}