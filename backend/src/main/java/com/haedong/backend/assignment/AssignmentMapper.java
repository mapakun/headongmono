package com.haedong.backend.assignment;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AssignmentMapper {
    List<VehicleAssignmentView> findAll();
    List<SubstituteDriverView> findSubstitutes();
    void upsert(VehicleAssignment assignment);
    void delete(String vehicleId);
}
