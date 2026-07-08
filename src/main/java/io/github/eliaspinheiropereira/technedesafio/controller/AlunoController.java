package io.github.eliaspinheiropereira.technedesafio.controller;

import io.github.eliaspinheiropereira.technedesafio.dto.request.AlunoRequest;
import io.github.eliaspinheiropereira.technedesafio.dto.response.AlunoResponse;
import io.github.eliaspinheiropereira.technedesafio.service.AlunoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/alunos")
@RequiredArgsConstructor
@Slf4j
public class AlunoController {

    private final AlunoService alunoService;


    @PostMapping
    public ResponseEntity<Void> cadastrar(@Valid @RequestBody AlunoRequest alunoRequest) {
        log.info("POST -> /api/v1/alunos - Cadastrando alunos: {}", alunoRequest);
        this.alunoService.cadastrarAluno(alunoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponse> buscarPorId(@PathVariable Long id) {
        log.info("GET -> /api/v1/alunos/{} - Buscando alunos por ID", id);
        AlunoResponse alunoResponse = this.alunoService.buscarAlunoPorId(id);
        return ResponseEntity.ok(alunoResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @Valid @RequestBody AlunoRequest alunoRequest) {
        log.info("PUT -> /api/v1/alunoss/{} - Atualizando aluno: {}", id, alunoRequest);
        this.alunoService.atualizarAluno(id, alunoRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        log.info("DELETE -> /api/v1/alunos/{} - Removendo aluno", id);
        this.alunoService.removerAluno(id);
        return ResponseEntity.noContent().build();
    }
}

