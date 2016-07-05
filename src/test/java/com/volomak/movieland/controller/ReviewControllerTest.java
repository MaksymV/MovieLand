package com.volomak.movieland.controller;

import com.volomak.movieland.controller.interceptor.MovielandInterceptor;
import com.volomak.movieland.service.dto.ReviewRequestDto;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ReviewControllerTest.TestConfiguration.class})
@WebAppConfiguration
public class ReviewControllerTest {

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
    public void addReviewTest() throws Exception {
        ReviewRequestDto reviewRequestDto = new ReviewRequestDto();
        reviewRequestDto.setUserId(1L);
        reviewRequestDto.setMoviewId(1L);
        reviewRequestDto.setReview("test1");

        mockMvc.perform(post("/v1/review").header("authToken", "token111")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonConverter.toJson(reviewRequestDto)))
                .andExpect(status().isOk())
                .andDo(print());
    }


    @Configuration
    @ImportResource("classpath:/spring/root-context.xml")
    @EnableWebMvc
    public static class TestConfiguration{
        @Bean
        public ReviewController reviewController(){
            return new ReviewController();
        }

        @Bean
        public MovielandInterceptor movielandInterceptor(){
            return new MovielandInterceptor();
        }
    }
}
