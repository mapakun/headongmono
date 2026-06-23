package com.haedong.backend.assignment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssignmentService {

    private final AssignmentMapper assignmentMapper;

    public List<VehicleAssignmentView> findAll() {
        return assignmentMapper.findAll();
    }

    public List<SubstituteDriverView> findSubstitutes() {
        return assignmentMapper.findSubstitutes();
    }

    public void upsert(VehicleAssignment assignment) {
        assignmentMapper.upsert(assignment);
    }

    public void delete(String vehicleId) {
        assignmentMapper.delete(vehicleId);
    }
}
