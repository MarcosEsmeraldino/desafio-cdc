package dev.desafiocdc.services;

import dev.desafiocdc.client.mongodb.entities.Livro;
import dev.desafiocdc.client.mongodb.repositories.AutorRepository;
import dev.desafiocdc.client.mongodb.repositories.CategoriaRepository;
import dev.desafiocdc.client.mongodb.repositories.LivroRepository;
import dev.desafiocdc.dtos.LivroRequestDTO;
import dev.desafiocdc.dtos.LivroResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Slf4j
@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public LivroResponseDTO cadastrarLivro(LivroRequestDTO request) {
        log.debug("service cadastrarLivro: {}", request);

        Assert.state(!livroRepository.existsByTitulo(request.titulo()), "Título já cadastrado");
        Assert.state(!livroRepository.existsByIsbn(request.isbn()), "ISBN já cadastrado");

        var autor = autorRepository.findById(request.idAutor())
                .orElseThrow(() -> new IllegalStateException("Autor não encontrado"));

        var categoria = categoriaRepository.findById(request.idCategoria())
                .orElseThrow(() -> new IllegalStateException("Categoria não encontrada"));


        var livro = request.toModel();
        livro.setAutor(autor);
        livro.setCategoria(categoria);

        livroRepository.save(livro);

        return LivroResponseDTO.from(livro);
    }

    public List<LivroResponseDTO> listarLivros() {
        return livroRepository.findAll().stream()
                .map(LivroResponseDTO::from)
                .toList();
    }
}
