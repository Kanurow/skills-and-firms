package com.rowland.engineering.rowjob.controller;

import com.rowland.engineering.rowjob.dto.CreateJobRequest;
import com.rowland.engineering.rowjob.dto.JobUpdateRequest;
import com.rowland.engineering.rowjob.dto.JobDTO;
import com.rowland.engineering.rowjob.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/jobs")
@RequiredArgsConstructor
public class JobController {
    private final JobService jobService;

    @GetMapping("/get-all-jobs")
    public ResponseEntity<List<JobDTO>> findAllJobs(){
        return ResponseEntity.ok(jobService.findAllJob());
    }

    @PostMapping("/create-job")
    public ResponseEntity<String> createJob(@RequestBody CreateJobRequest jobRequest) {
        jobService.createJob(jobRequest);
        return new ResponseEntity<>("New job opportunity successfully created!", HttpStatus.CREATED);
    }

    @GetMapping("/get-job-{jobId}")
    public ResponseEntity<JobDTO> getJobById(@PathVariable("jobId") Long id) {
        JobDTO jobById = jobService.getJobById(id);
        if (jobById != null)
            return new ResponseEntity<>(jobById, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete-job-{jobId}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long jobId) {
        boolean jobDeleted = jobService.deleteJobById(jobId);
        if (jobDeleted)
            return new ResponseEntity<>("job deleted successfully", HttpStatus.NO_CONTENT);
        return new ResponseEntity<>("Error occurred", HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/update-job")
    public ResponseEntity<String> updateJobInformation(@RequestBody JobUpdateRequest jobUpdateRequest) {
        boolean updatedJob = jobService.updateJob(jobUpdateRequest);
        if (updatedJob)
            return new ResponseEntity<>("Successfully updated!", HttpStatus.ACCEPTED);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
