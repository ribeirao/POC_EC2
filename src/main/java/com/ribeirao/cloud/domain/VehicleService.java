package com.ribeirao.cloud.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ribeirao.cloud.application.config.exception.NotFoundException;
import com.ribeirao.cloud.application.dto.VehicleCommand;
import com.ribeirao.cloud.application.dto.VehicleTypeCommand;
import com.ribeirao.cloud.port.adapter.persistence.VehicleRepository;
import com.ribeirao.cloud.port.adapter.persistence.VehicleTypeRepository;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    private final VehicleTypeRepository vehicleTypeRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository,
            VehicleTypeRepository vehicleTypeRepository) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleTypeRepository = vehicleTypeRepository;
    }

    public VehicleCommand createVehicle(VehicleCommand command) {
        return convertEntityToCommand(vehicleRepository.save(convertCommandToEntity(command)));
    }

    public VehicleCommand editVehicle(Integer id, VehicleCommand command) throws NotFoundException {
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(NotFoundException::new);

        applyUpdatedValues(vehicle, command);

        return convertEntityToCommand(vehicleRepository.save(vehicle));
    }

    public VehicleCommand getVehicle(Integer id) throws NotFoundException {
        return convertEntityToCommand(
                vehicleRepository.findById(id).orElseThrow(NotFoundException::new));
    }

    public void deleteVehicle(Integer id) throws NotFoundException {
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(NotFoundException::new);
        vehicleRepository.delete(vehicle);
    }

    private Vehicle convertCommandToEntity(VehicleCommand command) {
        return new Vehicle(command.getName(), command.getDesc(),
                verifyAndSaveType(command.getType()), command.getPlate());
    }

    private void applyUpdatedValues(Vehicle vehicle, VehicleCommand command) {
        vehicle.setName(command.getName());
        vehicle.setDescription(command.getDesc());
        vehicle.setPlate(command.getPlate());
        vehicle.setType(verifyAndSaveType(command.getType()));
    }

    private VehicleCommand convertEntityToCommand(Vehicle vehicle) {
        return new VehicleCommand(vehicle.getId(), vehicle.getName(), vehicle.getDescription(),
                new VehicleTypeCommand(vehicle.getType().getId(), vehicle.getType().getName(),
                        vehicle.getType().getDescription()),
                vehicle.getPlate());
    }

    private VehicleType verifyAndSaveType(VehicleTypeCommand typeCommand) {
        return vehicleTypeRepository.findOneByName(typeCommand.getName())
                .orElse(vehicleTypeRepository.save(convertTypeCommandToEntity(typeCommand)));
    }

    private VehicleType convertTypeCommandToEntity(VehicleTypeCommand typeCommand) {
        return new VehicleType(typeCommand.getName(), typeCommand.getDesc());
    }
}
