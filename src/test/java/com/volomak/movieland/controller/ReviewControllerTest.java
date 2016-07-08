package com.volomak.movieland.controller;

import com.jayway.jsonpath.JsonPath;
import com.volomak.movieland.controller.exception.GlobalControllerExceptionHandler;
import com.volomak.movieland.controller.interceptor.MovielandInterceptor;
import com.volomak.movieland.service.dto.ReviewRequestDto;
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
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;
import java.security.Principal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ReviewControllerTest.TestConfiguration.class})
@WebAppConfiguration
public class ReviewControllerTest {

    private static final JsonConverter jsonConverter = new JsonConverter();
    private static final String UUID1 = "067e6162-3b6f-4ae2-a171-2470b63dff00";
    private static final String UUID2 = "54947df8-0e9e-4471-a2f9-9af509fb5889";

    @Resource
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void addReviewTest() throws Exception {
        ReviewRequestDto reviewRequestDto = new ReviewRequestDto();
        reviewRequestDto.setUserId(1L);
        reviewRequestDto.setMoviewId(1L);
        reviewRequestDto.setReview("test1");

        UserCredentials userCredentials = new UserCredentials();
        userCredentials.setLogin("daryl.bryant94@example.com");
        userCredentials.setPassword("exodus");

        final MvcResult result = mockMvc.perform(post("/v1/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonConverter.toJson(userCredentials)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.login").value("daryl.bryant94@example.com"))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();
        response.getContentAsString();
        String token1 = JsonPath.read(response.getContentAsString(), "$.token");

        mockMvc.perform(post("/v1/review").header("authToken", token1).principal(new Principal() {
            @Override
            public String getName() {
                return "daryl.bryant94@example.com";
            }
        })
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonConverter.toJson(reviewRequestDto)))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test//(expected = NestedServletException.class)
    public void addReviewTestFail() throws Exception {
        ReviewRequestDto reviewRequestDto = new ReviewRequestDto();
        reviewRequestDto.setUserId(1L);
        reviewRequestDto.setMoviewId(1L);
        reviewRequestDto.setReview("test1");

        UserCredentials userCredentials = new UserCredentials();
        userCredentials.setLogin("dennis.craig82@example.com");
        userCredentials.setPassword("coldbeer");

        final MvcResult result = mockMvc.perform(post("/v1/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonConverter.toJson(userCredentials)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.login").value("dennis.craig82@example.com"))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();
        response.getContentAsString();
        String token1 = JsonPath.read(response.getContentAsString(), "$.token");

        mockMvc.perform(post("/v1/review").header("authToken", token1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonConverter.toJson(reviewRequestDto)))
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());

    }

    @Configuration
    @ImportResource("classpath:/spring/root-context.xml")
    @EnableWebMvc
    public static class TestConfiguration extends WebMvcConfigurerAdapter
    {
        @Bean
        public AuthorizationController authorizationController(){
            return new AuthorizationController();
        }

        @Bean
        public ReviewController reviewController(){
            return new ReviewController();
        }

        @Bean
        public MovielandInterceptor movielandInterceptor(){
            return new MovielandInterceptor();
        }

        @Override
        public void addInterceptors(InterceptorRegistry registry){
            registry.addInterceptor(movielandInterceptor()).addPathPatterns("/v1/**");
        }

        @Bean
        public GlobalControllerExceptionHandler globalControllerExceptionHandler(){
            return new GlobalControllerExceptionHandler();
        }

    }


}
