package com.skillswapcomunity.skillswapcomunity.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
public class CompanyControllerTest extends BaseControllerTest {

    @Test
    void getAllCompanys() throws Exception {
        mockMvc.perform(get("/company/all")
                        .header(HttpHeaders.AUTHORIZATION, getAdminAuthorizationHeader())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().encoding(StandardCharsets.UTF_8))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void getCompanyById() throws Exception {
        mockMvc.perform(get("/company/1")
                        .header(HttpHeaders.AUTHORIZATION, getAdminAuthorizationHeader())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().encoding(StandardCharsets.UTF_8))
                .andExpect(jsonPath("$.companyName").value("TVZ Company"));
    }

    @Test
    void updateCompany() throws Exception {
        Map<String, Object> body = new HashMap<>();
        body.put("companyName", "NewCompanyName");

        mockMvc.perform(put("/company/1")
                        .header(HttpHeaders.AUTHORIZATION, getAdminAuthorizationHeader())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body))
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().encoding(StandardCharsets.UTF_8))
                .andExpect(jsonPath("$.companyName").value("NewCompanyName"));
    }

    @Test
    void deleteCompany() throws Exception {
        mockMvc.perform(delete("/skill/1")
                        .header(HttpHeaders.AUTHORIZATION, getAdminAuthorizationHeader())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent());
    }
}
