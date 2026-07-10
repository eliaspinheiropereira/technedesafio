package io.github.eliaspinheiropereira.technedesafio.service;

import io.github.eliaspinheiropereira.technedesafio.dto.request.MatriculaRequest;
import io.github.eliaspinheiropereira.technedesafio.dto.request.MatriculaUpdateRequest;
import io.github.eliaspinheiropereira.technedesafio.dto.response.MatriculaResponse;
import io.github.eliaspinheiropereira.technedesafio.exception.AlunoNaoEncontradoException;
import io.github.eliaspinheiropereira.technedesafio.exception.MatriculaException;
import io.github.eliaspinheiropereira.technedesafio.exception.TurmaNaoEncontradoException;
import io.github.eliaspinheiropereira.technedesafio.mapper.MatriculaMapper;
import io.github.eliaspinheiropereira.technedesafio.model.Aluno;
import io.github.eliaspinheiropereira.technedesafio.model.Matricula;
import io.github.eliaspinheiropereira.technedesafio.model.Turma;
import io.github.eliaspinheiropereira.technedesafio.model.enums.StatusMatricula;
import io.github.eliaspinheiropereira.technedesafio.repository.AlunoRepository;
import io.github.eliaspinheiropereira.technedesafio.repository.MatriculaRepository;
import io.github.eliaspinheiropereira.technedesafio.repository.TurmaRepository;
import io.github.eliaspinheiropereira.technedesafio.validator.MatriculaValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MatriculaService {

    private final MatriculaRepository matriculaRepository;
    private final AlunoRepository alunoRepository;
    private final TurmaRepository turmaRepository;
    private final MatriculaMapper matriculaMapper;
    private final MatriculaValidator matriculaValidator;

    @Transactional
    public void cadastrarMatricula(MatriculaRequest dto) {
        this.matriculaValidator.validarTurmasAbertas(dto.turmaId());
        this.matriculaValidator.validarDuplicidadeAluno(dto.alunoId(), dto.turmaId());

        Aluno aluno = getAluno(dto.alunoId());
        Turma turma = getTurma(dto.turmaId());

        Matricula matricula = this.matriculaMapper.toEntity(dto);
        matricula.setAluno(aluno);
        matricula.setTurma(turma);
        matricula.setStatusMatricula(StatusMatricula.PENDENTE);

        this.matriculaRepository.save(matricula);
    }

    @Transactional
    public void atualizarMatricula(UUID id, MatriculaUpdateRequest dto) {
        Matricula matricula = getMatricula(id);
        Matricula atualizarMatricula = this.matriculaMapper.toUpdateEntity(dto, matricula);
        this.matriculaValidator.validarMatricula(atualizarMatricula);
        this.matriculaRepository.save(atualizarMatricula);
    }

    public void removerMatricula(UUID id) {
        Matricula matricula = getMatricula(id);
        this.matriculaRepository.deleteById(matricula.getCodigoMatricula());
    }

    public List<MatriculaResponse> buscarMatriculasPorAluno(String nome) {
        List<Matricula> buscarMatriculaPorNome = getMatriculaPorNomeAluno(nome);
        return buscarMatriculaPorNome.stream().map(this.matriculaMapper::toResponse).toList();
    }

    public List<MatriculaResponse> buscarMatriculasPorTurma(String periodo) {
        List<Matricula> buscarMatriculaPorPeriodo = getMatriculaPorPeriodoTurma(periodo);
        return buscarMatriculaPorPeriodo.stream().map(this.matriculaMapper::toResponse).toList();
    }

    private Aluno getAluno(Long alunoId) {
        return this.alunoRepository.findById(alunoId)
                .orElseThrow(() -> new AlunoNaoEncontradoException("Aluno não encontrado na base de dados."));
    }

    private Turma getTurma(Long turmaId) {
        return this.turmaRepository.findById(turmaId)
                .orElseThrow(() -> new TurmaNaoEncontradoException("Turma não encontrada na base de dados."));
    }

    private List<Matricula> getMatriculaPorNomeAluno(String nome){
        List<Matricula> matricula = this.matriculaRepository.findByAlunoNomeContainingIgnoreCase(nome);

        if(matricula.isEmpty()){
            throw new MatriculaException("Nenhuma matrícula encontrada para o aluno");
        }

        return matricula;
    }

    private List<Matricula> getMatriculaPorPeriodoTurma(String periodo){
        List<Matricula> matricula = this.matriculaRepository.findByTurmaPeriodoContainingIgnoreCase(periodo);

        if(matricula.isEmpty()){
            throw new MatriculaException("Nenhuma matrícula encontrada para a turma");
        }

        return matricula;
    }

    private Matricula getMatricula(UUID id) {
        return this.matriculaRepository.findById(id)
                .orElseThrow(() -> new MatriculaException("Matricula não encontrada na base de dados."));
    }
}




