package io.github.eliaspinheiropereira.technedesafio.repository;

import io.github.eliaspinheiropereira.technedesafio.model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, UUID> {

    Optional<Matricula> findByAlunoIdAndTurmaId(Long alunoId, Long turmaId);
    List<Matricula> findByAlunoNomeContainingIgnoreCase(String nome);
    List<Matricula> findByTurmaPeriodoContainingIgnoreCase(String periodo);
}

