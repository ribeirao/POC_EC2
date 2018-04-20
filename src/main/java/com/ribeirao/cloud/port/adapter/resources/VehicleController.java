package com.ribeirao.cloud.port.adapter.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ribeirao.cloud.application.config.exception.NotFoundException;
import com.ribeirao.cloud.application.dto.VehicleCommand;
import com.ribeirao.cloud.domain.VehicleService;

@RestController
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping(value = "/v1/vehicles")
    public ResponseEntity<VehicleCommand> createVehicle(@RequestBody VehicleCommand command) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(vehicleService.createVehicle(command));
    }

    @PutMapping(value = "/v1/vehicles/{id}")
    public ResponseEntity<VehicleCommand> editVehicle(@PathVariable("id") Integer id,
            @RequestBody VehicleCommand command) throws NotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(vehicleService.editVehicle(id, command));
    }

    @GetMapping(value = "/v1/vehicles/{id}")
    public ResponseEntity<VehicleCommand> getVehicle(@PathVariable("id") Integer id)
            throws NotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED).body(vehicleService.getVehicle(id));
    }

    @DeleteMapping(value = "/v1/vehicles/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable("id") Integer id)
            throws NotFoundException {
        vehicleService.deleteVehicle(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
