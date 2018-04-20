package com.ribeirao.cloud.port.adapter.persistence;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ribeirao.cloud.domain.VehicleType;

public interface VehicleTypeRepository extends CrudRepository<VehicleType, Integer> {

    Optional<VehicleType> findOneByName(String name);
}
