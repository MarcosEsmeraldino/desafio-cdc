package dev.desafiocdc.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.desafiocdc.services.LivroService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = LivroController.class)
class LivroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private LivroService service;

    @Test
    void shouldReturn200WhenPayloadIsOk() throws Exception {
        var payload = Map.of(
                "titulo", "Dia a dia com os Puritanos Ingleses",
                "isbn", "978.85.263.1312-5",
                "resumo", "Resumindo...",
                "sumario", "Sumário...",
                "preco", 44.5,
                "numeroPaginas", 590,
                "dataPublicacao", "2125-01-01",
                "idCategoria", "6862ab7571319a3a48544fe6",
                "idAutor", "6862ae0fbe2b85959603bd8f"
        );

        mockMvc.perform(post("/livros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturn400WhenTituloIsMissing() throws Exception {
        var payload = Map.of(
                "isbn", "978.85.263.1312-5",
                "resumo", "Resumindo...",
                "sumario", "Sumário...",
                "preco", 44.5,
                "numeroPaginas", 590,
                "dataPublicacao", "2125-01-01",
                "idCategoria", "6862ab7571319a3a48544fe6",
                "idAutor", "6862ae0fbe2b85959603bd8f"
        );

        mockMvc.perform(post("/livros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn400WhenTituloIsDuplicated() throws Exception {
        var payload = Map.of(
                "titulo", "Dia a dia com os Puritanos Ingleses",
                "isbn", "978.85.263.1312-5",
                "resumo", "Resumindo...",
                "sumario", "Sumário...",
                "preco", 44.5,
                "numeroPaginas", 590,
                "dataPublicacao", "2125-01-01",
                "idCategoria", "6862ab7571319a3a48544fe6",
                "idAutor", "6862ae0fbe2b85959603bd8f"
        );

        doThrow(new IllegalStateException("Título já cadastrado"))
                .when(service)
                .cadastrarLivro(any());

        mockMvc.perform(post("/livros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Título já cadastrado"));
    }

    @Test
    void shouldReturn400WhenIsbnIsMissing() throws Exception {
        var payload = Map.of(
                "titulo", "Dia a dia com os Puritanos Ingleses",
                "resumo", "Resumindo...",
                "sumario", "Sumário...",
                "preco", 44.5,
                "numeroPaginas", 590,
                "dataPublicacao", "2125-01-01",
                "idCategoria", "6862ab7571319a3a48544fe6",
                "idAutor", "6862ae0fbe2b85959603bd8f"
        );

        mockMvc.perform(post("/livros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn400WhenIsbnIsDuplicated() throws Exception {
        var payload = Map.of(
                "titulo", "Dia a dia com os Puritanos Ingleses",
                "isbn", "978.85.263.1312-5",
                "resumo", "Resumindo...",
                "sumario", "Sumário...",
                "preco", 44.5,
                "numeroPaginas", 590,
                "dataPublicacao", "2125-01-01",
                "idCategoria", "6862ab7571319a3a48544fe6",
                "idAutor", "6862ae0fbe2b85959603bd8f"
        );

        doThrow(new IllegalStateException("ISBN já cadastrado"))
                .when(service)
                .cadastrarLivro(any());

        mockMvc.perform(post("/livros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("ISBN já cadastrado"));
    }

    @Test
    void shouldReturn400WhenResumoExceedsMaxLength() throws Exception {
        var longResumo = "a".repeat(501);
        var payload = Map.of(
                "titulo", "Livro com Resumo Longo",
                "isbn", "978.85.263.1312-6",
                "resumo", longResumo,
                "sumario", "Sumário...",
                "preco", 44.5,
                "numeroPaginas", 590,
                "dataPublicacao", "2125-01-01",
                "idCategoria", "6862ab7571319a3a48544fe6",
                "idAutor", "6862ae0fbe2b85959603bd8f"
        );

        mockMvc.perform(post("/livros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn400WhenPrecoIsBelowMinimum() throws Exception {
        var payload = Map.of(
                "titulo", "Livro com Preço Baixo",
                "isbn", "978.85.263.1312-7",
                "resumo", "Resumo válido",
                "sumario", "Sumário...",
                "preco", 19.99,
                "numeroPaginas", 590,
                "dataPublicacao", "2125-01-01",
                "idCategoria", "6862ab7571319a3a48544fe6",
                "idAutor", "6862ae0fbe2b85959603bd8f"
        );

        mockMvc.perform(post("/livros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn400WhenNumeroPaginasIsBelowMinimum() throws Exception {
        var payload = Map.of(
                "titulo", "Livro com Poucas Páginas",
                "isbn", "978.85.263.1312-8",
                "resumo", "Resumo válido",
                "sumario", "Sumário...",
                "preco", 44.5,
                "numeroPaginas", 99,
                "dataPublicacao", "2125-01-01",
                "idCategoria", "6862ab7571319a3a48544fe6",
                "idAutor", "6862ae0fbe2b85959603bd8f"
        );

        mockMvc.perform(post("/livros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn400WhenDataPublicacaoIsNotInFuture() throws Exception {
        var payload = Map.of(
                "titulo", "Livro com Data Passada",
                "isbn", "978.85.263.1312-9",
                "resumo", "Resumo válido",
                "sumario", "Sumário...",
                "preco", 44.5,
                "numeroPaginas", 590,
                "dataPublicacao", "2000-01-01",
                "idCategoria", "6862ab7571319a3a48544fe6",
                "idAutor", "6862ae0fbe2b85959603bd8f"
        );

        mockMvc.perform(post("/livros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn400WhenIdCategoriaIsMissing() throws Exception {
        var payload = Map.of(
                "titulo", "Livro sem Categoria",
                "isbn", "978.85.263.1313-0",
                "resumo", "Resumo válido",
                "sumario", "Sumário...",
                "preco", 44.5,
                "numeroPaginas", 590,
                "dataPublicacao", "2125-01-01",
                "idAutor", "6862ae0fbe2b85959603bd8f"
        );

        mockMvc.perform(post("/livros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn400WhenIdAutorIsMissing() throws Exception {
        var payload = Map.of(
                "titulo", "Livro sem Autor",
                "isbn", "978.85.263.1313-1",
                "resumo", "Resumo válido",
                "sumario", "Sumário...",
                "preco", 44.5,
                "numeroPaginas", 590,
                "dataPublicacao", "2125-01-01",
                "idCategoria", "6862ab7571319a3a48544fe6"
        );

        mockMvc.perform(post("/livros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isBadRequest());
    }
}