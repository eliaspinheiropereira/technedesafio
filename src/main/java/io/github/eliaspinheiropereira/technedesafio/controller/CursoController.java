package io.github.eliaspinheiropereira.technedesafio.controller;

import io.github.eliaspinheiropereira.technedesafio.dto.request.CursoRequest;
import io.github.eliaspinheiropereira.technedesafio.dto.response.CursoResponse;
import io.github.eliaspinheiropereira.technedesafio.service.CursoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cursos")
@RequiredArgsConstructor
@Slf4j
public class CursoController {

    private final CursoService cursoService;

    @PostMapping
    public ResponseEntity<Void> cadastrar(@Valid @RequestBody CursoRequest cursoRequest) {
        log.info("POST -> /api/v1/cursos - Cadastrando curso: {}", cursoRequest);
        this.cursoService.cadastrarCurso(cursoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoResponse> buscarPorId(@PathVariable Long id) {
        log.info("GET -> /api/v1/cursos/{} - Buscando curso por ID", id);
        CursoResponse cursoResponse = this.cursoService.buscarCursoPorId(id);
        return ResponseEntity.ok(cursoResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @Valid @RequestBody CursoRequest cursoRequest) {
        log.info("PUT -> /api/v1/cursos/{} - Atualizando curso: {}", id, cursoRequest);
        this.cursoService.atualizarCurso(id, cursoRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        log.info("DELETE -> /api/v1/cursos/{} - Removendo curso", id);
        this.cursoService.removerCurso(id);
        return ResponseEntity.noContent().build();
    }
}

