package dev.desafiocdc.client.mongodb.repositories;

import dev.desafiocdc.client.mongodb.entities.Categoria;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CategoriaRepository extends MongoRepository<Categoria, String> {

    boolean existsByNome(String nome);
    Optional<Categoria> findByNome(String nome);

}
