package com.rowland.engineering.rowjob.service;

import com.rowland.engineering.rowjob.dto.CreateJobRequest;
import com.rowland.engineering.rowjob.dto.JobUpdateRequest;
import com.rowland.engineering.rowjob.dto.JobDTO;
import com.rowland.engineering.rowjob.exception.JobNotFoundException;
import com.rowland.engineering.rowjob.external.Company;
import com.rowland.engineering.rowjob.external.Review;
import com.rowland.engineering.rowjob.model.Job;
import com.rowland.engineering.rowjob.repository.JobRepository;
import com.rowland.engineering.rowjob.utils.JobMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService{
    private final JobRepository jobRepository;
    private final RestTemplate restTemplate;

    @Override
    public List<JobDTO> findAllJob() {
        List<Job> allJobs = jobRepository.findAll();
        log.info("JOBS-- "+ allJobs);

        return allJobs.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public void createJob(CreateJobRequest jobRequest) {
        Job newJob = new Job();
        newJob.setJobTitle(jobRequest.getJobTitle());
        newJob.setDescription(jobRequest.getDescription());
        newJob.setLocation(jobRequest.getLocation());
        newJob.setMinSalary(jobRequest.getMinSalary());
        newJob.setMinSalary(jobRequest.getMaxSalary());
        newJob.setCompanyId(jobRequest.getCompanyId());
        jobRepository.save(newJob);
    }

    @Override
    public JobDTO getJobById(Long jobId) {
        Job foundJob = jobRepository.findById(jobId).orElseThrow(() -> new JobNotFoundException("Job with ID: " + jobId + " not found"));
        return convertToDTO(foundJob);

    }

    @Override
    public boolean deleteJobById(Long jobId) {
        try {
            jobRepository.deleteById(jobId);
            return true;
        } catch (Exception e){
            log.error(e.getMessage());
            return false;
        }

    }

    @Override
    public boolean updateJob(JobUpdateRequest jobUpdateRequest) {
        Job foundJob = jobRepository.findById(jobUpdateRequest.getId()).orElseThrow(() -> new JobNotFoundException("Job with ID: " + jobUpdateRequest.getId() + " not found"));
        try {
            foundJob.setJobTitle(jobUpdateRequest.getJobTitle());
            foundJob.setDescription(jobUpdateRequest.getDescription());
            foundJob.setLocation(jobUpdateRequest.getLocation());
            foundJob.setMinSalary(jobUpdateRequest.getMinSalary());
            foundJob.setMaxSalary(jobUpdateRequest.getMaxSalary());
            jobRepository.save(foundJob);
            return true;
        } catch (Exception e) {
            return false;
        }


    }
    private JobDTO convertToDTO(Job job) {

        Company company = restTemplate.getForObject(
                    "http://COMPANY-SERVICE:8081/api/v1/companies/get-company/" + job.getCompanyId(),
                    Company.class);
        log.info(company + " COMPANY IN DTO ");
        ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange("http://REVIEW-SERVICE:8083/api/v1/reviews?companyId=" + job.getCompanyId(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Review>>() {
                }
        );
        log.info(reviewResponse+"REVIEW RESPONSE IN DTO");

        List<Review> reviews = reviewResponse.getBody();

        return JobMapper.mapJobToCompanyDTO(job, company, reviews);
    }


}
