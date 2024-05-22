package com.skillswapcomunity.skillswapcomunity.dto;

import com.skillswapcomunity.skillswapcomunity.model.Company;
import com.skillswapcomunity.skillswapcomunity.model.Skill;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class PersonDto {
    private String name;
    private String email;
    private String phone;
    private String description;
    private String achievements;
    private Skill skill;
    private BigDecimal salary;
    private BigDecimal rating;
    private BigDecimal experience;
    private Company company;
}
