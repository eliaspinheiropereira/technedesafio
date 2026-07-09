package io.github.eliaspinheiropereira.technedesafio.service;

import io.github.eliaspinheiropereira.technedesafio.dto.request.CursoRequest;
import io.github.eliaspinheiropereira.technedesafio.dto.response.CursoResponse;
import io.github.eliaspinheiropereira.technedesafio.exception.CursoNaoEncontradoException;
import io.github.eliaspinheiropereira.technedesafio.mapper.CursoMapper;
import io.github.eliaspinheiropereira.technedesafio.model.Curso;
import io.github.eliaspinheiropereira.technedesafio.repository.CursoRepository;
import io.github.eliaspinheiropereira.technedesafio.validator.CursoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepository cursoRepository;
    private final CursoMapper cursoMapper;
    private final CursoValidator cursoValidator;

    public void cadastrarCurso(CursoRequest dto){
        this.cursoValidator.validar(dto.nome());
        Curso curso = this.cursoMapper.toEntity(dto);
        this.cursoRepository.save(curso);
    }

    public void atualizarCurso(Long id, CursoRequest dto){
        Curso curso = getCurso(id);
        Curso atualizarCurso = this.cursoMapper.toUpdateEntity(dto, curso);
        this.cursoRepository.save(atualizarCurso);
    }

    public void removerCurso(Long id){
        Curso curso = getCurso(id);
        this.cursoRepository.deleteById(id);
    }

    public CursoResponse buscarCursoPorId(Long id) {
        Curso curso = getCurso(id);
        return this.cursoMapper.toResponse(curso);
    }

    private Curso getCurso(Long id) {
        return this.cursoRepository.findById(id)
                .orElseThrow(() -> new CursoNaoEncontradoException("Curso não encontrado na base de dados"));
    }

}
