package dev.desafiocdc.client.autor.repositories;

import dev.desafiocdc.client.autor.entities.Autor;
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
class AutorRepositoryTest {

    @Autowired
    private AutorRepository repository;

    @BeforeEach
    void cleanup() {
        repository.deleteAll();
    }

    @Test
    void salvaAutorUnico() {
        var autor = new Autor();
        autor.setNome("Maria");
        autor.setEmail("maria@exemplo.com");
        autor.setDescricao("Descrição");

        repository.save(autor);

        assertThat(repository.existsByEmail("maria@exemplo.com")).isTrue();
    }

    @Test
    void falhaAoSalvarEmailDuplicado() {
        var autor = new Autor();
        autor.setNome("Pedro");
        autor.setEmail("pedro@exemplo.com");
        autor.setDescricao("Desc");

        var autor2 = new Autor();
        autor2.setNome("Pedro2");
        autor2.setEmail("pedro@exemplo.com");
        autor2.setDescricao("Outra");

        repository.save(autor);

        assertThatThrownBy(() -> repository.save(autor2))
                .isInstanceOf(DuplicateKeyException.class);
    }
}