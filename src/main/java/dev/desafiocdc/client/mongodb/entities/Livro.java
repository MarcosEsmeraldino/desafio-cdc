package dev.desafiocdc.client.mongodb.entities;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Data
@Document
public class Livro {

    @Id
    private String id;

    @NotBlank(message = "Título é obrigatório")
    @Indexed(unique = true)
    private String titulo;

    @NotBlank(message = "ISBN é obrigatório")
    @Indexed(unique = true)
    private String isbn;

    @NotBlank(message = "Resumo é obrigatório")
    @Size(max = 500, message = "Resumo deve ter no máximo 500 caracteres")
    private String resumo;

    private String sumario;

    @NotNull(message = "Preço é obrigatório")
    @DecimalMin(value = "20.0", message = "Preço deve ser maior que 20")
    private BigDecimal preco;

    @NotNull(message = "Número de páginas é obrigatório")
    @Min(value = 100, message = "Número de páginas deve ser maior que 100")
    private Integer numeroPaginas;

    @Future(message = "Data de publicação deve ser futura")
    private LocalDate dataPublicacao;

    @DBRef
    @NotNull(message = "Categoria é obrigatória")
    private Categoria categoria;

    @DBRef
    @NotNull(message = "Autor é obrigatório")
    private Autor autor;

    @CreatedDate // gerenciado por @EnableMongoAuditing
    private Instant createdAt;
}
