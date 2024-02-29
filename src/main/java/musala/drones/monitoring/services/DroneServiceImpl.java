package musala.drones.monitoring.services;

import lombok.extern.slf4j.Slf4j;
import musala.drones.monitoring.dto.*;
import musala.drones.monitoring.entities.*;
import musala.drones.monitoring.exceptions.*;
import musala.drones.monitoring.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class DroneServiceImpl implements DroneService {

    @Autowired
    DronesRepository dronesRepo;
    @Autowired
    MedicationsRepository medicationsRepo;
    @Autowired
    FlightRecordRepository logDroneLoadRecordRepo;

    @Value("${app.battery.capacity.LOW_BATTERY_CAPACITY:25}")
    int lowBatteryCapacity;

    @Override
    @Transactional
    public DroneDto registerDrone(DroneDto droneDto) {
        if (dronesRepo.existsById(droneDto.getSerialNumber())) {
            throw new DroneAlreadyRegisteredException();
        }
        log.debug("DroneServiceImpl::registerDrone: {}", droneDto.getSerialNumber());
        DroneEntity droneEntity = DroneEntity.of(droneDto);
        droneEntity.setState(DroneState.IDLE);
        dronesRepo.save(droneEntity);
        return droneDto;
    }
    @Override
    @Transactional
    public long loadDroneWithMedications(String droneId, List<MedicationDto> medications) {
        DroneEntity droneEntity = dronesRepo.findById(droneId).orElseThrow(() -> new DroneNotFoundException());
        log.debug("DroneServiceImpl::loadDroneWithMedications: found drone: {}", droneId);
        if (droneEntity.getState() != DroneState.IDLE)
            throw new IllegalDroneStateException();

        if (droneEntity.getBatteryCapacity() < lowBatteryCapacity)
            throw new LowBatteryCapacityException();

        int droneWeightLimit = droneEntity.getWeightLimit();
        FlightRecordEntity flightLogEntity = new FlightRecordEntity();
        droneEntity.setState(DroneState.LOADING);
        flightLogEntity.setDrone(droneEntity);
        flightLogEntity.setTimestamp(LocalDateTime.now());
        flightLogEntity.medications = new ArrayList<>();
        int weight = 0;
        for (MedicationDto medicationDto : medications) {
            MedicationEntity medicationEntity = medicationsRepo.findById(medicationDto.getCode())
                    .orElseThrow(() -> new MedicationNotFoundException());

            weight += medicationEntity.getWeight();
            if (droneWeightLimit < weight)
                throw new MedicationWeightExceededException();

            flightLogEntity.medications.add(medicationEntity);
        }

        logDroneLoadRecordRepo.save(flightLogEntity);
        droneEntity.setCurrent_flight(flightLogEntity);
        dronesRepo.save(droneEntity);
        return flightLogEntity.getFlight_id();
    }
    @Override
    @Transactional(readOnly = true)
    public List<MedicationDto> getLoadedMedications(String droneId) {
        log.debug("DroneServiceImpl::getLoadedMedications: {}", droneId);
        DroneEntity droneEntity = dronesRepo.findById(droneId).orElseThrow(() -> new DroneNotFoundException());

        FlightRecordEntity flightRec = droneEntity.getCurrent_flight();
        if (flightRec == null)
            return Collections.emptyList();
        
        List<MedicationDto> res = flightRec.medications.stream().map(m -> MedicationEntity.to(m)).toList();
        System.out.println(res.size());
        return flightRec.medications.stream().map(m -> MedicationEntity.to(m)).toList();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<String> getAvailableDrones() {
        log.debug("DroneServiceImpl::getAvailableDrones");
        List<DroneEntity> res = dronesRepo.findByStateAndBatteryCapacityGreaterThanEqual(DroneState.IDLE, lowBatteryCapacity);
        return res.stream().map(d -> d.getSerialNumber()).toList();
    }
    
    @Override
    @Transactional(readOnly = true)
    public int getBatteryLevel(String droneId) {
        log.debug("DroneServiceImpl::getBatteryLevel: {}", droneId);
        DroneEntity droneEntity = dronesRepo.findById(droneId).orElseThrow(() -> new DroneNotFoundException());
        return droneEntity.getBatteryCapacity();
    }
}
