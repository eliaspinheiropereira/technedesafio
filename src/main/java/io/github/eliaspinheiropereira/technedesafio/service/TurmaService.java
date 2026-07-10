package io.github.eliaspinheiropereira.technedesafio.service;

import io.github.eliaspinheiropereira.technedesafio.dto.request.TurmaRequest;
import io.github.eliaspinheiropereira.technedesafio.dto.response.TurmaResponse;
import io.github.eliaspinheiropereira.technedesafio.exception.DisciplinaNaoEncontradoException;
import io.github.eliaspinheiropereira.technedesafio.exception.TurmaNaoEncontradoException;
import io.github.eliaspinheiropereira.technedesafio.mapper.TurmaMapper;
import io.github.eliaspinheiropereira.technedesafio.model.Disciplina;
import io.github.eliaspinheiropereira.technedesafio.model.Turma;
import io.github.eliaspinheiropereira.technedesafio.repository.DisciplinaRepository;
import io.github.eliaspinheiropereira.technedesafio.repository.TurmaRepository;
import io.github.eliaspinheiropereira.technedesafio.validator.TurmaValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TurmaService {

    private final TurmaRepository turmaRepository;
    private final DisciplinaRepository disciplinaRepository;
    private final TurmaMapper turmaMapper;
    private final TurmaValidator turmaValidator;

    public void cadastrarTurma(TurmaRequest dto) {
        this.turmaValidator.validar(dto);
        Turma turma = this.turmaMapper.toEntity(dto);

        for (Long disciplinaId : dto.disciplinaIds()) {
            Disciplina disciplina = getDisciplina(disciplinaId);
            turma.getDisciplinas().add(disciplina);
        }

        turma.setVagaDisponivel(dto.limiteVaga());
        this.turmaRepository.save(turma);
    }

    public TurmaResponse buscarTurmaPorId(Long id) {
        Turma buscarTurma = getTurma(id);
        return this.turmaMapper.toResponse(buscarTurma);
    }

    public void atualizarTurma(Long id, TurmaRequest dto) {
        Turma turma = getTurma(id);
        Turma atualizarTurma = this.turmaMapper.toUpdateEntity(dto, turma);

        atualizarTurma.getDisciplinas().clear();
        for (Long disciplinaId : dto.disciplinaIds()) {
            Disciplina disciplina = getDisciplina(disciplinaId);
            atualizarTurma.getDisciplinas().add(disciplina);
        }

        this.turmaRepository.save(atualizarTurma);
    }

    public void removerTurma(Long id) {
        Turma turma = getTurma(id);
        this.turmaRepository.deleteById(turma.getId());
    }

    private Turma getTurma(Long id) {
        return this.turmaRepository.findById(id)
                .orElseThrow(() -> new TurmaNaoEncontradoException("Turma não encontrada na base de dados."));
    }

    private Disciplina getDisciplina(Long disciplinaId){
        return this.disciplinaRepository.findById(disciplinaId)
                .orElseThrow(() -> new DisciplinaNaoEncontradoException("Disciplina não encontrada na base de dados."));
    }
}
