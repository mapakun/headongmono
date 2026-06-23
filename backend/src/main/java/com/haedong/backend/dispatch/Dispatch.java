package com.haedong.backend.dispatch;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dispatch {
    private String dispatchId;
    @NotBlank
    private String driverId;
    @NotBlank
    private String vehicleId;
    @NotNull
    private Long dispatchDate;
    private String shift;
    @NotBlank
    private String status;
    private Long createdAt;
    private Long updatedAt;
    private String updatedBy;
}
