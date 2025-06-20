package dev.desafiocdc.client.categoria.repositories;

import dev.desafiocdc.client.categoria.entities.Categoria;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CategoriaRepository extends MongoRepository<Categoria, String> {

    boolean existsByNome(String nome);
    Optional<Categoria> findByNome(String nome);

}
