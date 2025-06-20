package dev.desafiocdc.client.categoria.repositories;

import dev.desafiocdc.client.categoria.entities.Categoria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataMongoTest
@ActiveProfiles("test")
class CategoriaRepositoryTest {

    @Autowired
    private CategoriaRepository repository;

    @BeforeEach
    void cleanup() {
        repository.deleteAll();
    }

    @Test
    void salvaCategoriaUnica() {
        var categoria = new Categoria();
        categoria.setNome("Ação");

        repository.save(categoria);

        assertThat(repository.existsByNome("Ação")).isTrue();
    }

    @Test
    void falhaAoSalvarNomeDuplicado() {
        var categoria = new Categoria();
        categoria.setNome("Terror");

        var categoria2 = new Categoria();
        categoria2.setNome("Terror");

        repository.save(categoria);

        assertThatThrownBy(() -> repository.save(categoria2))
                .isInstanceOf(DuplicateKeyException.class);
    }
}