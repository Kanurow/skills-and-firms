package com.rowland.engineering.company.repository;

import com.rowland.engineering.company.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
