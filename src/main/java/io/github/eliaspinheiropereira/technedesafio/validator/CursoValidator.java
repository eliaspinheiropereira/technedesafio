package io.github.eliaspinheiropereira.technedesafio.validator;

import io.github.eliaspinheiropereira.technedesafio.exception.CursoCadastradoException;
import io.github.eliaspinheiropereira.technedesafio.model.Curso;
import io.github.eliaspinheiropereira.technedesafio.repository.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CursoValidator {

    private final CursoRepository cursoRepository;

    public void validar(String nome){
        Optional<Curso> buscarCurso = this.cursoRepository.findByNome(nome);

        if(buscarCurso.isPresent()){
            throw new CursoCadastradoException("Curso já se encontra cadastrado no sistema.");
        }
    }
}
