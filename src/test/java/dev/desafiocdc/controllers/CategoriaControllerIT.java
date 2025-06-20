package dev.desafiocdc.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.desafiocdc.client.categoria.entities.Categoria;
import dev.desafiocdc.client.categoria.repositories.CategoriaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoriaControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoriaRepository repository;

    @Autowired
    private ObjectMapper mapper;

    @BeforeEach
    void cleanup() {
        repository.deleteAll();
    }

    @Test
    void criaCategoriaComDadosValidos() throws Exception {
        var payload = Map.of(
                "nome", "Terror"
        );

        mockMvc.perform(post("/categorias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(payload)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.nome").value("Terror"));

        var categoria = repository.findByNome("Terror").orElseThrow();
        assertThat(categoria.getCreatedAt()).isBeforeOrEqualTo(Instant.now());
    }

    @Test
    void naoPermiteNomeDuplicado() throws Exception {
        var categoria = new Categoria();
        categoria.setNome("Aventura");

        repository.save(categoria);

        var payload = Map.of(
                "nome", "Aventura"
        );

        mockMvc.perform(post("/categorias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(payload)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Nome já cadastrado"));
    }
}
