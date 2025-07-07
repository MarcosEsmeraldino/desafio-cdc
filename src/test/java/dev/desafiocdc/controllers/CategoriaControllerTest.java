package dev.desafiocdc.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.desafiocdc.client.mongodb.entities.Categoria;
import dev.desafiocdc.client.mongodb.repositories.CategoriaRepository;
import dev.desafiocdc.services.CategoriaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CategoriaController.class)
class CategoriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CategoriaService service;

    @MockBean
    private CategoriaRepository repository;

    @MockBean
    private MongoTemplate mongoTemplate;

    @Test
    void shouldReturn200WhenPayloadIsOk() throws Exception {
        var payload = Map.of("nome", "Terror");

        mockMvc.perform(post("/categorias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturn400WhenNomeIsMissing() throws Exception {
        var payload = Map.of();

        mockMvc.perform(post("/categorias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn400WhenNomeIsDuplicated() throws Exception {
        var payload = Map.of("nome", "Terror");

        doReturn(true)
                .when(mongoTemplate)
                .exists(any(), eq(Categoria.class));

        mockMvc.perform(post("/categorias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Nome já cadastrado"));
    }
}