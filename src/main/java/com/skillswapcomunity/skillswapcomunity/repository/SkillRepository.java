package com.skillswapcomunity.skillswapcomunity.repository;

import com.skillswapcomunity.skillswapcomunity.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
    Skill findByName(String name);
}
