package musala.drones.monitoring.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import musala.drones.monitoring.dto.DroneDto;
import musala.drones.monitoring.dto.MedicationDto;

import static musala.drones.monitoring.configuration.ConstantProperies.MEDICATION_CODE_REGEXP;
import static musala.drones.monitoring.configuration.ConstantProperies.MEDICATION_NAME_REGEXP;
import static musala.drones.monitoring.configuration.ConstantProperies.WEIGHT_LIMIT_MAX;
import static musala.drones.monitoring.configuration.ServiceExceptionMessages.WRONG_MEDICATION_CODE_MESSAGE;
import static musala.drones.monitoring.configuration.ServiceExceptionMessages.WRONG_MEDICATION_NAME_MESSAGE;
import static musala.drones.monitoring.configuration.ServiceExceptionMessages.WRONG_WEIGHT_LIMIT_MESSAGE;

@Entity
@Table(name="medications")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MedicationEntity {

    @Id
    String code;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    int weight;

    byte[] image;
    
    public static MedicationEntity of(MedicationDto medicationDto) {
        return new MedicationEntity(medicationDto.getCode(),
        		medicationDto.getName(), medicationDto.getWeight(),
        		medicationDto.getImage());
    }
    
    public static MedicationDto to(MedicationEntity medicationEntity) {
        return new MedicationDto(medicationEntity.getName(),
        		medicationEntity.getWeight(), medicationEntity.getCode(),
        		medicationEntity.getImage());
    }
}
