package br.com.serasa.controller;

import br.com.serasa.DemoApplication;
import br.com.serasa.mock.AffinityMocks;
import br.com.serasa.service.AffinityService;
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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AffinityController.class)
@ContextConfiguration(classes = { DemoApplication.class })
@AutoConfigureMockMvc(addFilters = false)
class AffinityControllerTest {
    @MockBean
    private AffinityService affinityService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    void When_Post_To_Affinity_Expect_Response_Status_Equals_To_201_Created() throws Exception {
        Mockito.when(affinityService.saveDto(Mockito.any())).thenReturn(Mockito.any());

        var mockAffinityDto = AffinityMocks.createAffinityDTOMock();

        mockMvc.perform(
                post("/afinidade")
                        .characterEncoding("utf8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(mockAffinityDto)))
                .andExpect(status().is(HttpStatus.CREATED.value()))
                .andReturn();
    }
}