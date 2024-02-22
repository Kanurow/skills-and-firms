package com.rowland.engineering.company.service;

import com.rowland.engineering.company.dto.CompanyRequest;
import com.rowland.engineering.company.dto.CompanyResponse;
import com.rowland.engineering.company.dto.UpdateCompanyRequest;
import com.rowland.engineering.company.model.Company;

import java.util.List;

public interface CompanyService {
    List<CompanyResponse> getAllCompanies();

    boolean updateCompanyDetails(Long companyId, UpdateCompanyRequest updateCompanyRequest);

    void createNewCompany(CompanyRequest request);

    boolean deleteCompany(Long companyId);

    Company getCompanyById(Long id);
}
