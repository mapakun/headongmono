package com.haedong.backend.vehicle;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VehicleMapper {
    List<Vehicle> findAll();
    Vehicle findById(String vehicleId);
    void insert(Vehicle vehicle);
    void update(Vehicle vehicle);
    void delete(String vehicleId);
}
