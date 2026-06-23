package com.haedong.backend.assignment;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
@RequiredArgsConstructor
public class AssignmentController {

    private final AssignmentService assignmentService;

    @GetMapping
    public ResponseEntity<List<VehicleAssignmentView>> findAll() {
        return ResponseEntity.ok(assignmentService.findAll());
    }

    @GetMapping("/substitutes")
    public ResponseEntity<List<SubstituteDriverView>> findSubstitutes() {
        return ResponseEntity.ok(assignmentService.findSubstitutes());
    }

    @PutMapping("/{vehicleId}")
    public ResponseEntity<Void> upsert(@PathVariable String vehicleId, @Valid @RequestBody VehicleAssignment assignment) {
        assignment.setVehicleId(vehicleId);
        assignmentService.upsert(assignment);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<Void> delete(@PathVariable String vehicleId) {
        assignmentService.delete(vehicleId);
        return ResponseEntity.ok().build();
    }
}
