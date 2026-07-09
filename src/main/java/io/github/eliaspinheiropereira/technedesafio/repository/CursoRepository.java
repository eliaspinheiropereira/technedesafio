package io.github.eliaspinheiropereira.technedesafio.repository;

import io.github.eliaspinheiropereira.technedesafio.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    Optional<Curso> findByNome(String nome);
}

