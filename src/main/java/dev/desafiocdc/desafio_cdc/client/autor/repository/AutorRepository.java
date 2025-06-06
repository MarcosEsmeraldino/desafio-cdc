package dev.desafiocdc.desafio_cdc.client.autor.repository;

import dev.desafiocdc.desafio_cdc.client.autor.entity.Autor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AutorRepository extends MongoRepository<Autor, String> {
}
