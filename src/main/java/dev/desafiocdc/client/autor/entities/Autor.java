package dev.desafiocdc.client.autor.entities;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Document
public class Autor {

    @Id
    private String id;

    @NotBlank(message = "Nome é obrigatório")
    String nome;

    @Indexed(unique = true)
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Formato de email inválido")
    String email;

    @NotBlank(message = "Descrição é obrigatória")
    @Size(max = 400, message = "Descrição deve ter no máximo 400 caracteres")
    String descricao;

    @CreatedDate // gerenciado por @EnableMongoAuditing
    private Instant createdAt;
}
