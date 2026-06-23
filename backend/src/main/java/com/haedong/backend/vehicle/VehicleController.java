package com.haedong.backend.vehicle;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @GetMapping
    public ResponseEntity<List<Vehicle>> findAll() {
        return ResponseEntity.ok(vehicleService.findAll());
    }

    @GetMapping("/{vehicleId}")
    public ResponseEntity<Vehicle> findById(@PathVariable String vehicleId) {
        return ResponseEntity.ok(vehicleService.findById(vehicleId));
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody Vehicle vehicle) {
        vehicleService.create(vehicle);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{vehicleId}")
    public ResponseEntity<Void> update(@PathVariable String vehicleId, @Valid @RequestBody Vehicle vehicle) {
        vehicle.setVehicleId(vehicleId);
        vehicleService.update(vehicle);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<Void> delete(@PathVariable String vehicleId) {
        vehicleService.delete(vehicleId);
        return ResponseEntity.ok().build();
    }
}
