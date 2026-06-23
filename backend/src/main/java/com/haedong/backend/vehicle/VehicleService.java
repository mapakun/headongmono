package com.haedong.backend.vehicle;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleMapper vehicleMapper;

    public List<Vehicle> findAll() {
        return vehicleMapper.findAll();
    }

    public Vehicle findById(String vehicleId) {
        return vehicleMapper.findById(vehicleId);
    }

    public void create(Vehicle vehicle) {
        vehicleMapper.insert(vehicle);
    }

    public void update(Vehicle vehicle) {
        vehicleMapper.update(vehicle);
    }

    public void delete(String vehicleId) {
        vehicleMapper.delete(vehicleId);
    }
}
