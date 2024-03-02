package musala.drones.monitoring.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import musala.drones.monitoring.dto.DroneState;

import java.time.LocalDateTime;

@Entity
@Table(name = "drone-states-log")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LogDroneStateRecordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToOne
    @JoinColumn(name = "serial_number", nullable = false, updatable = false)
    DroneEntity drone;

    @Column(name = "battery_capacity")
    int batteryCapacity;

    @Enumerated(EnumType.STRING)
    DroneState state;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    LocalDateTime timestamp;
}
