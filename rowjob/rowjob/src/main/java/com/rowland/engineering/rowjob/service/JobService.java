package com.rowland.engineering.rowjob.service;

import com.rowland.engineering.rowjob.dto.CreateJobRequest;
import com.rowland.engineering.rowjob.dto.JobUpdateRequest;
import com.rowland.engineering.rowjob.dto.JobDTO;

import java.util.List;

public interface JobService {
    List<JobDTO> findAllJob();
    void createJob(CreateJobRequest jobRequest);
    JobDTO getJobById(Long jobId);
    boolean deleteJobById(Long jobId);
    boolean updateJob(JobUpdateRequest jobUpdateRequest);
}
