package io.github.eliaspinheiropereira.technedesafio.service;

import io.github.eliaspinheiropereira.technedesafio.dto.request.AlunoRequest;
import io.github.eliaspinheiropereira.technedesafio.dto.response.AlunoResponse;
import io.github.eliaspinheiropereira.technedesafio.exception.AlunoNaoEncontradoException;
import io.github.eliaspinheiropereira.technedesafio.mapper.AlunoMapper;
import io.github.eliaspinheiropereira.technedesafio.model.Aluno;
import io.github.eliaspinheiropereira.technedesafio.repository.AlunoRepository;
import io.github.eliaspinheiropereira.technedesafio.validator.AlunoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final AlunoValidator alunoValidator;
    private final AlunoMapper alunoMapper;

    public void cadastrarAluno(AlunoRequest dto){
        this.alunoValidator.validar(dto.cpf());
        Aluno aluno = this.alunoMapper.toEntity(dto);
        aluno.setDataCadastro(LocalDate.now());
        this.alunoRepository.save(aluno);
    }

    public void atualizarAluno(Long id, AlunoRequest dto){
        Aluno aluno = getAluno(id);
        Aluno alunoAtualizado = this.alunoMapper.toUpdateAluno(dto, aluno);
        this.alunoRepository.save(alunoAtualizado);
    }

    public void removerAluno(Long id){
        Aluno aluno = getAluno(id);
        this.alunoRepository.deleteById(id);
    }

    public AlunoResponse buscarAlunoPorId(Long id) {
        Aluno aluno = getAluno(id);
        return this.alunoMapper.toResponse(aluno);
    }

    private Aluno getAluno(Long id) {
        return this.alunoRepository.findById(id)
                .orElseThrow(() -> new AlunoNaoEncontradoException("Aluno não encontrado na base de dados."));
    }
}
