package io.github.eliaspinheiropereira.technedesafio.service;

import io.github.eliaspinheiropereira.technedesafio.dto.request.DisciplinaRequest;
import io.github.eliaspinheiropereira.technedesafio.dto.response.DisciplinaResponse;
import io.github.eliaspinheiropereira.technedesafio.exception.CursoNaoEncontradoException;
import io.github.eliaspinheiropereira.technedesafio.exception.DisciplinaNaoEncontradoException;
import io.github.eliaspinheiropereira.technedesafio.mapper.DisciplinaMapper;
import io.github.eliaspinheiropereira.technedesafio.model.Curso;
import io.github.eliaspinheiropereira.technedesafio.model.Disciplina;
import io.github.eliaspinheiropereira.technedesafio.repository.CursoRepository;
import io.github.eliaspinheiropereira.technedesafio.repository.DisciplinaRepository;
import io.github.eliaspinheiropereira.technedesafio.validator.DisciplinaValidator;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DisciplinaService {

    private final DisciplinaRepository disciplinaRepository;
    private final DisciplinaMapper disciplinaMapper;
    private final DisciplinaValidator disciplinaValidator;
    private final CursoRepository cursoRepository;

    public void cadastrarDisciplina(DisciplinaRequest dto){
        this.disciplinaValidator.validar(dto.nome());
        Disciplina disciplina = this.disciplinaMapper.toEntity(dto);
        Curso curso = getCurso(dto);
        disciplina.setCurso(curso);
        this.disciplinaRepository.save(disciplina);
    }

    public void atualizarDisciplina(Long id, DisciplinaRequest dto){
        Disciplina disciplina = getDisciplina(id);
        Disciplina atualizarDisciplina = this.disciplinaMapper.toUpdateEntity(dto, disciplina);
        Curso curso = getCurso(dto);
        atualizarDisciplina.setCurso(curso);
        this.disciplinaRepository.save(atualizarDisciplina);
    }

    public void removerDisciplina(Long id){
        Disciplina disciplina = getDisciplina(id);
        this.disciplinaRepository.deleteById(id);
    }

    public DisciplinaResponse buscarDisciplinaPorId(Long id) {
        Disciplina disciplina = getDisciplina(id);
        return this.disciplinaMapper.toResponse(disciplina);
    }

    private Disciplina getDisciplina(Long id) {
        return this.disciplinaRepository.findById(id)
                .orElseThrow(() -> new DisciplinaNaoEncontradoException("Disciplina não encontrada na base de dados"));
    }

    private Curso getCurso(DisciplinaRequest dto) {
        return cursoRepository.findById(dto.cursoId())
                .orElseThrow(() -> new CursoNaoEncontradoException("Curso não encontrado na base de dados"));
    }

}


