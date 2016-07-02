package com.volomak.movieland.controller;

import com.volomak.movieland.service.dto.MovieSearchRequestDto;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={MovieControllerTest.TestConfiguration.class})
@WebAppConfiguration
public class MovieControllerTest {

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
    public void getMovieByIdTest() throws Exception {
        mockMvc.perform(get("/v1/movie/{id}", 5L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.originalName").value("intouchables"));
    }

    @Test
    public void searchTestByYear() throws Exception {
        MovieSearchRequestDto movieSearchRequestDto = new MovieSearchRequestDto();
        movieSearchRequestDto.setYear(2001);

        mockMvc.perform(get("/v1/movie/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonConverter.toJson(movieSearchRequestDto))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].originalName").value("sen to chihiro no kamikakushi"));
    }

    @Test
    public void searchTestByName() throws Exception {
        MovieSearchRequestDto movieSearchRequestDto = new MovieSearchRequestDto();
        movieSearchRequestDto.setOriginalName("the green");

        mockMvc.perform(get("/v1/movie/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonConverter.toJson(movieSearchRequestDto))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].originalName").value("the green mile"));
    }

    @Configuration
    @ImportResource("classpath:/spring/root-context.xml")
    @EnableWebMvc
    public static class TestConfiguration{
        @Bean
        public MovieController movieController(){
            return new MovieController();
        }
    }
}
