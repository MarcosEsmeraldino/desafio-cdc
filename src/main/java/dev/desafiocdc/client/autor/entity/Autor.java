package dev.desafiocdc.client.autor.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

import java.time.Instant;

@Data
public class Autor {

    @Id
    private String id;

    private String nome;
    private String email;
    private String descricao;

    @CreatedDate // gerenciado por @EnableMongoAuditing
    private Instant createdAt;
}
