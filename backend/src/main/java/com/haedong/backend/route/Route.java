package com.haedong.backend.route;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Route {
    @NotBlank
    private String routeNo;
    @NotBlank
    private String routeName;
    private Integer totalVehicles;
    @NotBlank
    private String status;
    private Long createdAt;
    private Long updatedAt;
    private String updatedBy;
}
