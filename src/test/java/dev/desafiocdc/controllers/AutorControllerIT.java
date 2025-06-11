package dev.desafiocdc.controllers;

import dev.desafiocdc.client.autor.repository.AutorRepository;
import dev.desafiocdc.dtos.AutorResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AutorControllerIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AutorRepository repository;

    @Test
    void shouldCreateAuthorSuccessfully() {
        var payload = Map.of(
                "nome", "Fulano",
                "email", "fulano@email.com",
                "descricao", "Descrição válida"
        );

        ResponseEntity<AutorResponseDTO> response = restTemplate.postForEntity("/autores", payload, AutorResponseDTO.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().id()).isNotNull();
        assertThat(response.getBody().instante()).isNotNull();
        assertThat(response.getBody().nome()).isNotNull();
        assertThat(response.getBody().email()).isNotNull();
        assertThat(response.getBody().descricao()).isNotNull();
    }
}
