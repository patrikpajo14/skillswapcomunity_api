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
public class PersonControllerTest extends BaseControllerTest {

    @Test
    void getAllPersons() throws Exception {
        mockMvc.perform(get("/person/all")
                        .header(HttpHeaders.AUTHORIZATION, getAdminAuthorizationHeader())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().encoding(StandardCharsets.UTF_8))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void getPersonById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/person/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void updatePerson() throws Exception {
        Map<String, Object> body = new HashMap<>();
        body.put("name", "Lucija Beba");

        mockMvc.perform(put("/person/2")
                        .header(HttpHeaders.AUTHORIZATION, getAdminAuthorizationHeader())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body))
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().encoding(StandardCharsets.UTF_8))
                .andExpect(jsonPath("$.name").value("Lucija Beba"));
    }

    @Test
    void deletePerson() throws Exception {
        mockMvc.perform(delete("/person/1")
                        .header(HttpHeaders.AUTHORIZATION, getAdminAuthorizationHeader())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent());
    }
}
