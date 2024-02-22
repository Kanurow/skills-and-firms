package com.rowland.engineering.rowjob.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateJobRequest {
    private String jobTitle;
    private String description;
    private BigDecimal minSalary;
    private BigDecimal maxSalary;
    private String location;
    private Long companyId;
}
