package com.rowland.engineering.rowjob.handler;

import com.rowland.engineering.rowjob.exception.JobNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class JobExceptionHandler {
    @ExceptionHandler(JobNotFoundException.class)
    public ProblemDetail handleJobNotFoundException(JobNotFoundException exception){
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getMessage());
    }
}
