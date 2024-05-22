package com.skillswapcomunity.skillswapcomunity.dto;

import com.skillswapcomunity.skillswapcomunity.model.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class CompanyDto {
    private String companyName;
    private List<Person> usersList;
}
