package com.skillswapcomunity.skillswapcomunity.controller;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class AuthControllerTest extends BaseControllerTest {

    @Test
    void register() throws Exception {
        Map<String,Object> body = new HashMap<>();
        body.put("email", "test@gmail.com");
        body.put("password", "test");
        body.put("fullName", "Test User");

        mockMvc.perform(
                        post("/auth/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(body))
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().encoding(StandardCharsets.UTF_8))
                .andExpect(jsonPath("$.email").value("test@gmail.com"));
    }

    @Test
    void login() throws Exception {
        Map<String,Object> body = new HashMap<>();
        body.put("email", "test@gmail.com");
        body.put("password", "test");

        mockMvc.perform(
                        post("/auth/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(body))
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().encoding(StandardCharsets.UTF_8))
                .andExpect(jsonPath("$.token").isNotEmpty());
    }
}
