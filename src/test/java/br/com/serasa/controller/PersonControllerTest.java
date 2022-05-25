package br.com.serasa.controller;

import br.com.serasa.DemoApplication;
import br.com.serasa.mock.PersonMocks;
import br.com.serasa.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PersonController.class)
@ContextConfiguration(classes = { DemoApplication.class })
@AutoConfigureMockMvc(addFilters = false)
class PersonControllerTest {
    @MockBean
    private PersonService personService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void When_Post_To_Person_Expect_Response_Status_Equals_To_201_Created() throws Exception {
        Mockito.when(personService.saveDto(Mockito.any())).thenReturn(Mockito.any());

        var mockPersonDto = PersonMocks.createPersonDtoMock();

        mockMvc.perform(
                        post("/pessoa")
                                .characterEncoding("utf8")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(mockPersonDto)))
                .andExpect(status().is(HttpStatus.CREATED.value()))
                .andReturn();
    }

    @Test
    void When_Get_Person_Passing_Id_Expect_NotNull_Values_On_Returned_Json() throws Exception {
        var mockId = 1L;
        var mockPersonDto = Optional.of(PersonMocks.createPersonDtoGetOneMock());
        Mockito.when(personService.findPersonDtoById(mockId)).thenReturn(mockPersonDto);

        mockMvc.perform(
                        get("/pessoa/1")
                                .characterEncoding("utf8")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.nome", is(notNullValue())))
                .andExpect(jsonPath("$.telefone", is(notNullValue())))
                .andExpect(jsonPath("$.idade", is(notNullValue())))
                .andExpect(jsonPath("$.scoreDescricao", is(notNullValue())))
                .andExpect(jsonPath("$.estados", is(notNullValue())))
                .andReturn();

    }

    @Test
    void When_Get_Person_Passing_Id_Expect_204_NoContent() throws Exception {
        var mockId = 1L;
        Mockito.when(personService.findPersonDtoById(mockId)).thenReturn(Optional.empty());

        mockMvc.perform(
                        get("/pessoa/1")
                                .characterEncoding("utf8")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.NO_CONTENT.value()))
                .andReturn();

    }

    @Test
    void When_Get_All_Persons_Expect_NotEmpty_List_And_Not_Null_Values_On_Returned_Json() throws Exception {
        var mockPersonDto = Optional.of(List.of(PersonMocks.createPersonDtoGetAllMock()));
        Mockito.when(personService.findAllPersonsDto()).thenReturn(mockPersonDto);

        mockMvc.perform(
                        get("/pessoa")
                                .characterEncoding("utf8")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.[0].nome", is(notNullValue())))
                .andExpect(jsonPath("$.[0].cidade", is(notNullValue())))
                .andExpect(jsonPath("$.[0].estado", is(notNullValue())))
                .andExpect(jsonPath("$.[0].scoreDescricao", is(notNullValue())))
                .andExpect(jsonPath("$.[0].estados", is(notNullValue())))
                .andReturn();
    }

    @Test
    void When_Get_All_Persons_Expect_204_NoContent() throws Exception {
        Mockito.when(personService.findAllPersonsDto()).thenReturn(Optional.empty());

        mockMvc.perform(
                        get("/pessoa")
                                .characterEncoding("utf8")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.NO_CONTENT.value()))
                .andReturn();
    }
}