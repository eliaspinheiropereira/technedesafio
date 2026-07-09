package io.github.eliaspinheiropereira.technedesafio.controller;

import io.github.eliaspinheiropereira.technedesafio.dto.request.DisciplinaRequest;
import io.github.eliaspinheiropereira.technedesafio.dto.response.DisciplinaResponse;
import io.github.eliaspinheiropereira.technedesafio.service.DisciplinaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/disciplinas")
@RequiredArgsConstructor
@Slf4j
public class DisciplinaController {

    private final DisciplinaService disciplinaService;

    @PostMapping
    public ResponseEntity<Void> cadastrar(@Valid @RequestBody DisciplinaRequest disciplinaRequest) {
        log.info("POST -> /api/v1/disciplinas - Cadastrando disciplina: {}", disciplinaRequest);
        this.disciplinaService.cadastrarDisciplina(disciplinaRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisciplinaResponse> buscarPorId(@PathVariable Long id) {
        log.info("GET -> /api/v1/disciplinas/{} - Buscando disciplina por ID", id);
        DisciplinaResponse disciplinaResponse = this.disciplinaService.buscarDisciplinaPorId(id);
        return ResponseEntity.ok(disciplinaResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @Valid @RequestBody DisciplinaRequest disciplinaRequest) {
        log.info("PUT -> /api/v1/disciplinas/{} - Atualizando disciplina: {}", id, disciplinaRequest);
        this.disciplinaService.atualizarDisciplina(id, disciplinaRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        log.info("DELETE -> /api/v1/disciplinas/{} - Removendo disciplina", id);
        this.disciplinaService.removerDisciplina(id);
        return ResponseEntity.noContent().build();
    }
}

