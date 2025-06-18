package dev.desafiocdc.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.desafiocdc.client.autor.repositories.AutorRepository;
import dev.desafiocdc.services.AutorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = AutorController.class)
class AutorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AutorService autorService;

    @MockBean
    private AutorRepository autorRepository;

    @Test
    void shouldReturn400WhenNameIsMissing() throws Exception {
        var payload = Map.of(
                "email", "autor@email.com",
                "descricao", "Descrição válida"
        );

        mockMvc.perform(post("/autores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn400WhenEmailIsInvalid() throws Exception {
        var payload = Map.of(
                "nome", "Autor",
                "email", "email-invalido",
                "descricao", "Descrição válida"
        );

        mockMvc.perform(post("/autores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Formato de email inválido"));
    }

    @Test
    void shouldReturn400WhenDescriptionTooLong() throws Exception {
        String longDescription = "a".repeat(401);

        var payload = Map.of(
                "nome", "Autor",
                "email", "autor@email.com",
                "descricao", longDescription
        );

        mockMvc.perform(post("/autores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Descrição deve ter no máximo 400 caracteres"));
    }
}