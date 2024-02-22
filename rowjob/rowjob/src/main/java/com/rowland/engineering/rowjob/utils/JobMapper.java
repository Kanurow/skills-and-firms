package com.rowland.engineering.rowjob.utils;

import com.rowland.engineering.rowjob.dto.JobDTO;
import com.rowland.engineering.rowjob.external.Company;
import com.rowland.engineering.rowjob.external.Review;
import com.rowland.engineering.rowjob.model.Job;

import java.util.List;

public class JobMapper {
    public static JobDTO mapJobToCompanyDTO(
            Job job,
            Company company,
            List<Review> reviewList
    ){
        JobDTO jobDTO = new JobDTO();
        jobDTO.setJobId(job.getId());
        jobDTO.setJobTitle(job.getJobTitle());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setMinSalary(job.getMinSalary());
        jobDTO.setMaxSalary(job.getMaxSalary());

        jobDTO.setCompany(company);
        jobDTO.setReviews(reviewList);

        return jobDTO;
    }
}
