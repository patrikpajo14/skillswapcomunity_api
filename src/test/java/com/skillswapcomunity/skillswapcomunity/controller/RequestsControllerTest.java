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
public class RequestsControllerTest extends BaseControllerTest {

    @Test
    void getAllRequests() throws Exception {
        mockMvc.perform(get("/requests/all")
                        .header(HttpHeaders.AUTHORIZATION, getAdminAuthorizationHeader())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().encoding(StandardCharsets.UTF_8))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void getRequestsById() throws Exception {
        mockMvc.perform(get("/requests/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(get("/requests/{id}", 999)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
    @Test
    void updateRequests() throws Exception {
        Map<String, Object> body = new HashMap<>();
        body.put("status", 20);

        mockMvc.perform(MockMvcRequestBuilders.put("/requests/1")
                        .header(HttpHeaders.AUTHORIZATION, getAdminAuthorizationHeader())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())  // Adjusted to use MockMvcResultMatchers
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().encoding(StandardCharsets.UTF_8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(20));
    }

    @Test
    void deleteRequests() throws Exception {
        mockMvc.perform(delete("/requests/1")
                        .header(HttpHeaders.AUTHORIZATION, getAdminAuthorizationHeader())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent());
    }
}
