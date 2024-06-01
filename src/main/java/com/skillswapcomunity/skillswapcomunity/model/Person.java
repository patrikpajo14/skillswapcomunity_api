package com.skillswapcomunity.skillswapcomunity.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String description;
    private String achievements;

    @ManyToOne
    @JoinColumn(name="skill_id")
    private Skill skill;
    private BigDecimal salary;
    private BigDecimal rating;
    private BigDecimal experience;

    @ManyToOne
    @JoinColumn(name="company_id", nullable=false)
    @JsonBackReference
    private Company company;
}
