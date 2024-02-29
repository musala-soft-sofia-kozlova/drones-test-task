package musala.drones.monitoring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class LogDroneLoadRecordDto {
    long flightID;
    String droneSerialNumber;
    List<MedicationDto> medications;
    LocalDateTime timestamp;
}
