package musala.drones.monitoring.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "flights-log")
@Data
public class FlightRecordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long flight_id;

    @OneToOne
    public DroneEntity drone;

    @OneToMany
    public List<MedicationEntity> medications;
    public LocalDateTime timestamp;
}
