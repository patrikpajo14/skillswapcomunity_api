package com.skillswapcomunity.skillswapcomunity.service;
import com.skillswapcomunity.skillswapcomunity.dto.CompanyDto;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    List<CompanyDto> getCompanys();
    Optional<CompanyDto> getCompany(Long id);
    CompanyDto createCompany(CompanyDto company);
    CompanyDto updateCompany(CompanyDto company, Long id);
    void deleteCompany(Long id);
}
