package dev.desafiocdc.client.mongodb.entities;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Document
public class Categoria {

    @Id
    private String id;

    @Indexed(unique = true)
    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @CreatedDate // gerenciado por @EnableMongoAuditing
    private Instant createdAt;
}
