package com.haedong.backend.driver;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drivers")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;

    @GetMapping
    public ResponseEntity<List<Driver>> findAll() {
        return ResponseEntity.ok(driverService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Driver> findById(@PathVariable String id) {
        return ResponseEntity.ok(driverService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody Driver driver) {
        driverService.create(driver);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @Valid @RequestBody Driver driver) {
        driver.setId(id);
        driverService.update(driver);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        driverService.delete(id);
        return ResponseEntity.ok().build();
    }
}
