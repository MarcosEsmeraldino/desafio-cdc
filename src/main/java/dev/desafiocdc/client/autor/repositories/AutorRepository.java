package dev.desafiocdc.client.autor.repositories;

import dev.desafiocdc.client.autor.entities.Autor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AutorRepository extends MongoRepository<Autor, String> {

    boolean existsByEmail(String email);
    Optional<Autor> findByEmail(String email);

}
