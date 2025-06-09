package dev.desafiocdc.client.autor.repository;

import dev.desafiocdc.client.autor.entity.Autor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AutorRepository extends MongoRepository<Autor, String> {
}
