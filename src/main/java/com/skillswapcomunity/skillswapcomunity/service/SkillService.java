package com.skillswapcomunity.skillswapcomunity.service;
import com.skillswapcomunity.skillswapcomunity.model.Skill;

import java.util.List;
import java.util.Optional;

public interface SkillService {
    List<Skill> getSkills();
    Optional<Skill> getSkill(Long id);
    Skill createSkill(Skill position);
    Skill updateSkill(Skill position, Long id);
    void deleteSkill(Long id);
}
