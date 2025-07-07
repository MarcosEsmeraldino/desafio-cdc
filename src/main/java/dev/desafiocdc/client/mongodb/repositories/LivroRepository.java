package dev.desafiocdc.client.mongodb.repositories;

import dev.desafiocdc.client.mongodb.entities.Livro;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LivroRepository extends MongoRepository<Livro, String> {

    boolean existsByTitulo(String titulo);
    boolean existsByIsbn(String isbn);

}
