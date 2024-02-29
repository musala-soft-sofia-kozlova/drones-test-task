package musala.drones.monitoring.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import musala.drones.monitoring.dto.DroneDto;
import musala.drones.monitoring.dto.DroneModel;
import musala.drones.monitoring.dto.DroneState;

import static musala.drones.monitoring.configuration.ConstantProperies.DRONE_ID_LENGTH_MAX;

@Entity
@Table(name = "drones")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DroneEntity {
    @Id
    @Column(name="serial_number", length = DRONE_ID_LENGTH_MAX)
    String serialNumber;

    @Column(name="weight_limit", nullable = false, updatable = false)
    int weightLimit;

    @Column(name="battery_capacity", nullable = false, updatable = true)
    int batteryCapacity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,updatable = true)
    DroneState state;

    @OneToOne
    @JoinColumn(name = "flight_id")
    FlightRecordEntity current_flight;

    public void setBatteryCapacity(byte batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }
    public void setState(DroneState state) {
        this.state = state;
    }

    public static DroneEntity of(DroneDto droneDto) {
        return new DroneEntity(droneDto.getSerialNumber(),
                droneDto.getWeightLimit(),
                droneDto.getBatteryCapacity(), droneDto.getState(), null);
    }
}
