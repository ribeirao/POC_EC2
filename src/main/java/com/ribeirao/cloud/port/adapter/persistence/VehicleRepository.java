package com.ribeirao.cloud.port.adapter.persistence;

import com.ribeirao.cloud.domain.Vehicle;
import org.springframework.data.repository.CrudRepository;

public interface VehicleRepository extends CrudRepository<Vehicle, Integer> {

}
