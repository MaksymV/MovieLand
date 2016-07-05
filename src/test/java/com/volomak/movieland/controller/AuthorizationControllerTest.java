package com.volomak.movieland.controller;

import com.volomak.movieland.service.dto.UserCredentials;
import com.volomak.movieland.util.JsonConverter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AuthorizationControllerTest.TestConfiguration.class})
@WebAppConfiguration
public class AuthorizationControllerTest {

    private static final JsonConverter jsonConverter = new JsonConverter();

    @Resource
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void authorizeTest() throws Exception {
        UserCredentials userCredentials = new UserCredentials();
        userCredentials.setLogin("дэрил брайант");
        userCredentials.setPassword("exodus");
        mockMvc.perform(post("/v1/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonConverter.toJson(userCredentials)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.login").value("дэрил брайант"))
        .andDo(print());

        mockMvc.perform(post("/v1/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonConverter.toJson(userCredentials)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.login").value("дэрил брайант"))
                .andDo(print());

        UserCredentials userCredentials2 = new UserCredentials();
        userCredentials2.setLogin("айда дэвис");
        userCredentials2.setPassword("pepsi1");

        mockMvc.perform(post("/v1/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonConverter.toJson(userCredentials2)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.login").value("айда дэвис"))
                .andDo(print());
    }

    @Test
    public void authorizeTestFailure() throws Exception {
        UserCredentials userCredentials = new UserCredentials();
        userCredentials.setLogin("дэрил брайант");
        userCredentials.setPassword("blahblahblah");
        mockMvc.perform(post("/v1/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonConverter.toJson(userCredentials)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Configuration
    @ImportResource("classpath:/spring/root-context.xml")
    @EnableWebMvc
    public static class TestConfiguration{
        @Bean
        public AuthorizationController authorizationController(){
            return new AuthorizationController();
        }
    }
}





