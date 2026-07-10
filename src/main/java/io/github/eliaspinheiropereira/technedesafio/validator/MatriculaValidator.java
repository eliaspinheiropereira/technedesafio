package io.github.eliaspinheiropereira.technedesafio.validator;

import io.github.eliaspinheiropereira.technedesafio.exception.MatriculaException;
import io.github.eliaspinheiropereira.technedesafio.exception.TurmaNaoEncontradoException;
import io.github.eliaspinheiropereira.technedesafio.model.Matricula;
import io.github.eliaspinheiropereira.technedesafio.model.Turma;
import io.github.eliaspinheiropereira.technedesafio.model.enums.Status;
import io.github.eliaspinheiropereira.technedesafio.model.enums.StatusMatricula;
import io.github.eliaspinheiropereira.technedesafio.repository.MatriculaRepository;
import io.github.eliaspinheiropereira.technedesafio.repository.TurmaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MatriculaValidator {

    private final TurmaRepository turmaRepository;
    private final MatriculaRepository matriculaRepository;

    public void validarTurmasAbertas(Long id) {
        Turma turma = this.turmaRepository.findById(id)
                .orElseThrow(() -> new TurmaNaoEncontradoException("Turma não encontrada na base de dados."));

        if (!turma.getStatus().equals(Status.ABERTA)) {
            String erro = String.format("Aluno não pode ser matriculado, a turma deve estar aberta, status da turma: %s", turma.getStatus());
            throw new MatriculaException(erro);
        }
    }

    public void validarDuplicidadeAluno(Long alunoId, Long turmaId) {
        Optional<Matricula> matricula = this.matriculaRepository.findByAlunoIdAndTurmaId(alunoId, turmaId);

        if (matricula.isPresent()) {
            throw new MatriculaException("Aluno já possui matrícula cadastrada nesta turma.");
        }
    }

    public void validarMatricula(Matricula matricula) {
        Turma turma = this.turmaRepository.findById(matricula.getTurma().getId())
                .orElseThrow(() -> new TurmaNaoEncontradoException("Turma não encontrada na base de dados."));

        if (matricula.getStatusMatricula().equals(StatusMatricula.CONFIRMADA)) {
            turma.setVagaDisponivel(turma.getVagaDisponivel() - 1);
            this.turmaRepository.save(turma);
        }

        if (matricula.getStatusMatricula().equals(StatusMatricula.CANCELADA)) {
            turma.setVagaDisponivel(turma.getVagaDisponivel() + 1);
            this.turmaRepository.save(turma);
        }
    }
}
