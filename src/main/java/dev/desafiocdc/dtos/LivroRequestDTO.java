package dev.desafiocdc.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.desafiocdc.client.mongodb.entities.Livro;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record LivroRequestDTO(

        @NotBlank(message = "Título é obrigatório")
        String titulo,

        @NotBlank(message = "ISBN é obrigatório")
        String isbn,

        @NotBlank(message = "Resumo é obrigatório")
        @Size(max = 500, message = "Resumo deve ter no máximo 500 caracteres")
        String resumo,

        String sumario,

        @NotNull(message = "Preço é obrigatório")
        @DecimalMin(value = "20.0", message = "Preço deve ser maior que 20")
        BigDecimal preco,

        @NotNull(message = "Número de páginas é obrigatório")
        @Min(value = 100, message = "Número de páginas deve ser maior que 100")
        Integer numeroPaginas,

        @JsonFormat(pattern="yyyy-MM-dd")
        @Future(message = "Data de publicação deve ser futura")
        LocalDate dataPublicacao,

        @NotBlank(message = "ID Categoria é obrigatória")
        String idCategoria,

        @NotBlank(message = "ID Autor é obrigatório")
        String idAutor
) {

    public Livro toModel() {
        var model = new Livro();
        model.setTitulo(titulo);
        model.setIsbn(isbn);
        model.setResumo(resumo);
        model.setSumario(sumario);
        model.setPreco(preco);
        model.setNumeroPaginas(numeroPaginas);
        model.setDataPublicacao(dataPublicacao);
        return model;
    }
}
