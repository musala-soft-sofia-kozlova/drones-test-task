package musala.drones.monitoring.dto;

public class DroneDto
{
    String serialNumber;// (100 characters max);
    DroneModels model;
    int weightLimit;// (500gr max);
    int batteryCapacity;// (percentage);
    DroneStates state;
}
