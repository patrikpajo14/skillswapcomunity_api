package com.skillswapcomunity.skillswapcomunity.controller;

import com.skillswapcomunity.skillswapcomunity.dto.CompanyDto;
import com.skillswapcomunity.skillswapcomunity.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/company")
@AllArgsConstructor
public class CompanyController {

    private CompanyService companyService;

    @GetMapping("/all")
    public List<CompanyDto> getAllCompanys() {
        return companyService.getCompanys();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> getCompanyById(@PathVariable long id) {
        Optional<CompanyDto> companyOptional = companyService.getCompany(id);
        if (companyOptional.isPresent()) {
            return ResponseEntity.ok(companyOptional.get());
        }
        else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<CompanyDto> createCompany(@RequestBody CompanyDto company) {
        return new ResponseEntity<>(
                companyService.createCompany(company),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyDto> updateCompany(@RequestBody CompanyDto company, @PathVariable Long id)
    {
        try {
            CompanyDto updatedCompany = companyService.updateCompany(company, id);
            return new ResponseEntity<>(updatedCompany, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }
}