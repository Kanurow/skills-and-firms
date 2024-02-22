package com.rowland.engineering.company.controller;

import com.rowland.engineering.company.dto.CompanyRequest;
import com.rowland.engineering.company.dto.CompanyResponse;
import com.rowland.engineering.company.dto.UpdateCompanyRequest;
import com.rowland.engineering.company.model.Company;
import com.rowland.engineering.company.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/companies")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping("/get-all-companies")
    public ResponseEntity<List<CompanyResponse>> getAllCompanies() {
        return new ResponseEntity<>(companyService.getAllCompanies(), HttpStatus.OK);
    }

    @PostMapping("/add-company")
    public ResponseEntity<String> addNewCompany(@RequestBody CompanyRequest request){
        companyService.createNewCompany(request);
        return new ResponseEntity<>("Company added successfully", HttpStatus.CREATED);
    }

    @PatchMapping("/update-company/{companyId}")
    public ResponseEntity<String> updateCompany(@PathVariable("companyId") Long companyId,
                                                @RequestBody UpdateCompanyRequest updateCompanyRequest) {
        boolean updated = companyService.updateCompanyDetails(companyId, updateCompanyRequest);
        if (updated)
            return new ResponseEntity<>("Company details successfully updated", HttpStatus.OK);
        return new ResponseEntity<>("Error occurred", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete/{companyId}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long companyId) {
        boolean isDeleted = companyService.deleteCompany(companyId);
        if (isDeleted)
            return new ResponseEntity<>("Successfully deleted", HttpStatus.NO_CONTENT);
        return new ResponseEntity<>("Error! Not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/get-company/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        return new  ResponseEntity<>(companyService.getCompanyById(id), HttpStatus.OK);
    }

}
