package br.com.api.controller;

import br.com.api.DemoApplication;
import br.com.api.mock.ScoreMocks;
import br.com.api.service.ScoreService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ScoreController.class)
@ContextConfiguration(classes = { DemoApplication.class })
@AutoConfigureMockMvc(addFilters = false)
class ScoreControllerTest {
    @MockBean
    private ScoreService scoreService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    void When_Post_To_Score_Expect_Response_Status_Equals_To_201_Created() throws Exception {
        Mockito.when(scoreService.saveDto(Mockito.any())).thenReturn(Mockito.any());

        var mockScoreDto = ScoreMocks.createScoreDTOMock();

        mockMvc.perform(
                        post("/score")
                                .characterEncoding("utf8")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(mockScoreDto)))
                .andExpect(status().is(HttpStatus.CREATED.value()))
                .andReturn();
    }
}