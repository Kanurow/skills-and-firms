package com.rowland.engineering.company.dto;

import lombok.Data;

@Data
public class UpdateCompanyRequest {
    private String name;
    private String description;
}
