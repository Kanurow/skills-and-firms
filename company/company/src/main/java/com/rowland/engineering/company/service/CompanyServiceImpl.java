package com.rowland.engineering.company.service;

import com.rowland.engineering.company.dto.CompanyRequest;
import com.rowland.engineering.company.dto.CompanyResponse;
import com.rowland.engineering.company.dto.UpdateCompanyRequest;
import com.rowland.engineering.company.exception.CompanyNotFoundException;
import com.rowland.engineering.company.model.Company;
import com.rowland.engineering.company.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService{
    private final CompanyRepository companyRepository;
    @Override
    public List<CompanyResponse> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        return companies.stream().map((company) -> {
            CompanyResponse companyResponse = new CompanyResponse();
            companyResponse.setId(company.getId());
            companyResponse.setName(company.getName());
            companyResponse.setDescription(company.getDescription());
            return companyResponse;
        }).collect(Collectors.toList());
    }

    @Override
    public boolean updateCompanyDetails(Long companyId, UpdateCompanyRequest updateCompanyRequest) {
        Company companyToBeUpdated = companyRepository.findById(companyId).orElseThrow(() -> new CompanyNotFoundException("Company with ID: " + companyId + " not found"));
        try {
            companyToBeUpdated.setDescription(updateCompanyRequest.getDescription());
            companyToBeUpdated.setName(updateCompanyRequest.getName());
            companyRepository.save(companyToBeUpdated);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }

    }

    @Override
    public void createNewCompany(CompanyRequest request) {
        Company newCompany = new Company();
        newCompany.setName(request.getName());
        newCompany.setDescription(request.getDescription());
        companyRepository.save(newCompany);
    }

    @Override
    public boolean deleteCompany(Long companyId) {

        return false;
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElseThrow(
                () -> new CompanyNotFoundException("Company with ID: " + id + " not found")
        );
    }

}
