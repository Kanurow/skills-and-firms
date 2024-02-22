package com.rowland.engineering.rowjob.dto;

import com.rowland.engineering.rowjob.external.Company;
import com.rowland.engineering.rowjob.external.Review;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class JobDTO {
    private Long jobId;
    private String jobTitle;
    private String description;
    private BigDecimal minSalary;
    private BigDecimal maxSalary;
    private String location;

    private Company company;
    private List<Review> reviews;
}
