package io.github.eliaspinheiropereira.technedesafio.validator;

import io.github.eliaspinheiropereira.technedesafio.exception.CursoCadastradoException;
import io.github.eliaspinheiropereira.technedesafio.model.Disciplina;
import io.github.eliaspinheiropereira.technedesafio.repository.DisciplinaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DisciplinaValidator {

    private final DisciplinaRepository disciplinaRepository;

    public void validar(String nome){
        Optional<Disciplina> buscarCurso = this.disciplinaRepository.findByNome(nome);

        if(buscarCurso.isPresent()){
            throw new CursoCadastradoException("Disciplina já se encontra cadastrado no sistema.");
        }
    }
}
