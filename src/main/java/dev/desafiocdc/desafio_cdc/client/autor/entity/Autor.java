package dev.desafiocdc.desafio_cdc.client.autor.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Document
public class Autor {

    @Id
    private String id;

    private String nome;
    private String email;
    private String descricao;
    private Instant instante;
}
