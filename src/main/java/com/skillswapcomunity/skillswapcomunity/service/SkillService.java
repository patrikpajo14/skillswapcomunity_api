package com.skillswapcomunity.skillswapcomunity.service;
import com.skillswapcomunity.skillswapcomunity.dto.SkillDto;

import java.util.List;
import java.util.Optional;

public interface SkillService {
    List<SkillDto> getSkills();
    Optional<SkillDto> getSkill(Long id);
    SkillDto createSkill(SkillDto skill);
    SkillDto updateSkill(SkillDto skill, Long id);
    void deleteSkill(Long id);
}
