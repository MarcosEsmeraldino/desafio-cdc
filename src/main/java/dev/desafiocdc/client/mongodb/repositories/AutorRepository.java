package dev.desafiocdc.client.mongodb.repositories;

import dev.desafiocdc.client.mongodb.entities.Autor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AutorRepository extends MongoRepository<Autor, String> {

    boolean existsByEmail(String email);
    Optional<Autor> findByEmail(String email);

}
