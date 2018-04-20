package com.ribeirao.cloud.port.adapter.persistence;

import org.springframework.data.repository.CrudRepository;

import com.ribeirao.cloud.domain.Vehicle;

public interface VehicleRepository extends CrudRepository<Vehicle, Integer> {

}
