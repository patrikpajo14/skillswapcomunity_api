package com.skillswapcomunity.skillswapcomunity.controller;

import com.skillswapcomunity.skillswapcomunity.dto.SkillDto;
import com.skillswapcomunity.skillswapcomunity.service.SkillService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/skill")
@AllArgsConstructor
public class SkillController {

    private SkillService skillService;

    @GetMapping("/all")
    public List<SkillDto> getAllSkills() {
        return skillService.getSkills();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SkillDto> getSkillById(@PathVariable long id) {
        Optional<SkillDto> skillOptional = skillService.getSkill(id);
        if (skillOptional.isPresent()) {
            return ResponseEntity.ok(skillOptional.get());
        }
        else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<SkillDto> createSkill(@RequestBody SkillDto skill) {
        return new ResponseEntity<>(
                skillService.createSkill(skill),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<SkillDto> updateSkill(@RequestBody SkillDto skill, @PathVariable Long id)
    {
        try {
            SkillDto updatedSkill = skillService.updateSkill(skill, id);
            return new ResponseEntity<>(updatedSkill, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSkill(@PathVariable Long id) {
        try {
            skillService.deleteSkill(id);
            return ResponseEntity.noContent().build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}