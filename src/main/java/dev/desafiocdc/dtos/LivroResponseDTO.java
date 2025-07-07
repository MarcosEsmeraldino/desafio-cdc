package dev.desafiocdc.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.desafiocdc.client.mongodb.entities.Livro;

import java.math.BigDecimal;
import java.time.LocalDate;

public record LivroResponseDTO(
        String id,
        String titulo,
        String isbn,
        BigDecimal preco,
        Integer numeroPaginas,
        @JsonFormat(pattern="yyyy-MM-dd")
        LocalDate dataPublicacao,
        CategoriaResponseDTO categoria,
        AutorResponseDTO autor
) {
        
        public static LivroResponseDTO from(Livro livro) {
            return new LivroResponseDTO(
                    livro.getId(),
                    livro.getTitulo(),
                    livro.getIsbn(),
                    livro.getPreco(),
                    livro.getNumeroPaginas(),
                    livro.getDataPublicacao(),
                    CategoriaResponseDTO.from(livro.getCategoria()),
                    AutorResponseDTO.from(livro.getAutor())
            );
        }
}
