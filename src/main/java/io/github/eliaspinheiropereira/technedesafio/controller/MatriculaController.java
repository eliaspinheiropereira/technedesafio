package io.github.eliaspinheiropereira.technedesafio.controller;

import io.github.eliaspinheiropereira.technedesafio.dto.request.MatriculaRequest;
import io.github.eliaspinheiropereira.technedesafio.dto.request.MatriculaUpdateRequest;
import io.github.eliaspinheiropereira.technedesafio.dto.response.MatriculaResponse;
import io.github.eliaspinheiropereira.technedesafio.service.MatriculaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/matriculas")
@RequiredArgsConstructor
@Slf4j
public class MatriculaController {

    private final MatriculaService matriculaService;

    @PostMapping
    public ResponseEntity<Void> cadastrar(@Valid @RequestBody MatriculaRequest matriculaRequest) {
        log.info("POST -> /api/v1/matriculas - Cadastrando matrícula: aluno ID {} em turma ID {}",
                 matriculaRequest.alunoId(), matriculaRequest.turmaId());
        this.matriculaService.cadastrarMatricula(matriculaRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable UUID id, @Valid @RequestBody MatriculaUpdateRequest matriculaUpdateRequest) {
        log.info("PUT -> /api/v1/matriculas/{} - Atualizando status matrícula para: {}", id, matriculaUpdateRequest.statusMatricula());
        this.matriculaService.atualizarMatricula(id, matriculaUpdateRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable UUID id) {
        log.info("DELETE -> /api/v1/matriculas/{} - Removendo matrícula", id);
        this.matriculaService.removerMatricula(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/aluno/{nome}")
    public ResponseEntity<List<MatriculaResponse>> buscarMatriculasPorAluno(@PathVariable String nome) {
        log.info("GET -> /api/v1/matriculas/aluno/{} - Buscando matrículas do aluno", nome);
        List<MatriculaResponse> matriculaResponse = this.matriculaService.buscarMatriculasPorAluno(nome);
        return ResponseEntity.ok(matriculaResponse);
    }

    @GetMapping("/turma/{periodo}")
    public ResponseEntity<List<MatriculaResponse>> buscarMatriculasPorTurma(@PathVariable String periodo) {
        log.info("GET -> /api/v1/matriculas/turma/{} - Buscando matrículas da turma", periodo);
        List<MatriculaResponse> matriculaResponse = this.matriculaService.buscarMatriculasPorTurma(periodo);
        return ResponseEntity.ok(matriculaResponse);
    }
}



