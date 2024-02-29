package musala.drones.monitoring.dto;

import lombok.AllArgsConstructor;
import musala.drones.monitoring.exceptions.DroneLimitWeightExceededException;

@AllArgsConstructor
public enum DroneModel {
	Lightweight(0, 100),
	Middleweight(101, 200),
	Cruiserweight(201, 300),
	Heavyweight(301, 500);

    private final int lowerLimit;
    private final int upperLimit;

    public static DroneModel getModelByWeight(int weight) {
        for (DroneModel model : DroneModel.values()) {
            if (weight >= model.lowerLimit && weight <= model.upperLimit) return model;
        }

        throw new DroneLimitWeightExceededException();
    }
}