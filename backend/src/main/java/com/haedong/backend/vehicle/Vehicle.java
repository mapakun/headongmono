package com.haedong.backend.vehicle;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Vehicle {
    @NotBlank
    private String vehicleId;
    private String routeNo;
    private String vehicleType;
    @NotBlank
    private String status;
    private Long createdAt;
    private Long updatedAt;
    private String updatedBy;
}
