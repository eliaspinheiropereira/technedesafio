package io.github.eliaspinheiropereira.technedesafio.controller;

import io.github.eliaspinheiropereira.technedesafio.dto.request.TurmaRequest;
import io.github.eliaspinheiropereira.technedesafio.dto.response.TurmaResponse;
import io.github.eliaspinheiropereira.technedesafio.service.TurmaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/turmas")
@RequiredArgsConstructor
@Slf4j
public class TurmaController {

    private final TurmaService turmaService;

    @PostMapping
    public ResponseEntity<Void> cadastrar(@Valid @RequestBody TurmaRequest turmaRequest) {
        log.info("POST -> /api/v1/turmas - Cadastrando turma: {}", turmaRequest);
        this.turmaService.cadastrarTurma(turmaRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurmaResponse> buscarPorId(@PathVariable Long id) {
        log.info("GET -> /api/v1/turmas/{} - Buscando turma por ID", id);
        TurmaResponse turmaResponse = this.turmaService.buscarTurmaPorId(id);
        return ResponseEntity.ok(turmaResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @Valid @RequestBody TurmaRequest turmaRequest) {
        log.info("PUT -> /api/v1/turmas/{} - Atualizando turma: {}", id, turmaRequest);
        this.turmaService.atualizarTurma(id, turmaRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        log.info("DELETE -> /api/v1/turmas/{} - Removendo turma", id);
        this.turmaService.removerTurma(id);
        return ResponseEntity.noContent().build();
    }
}

