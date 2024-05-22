package com.skillswapcomunity.skillswapcomunity.service;
import com.skillswapcomunity.skillswapcomunity.dto.SkillDto;
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
    public List<SkillDto> getSkills() {
        return skillRepository.findAll()
                .stream()
                .map(this::convertSkillToSkillDto)
                .toList();
    }

    @Override
    public Optional<SkillDto> getSkill(Long id) {
        Optional<Skill> skillOptional = skillRepository.findById(id);
        if(skillOptional.isPresent()) {
            return Optional.of(convertSkillToSkillDto(skillOptional.get()));
        }
        else {
            return Optional.empty();
        }
    }

    @Override
    public SkillDto createSkill(SkillDto skillDto) {
        return convertSkillToSkillDto(
                skillRepository.save(convertSkillDtoToSkill(skillDto)));
    }

    @Override
    public SkillDto updateSkill(SkillDto skillDto, Long id) {
        Optional<Skill> skillOptional = skillRepository.findById(id);
        if (skillOptional.isPresent()) {
            Skill skillToUpdate = skillOptional.get();
            skillToUpdate.setName(skillDto.getName());
            return convertSkillToSkillDto(skillRepository.save(skillToUpdate));
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

    private SkillDto convertSkillToSkillDto(Skill skill) {
        return new SkillDto(skill.getName());
    }

    private Skill convertSkillDtoToSkill(SkillDto skillDto) {
        String skillName = skillDto.getName();
        Skill skill = skillRepository.findByName(skillName);
        return  skill;
    }
}

