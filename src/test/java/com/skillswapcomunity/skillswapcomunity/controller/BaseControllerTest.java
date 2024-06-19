package com.skillswapcomunity.skillswapcomunity.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.junit.jupiter.api.BeforeEach;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public abstract class BaseControllerTest {

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext wac;

    protected MockMvc mockMvc;

    @PostConstruct
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .defaultResponseCharacterEncoding(StandardCharsets.UTF_8)
                .build();
    }

    protected String getAdminAuthorizationHeader() throws Exception {
        Map<String, Object> body = new HashMap<>();
        body.put("email", "test@gmail.com");
        body.put("password", "test");

        MvcResult mvcResult = mockMvc.perform(
                post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body))
        ).andReturn();

        String jwt = JsonPath.read(mvcResult.getResponse().getContentAsString(), "$.token");

        return "Bearer " + jwt;
    }

    protected String getUserAuthorizationHeader() throws Exception {
        Map<String, Object> body = new HashMap<>();
        body.put("email", "test@gmail.com");
        body.put("password", "test");

        MvcResult mvcResult = mockMvc.perform(
                post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body))
        ).andReturn();

        String jwt = JsonPath.read(mvcResult.getResponse().getContentAsString(), "$.token");

        return "Bearer " + jwt;
    }
}
