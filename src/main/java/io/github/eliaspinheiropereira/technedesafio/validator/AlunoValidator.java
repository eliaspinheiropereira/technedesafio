package io.github.eliaspinheiropereira.technedesafio.validator;

import io.github.eliaspinheiropereira.technedesafio.exception.AlunoCadastradoException;
import io.github.eliaspinheiropereira.technedesafio.model.Aluno;
import io.github.eliaspinheiropereira.technedesafio.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AlunoValidator {

    private final AlunoRepository alunoRepository;

    public void validar(String cpf){
        Optional<Aluno> buscarAluno = this.alunoRepository.findByCpf(cpf);

        if(buscarAluno.isPresent()){
            throw new AlunoCadastradoException("Aluno já se encontra cadastrado no sistema.");
        }
    }

}
