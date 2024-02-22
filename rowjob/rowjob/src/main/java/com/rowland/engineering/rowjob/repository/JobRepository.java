package com.rowland.engineering.rowjob.repository;

import com.rowland.engineering.rowjob.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {

}
