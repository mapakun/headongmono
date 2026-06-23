package com.haedong.backend.assignment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleAssignment {
    private String vehicleId;
    private String driverId1;
    private String driverId2;
    private Long createdAt;
    private Long updatedAt;
    private String updatedBy;
}
