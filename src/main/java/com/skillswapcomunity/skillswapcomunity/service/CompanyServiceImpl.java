package com.skillswapcomunity.skillswapcomunity.service;
import com.skillswapcomunity.skillswapcomunity.dto.CompanyDto;
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

    private CompanyRepository companyRepository;

    @Override
    public List<CompanyDto> getCompanys() {
        return companyRepository.findAll()
                .stream()
                .map(this::convertCompanyToCompanyDto)
                .toList();
    }

    @Override
    public Optional<CompanyDto> getCompany(Long id) {
        Optional<Company> skillOptional = companyRepository.findById(id);
        if(skillOptional.isPresent()) {
            return Optional.of(convertCompanyToCompanyDto(skillOptional.get()));
        }
        else {
            return Optional.empty();
        }
    }

    @Override
    public CompanyDto createCompany(CompanyDto companyDto) {
        return convertCompanyToCompanyDto(
                companyRepository.save(convertCompanyDtoToCompany(companyDto)));
    }

    @Override
    public CompanyDto updateCompany(CompanyDto companyDto, Long id) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isPresent()) {
            Company companyToUpdate = companyOptional.get();
            companyToUpdate.setCompanyName(companyDto.getCompanyName());
            companyToUpdate.setUsersList(companyDto.getUsersList());
            return convertCompanyToCompanyDto(companyRepository.save(companyToUpdate));
        }
        else {
            throw new EntityNotFoundException("Company with the ID = '"
                    + id + "' not found");
        }
    }

    @Override
    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }

    private CompanyDto convertCompanyToCompanyDto(Company company) {
        return new CompanyDto(company.getCompanyName(), company.getUsersList());
    }

    private Company convertCompanyDtoToCompany(CompanyDto companyDto) {
        String companyName = companyDto.getCompanyName();
        Company company = companyRepository.findByCompanyName(companyName);
        if (company == null) {
            company = new Company();
            company.setCompanyName(companyName);
        }
        return  company;
    }
}
