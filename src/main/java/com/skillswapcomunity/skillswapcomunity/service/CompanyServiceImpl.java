package com.skillswapcomunity.skillswapcomunity.service;
import com.skillswapcomunity.skillswapcomunity.model.Company;
import com.skillswapcomunity.skillswapcomunity.repository.CompanyRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository skillRepository;

    @Override
    public List<Company> getCompanys() {
        return skillRepository.findAll();
    }

    @Override
    public Optional<Company> getCompany(Long id) {
        return skillRepository.findById(id);
    }

    @Override
    public Company createCompany(Company company) {
        return skillRepository.save(company);
    }

    @Override
    public Company updateCompany(Company company, Long id) {
        Optional<Company> companyOptional = skillRepository.findById(id);
        if (companyOptional.isPresent()) {
            Company companyToUpdate = companyOptional.get();
            companyToUpdate.setCompanyName(company.getCompanyName());
            companyToUpdate.setUsersList(company.getUsersList());
            return skillRepository.save(companyToUpdate);
        }
        else {
            throw new EntityNotFoundException("Company with the ID = '"
                    + id + "' not found");
        }
    }

    @Override
    public void deleteCompany(Long id) {
        skillRepository.deleteById(id);
    }
}
