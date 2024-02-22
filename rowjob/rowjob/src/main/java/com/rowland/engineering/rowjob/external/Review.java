package com.rowland.engineering.rowjob.external;

import lombok.Data;

@Data
public class Review {
    private Long id;
    private String title;
    private String description;
    private float rating;
}
