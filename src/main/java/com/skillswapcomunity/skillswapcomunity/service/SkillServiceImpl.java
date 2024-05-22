package com.skillswapcomunity.skillswapcomunity.service;
import com.skillswapcomunity.skillswapcomunity.model.Skill;
import com.skillswapcomunity.skillswapcomunity.repository.SkillRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SkillServiceImpl implements SkillService {

    private SkillRepository skillRepository;

    @Override
    public List<Skill> getSkills() {
        return skillRepository.findAll();
    }

    @Override
    public Optional<Skill> getSkill(Long id) {
        return skillRepository.findById(id);
    }

    @Override
    public Skill createSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    @Override
    public Skill updateSkill(Skill skill, Long id) {
        Optional<Skill> skillOptional = skillRepository.findById(id);
        if (skillOptional.isPresent()) {
            Skill skillToUpdate = skillOptional.get();
            skillToUpdate.setName(skill.getName());
            return skillRepository.save(skillToUpdate);
        }
        else {
            throw new EntityNotFoundException("Skill with the ID = '"
                    + id + "' not found");
        }
    }

    @Override
    public void deleteSkill(Long id) {
        skillRepository.deleteById(id);
    }
}
