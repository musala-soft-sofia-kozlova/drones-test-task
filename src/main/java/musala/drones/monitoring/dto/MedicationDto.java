package musala.drones.monitoring.dto;

public class MedicationDto {
    String name;// (allowed only letters, numbers, ‘-‘, ‘_’);
    int weight;
    String code;// (allowed only upper case letters, underscore and numbers);
    byte[] image; //(picture of the medication case).
}
