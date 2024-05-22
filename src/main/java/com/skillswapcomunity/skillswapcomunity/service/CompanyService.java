package com.skillswapcomunity.skillswapcomunity.service;
import com.skillswapcomunity.skillswapcomunity.model.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    List<Company> getCompanys();
    Optional<Company> getCompany(Long id);
    Company createCompany(Company position);
    Company updateCompany(Company position, Long id);
    void deleteCompany(Long id);
}
