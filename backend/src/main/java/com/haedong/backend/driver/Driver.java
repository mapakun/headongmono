package com.haedong.backend.driver;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Driver {
    @NotBlank
    private String id;
    @NotBlank
    private String name;
    private String phone;
    private String licenseNumber;
    private Long hireDate;
    @NotBlank
    private String status;
    private Long createdAt;
    private Long updatedAt;
    private String updatedBy;
}
