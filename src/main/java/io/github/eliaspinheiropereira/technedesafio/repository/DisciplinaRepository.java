package io.github.eliaspinheiropereira.technedesafio.repository;

import io.github.eliaspinheiropereira.technedesafio.model.Curso;
import io.github.eliaspinheiropereira.technedesafio.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
    Optional<Disciplina> findByNome(String nome);
}

