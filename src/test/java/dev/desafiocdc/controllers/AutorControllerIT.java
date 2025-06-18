package dev.desafiocdc.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.desafiocdc.client.autor.entities.Autor;
import dev.desafiocdc.client.autor.repositories.AutorRepository;
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
public class AutorControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AutorRepository repository;

    @Autowired
    private ObjectMapper mapper;

    @BeforeEach
    void cleanup() {
        repository.deleteAll();
    }

    @Test
    void criaAutorComDadosValidos() throws Exception {
        var payload = Map.of(
                "nome", "Teste",
                "email", "teste@exemplo.com",
                "descricao", "Uma descrição válida"
        );

        mockMvc.perform(post("/autores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(payload)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.email").value("teste@exemplo.com"))
                .andExpect(jsonPath("$.instante").exists());

        var autor = repository.findByEmail("teste@exemplo.com").orElseThrow();
        assertThat(autor.getCreatedAt()).isBeforeOrEqualTo(Instant.now());
    }

    @Test
    void naoPermiteEmailDuplicado() throws Exception {
        var autor = new Autor();
        autor.setNome("Fulano");
        autor.setEmail("fulano@exemplo.com");
        autor.setDescricao("Descrição");

        repository.save(autor);

        var payload = Map.of(
                "nome", "Outro Fulano",
                "email", "fulano@exemplo.com",
                "descricao", "Outra descrição"
        );

        mockMvc.perform(post("/autores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(payload)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("E-mail já cadastrado"));
    }
}
