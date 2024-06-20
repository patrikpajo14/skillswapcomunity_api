package com.skillswapcomunity.skillswapcomunity.controller;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
public class SkillControllerTest extends BaseControllerTest {

    @Test
    void getAllSkills() throws Exception {
        mockMvc.perform(get("/skill/all")
                        .header(HttpHeaders.AUTHORIZATION, getAdminAuthorizationHeader())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().encoding(StandardCharsets.UTF_8))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void getSkillById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/skill/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void createSkill() throws Exception {
        Map<String, Object> body = new HashMap<>();
        body.put("name", "Full Stack Developer");

        mockMvc.perform(post("/skill")
                        .header(HttpHeaders.AUTHORIZATION, getAdminAuthorizationHeader())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body))
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().encoding(StandardCharsets.UTF_8))
                .andExpect(jsonPath("$.name").value("Full Stack Developer"));
    }

    @Test
    void updateSkill() throws Exception {
        Map<String, Object> body = new HashMap<>();
        body.put("name", "Next.js developer");

        mockMvc.perform(put("/skill/2")
                        .header(HttpHeaders.AUTHORIZATION, getAdminAuthorizationHeader())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body))
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().encoding(StandardCharsets.UTF_8))
                .andExpect(jsonPath("$.name").value("Next.js developer"));
    }

    @Test
    void deleteSkill() throws Exception {
        mockMvc.perform(delete("/skill/1")
                        .header(HttpHeaders.AUTHORIZATION, getAdminAuthorizationHeader())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent());
    }
}
