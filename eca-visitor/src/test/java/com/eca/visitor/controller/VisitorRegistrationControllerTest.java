package com.eca.visitor.controller;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import utils.JsonUtils;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource("classpath:application-test.yaml")
@ActiveProfiles("test")
public class VisitorRegistrationControllerTest {

    public static final String REG_URL_PATH = "/v1/visitor/registration";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JsonUtils jsonUtils;



    @Test
    void checkVisitorRegistration() throws Exception {
        var json = IOUtils.toString(Objects.requireNonNull(this.getClass().getResourceAsStream("/visitorRegistrationRequest.json")),
                StandardCharsets.UTF_8);
        mvc.perform(MockMvcRequestBuilders
                        .post(REG_URL_PATH)
                        .content(json)
                      //  .accept(MediaType.APPLICATION_JSON_VALUE))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("$.error").doesNotExist())
                .andExpect(jsonPath("$.userData.emailId",is(equalTo("TestUser@gmail.com"))))
                .andExpect(jsonPath("$.userData.firstName",is(equalTo("TestUserFirstName"))));
    }

    @Test
    void checkVisitorInvalidRegistration() throws Exception {
        var json = IOUtils.toString(Objects.requireNonNull(this.getClass().getResourceAsStream("/visitorRegistrationInvalidRequest.json")),
                StandardCharsets.UTF_8);
        mvc.perform(MockMvcRequestBuilders
                        .post(REG_URL_PATH)
                        .content(json)
                        //  .accept(MediaType.APPLICATION_JSON_VALUE))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(jsonPath("$.error").exists());
    }


}
