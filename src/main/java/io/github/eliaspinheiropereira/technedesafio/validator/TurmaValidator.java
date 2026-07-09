package io.github.eliaspinheiropereira.technedesafio.validator;

import io.github.eliaspinheiropereira.technedesafio.dto.request.TurmaRequest;
import io.github.eliaspinheiropereira.technedesafio.exception.TurmaCadastradoException;
import io.github.eliaspinheiropereira.technedesafio.model.Turma;
import io.github.eliaspinheiropereira.technedesafio.repository.TurmaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TurmaValidator {

    private final TurmaRepository turmaRepository;

    public void validar(TurmaRequest turmaRequest) {
        Optional<Turma> buscarTurma = this.turmaRepository.findByPeriodo(turmaRequest.periodo());

        if (buscarTurma.isPresent()) {
            throw new TurmaCadastradoException("Turma com período '" + turmaRequest.periodo() + "' já se encontra cadastrada no sistema.");
        }
    }
}

