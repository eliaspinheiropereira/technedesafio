package io.github.eliaspinheiropereira.technedesafio.controller;

import io.github.eliaspinheiropereira.technedesafio.dto.request.DisciplinaRequest;
import io.github.eliaspinheiropereira.technedesafio.dto.response.DisciplinaResponse;
import io.github.eliaspinheiropereira.technedesafio.service.DisciplinaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Disciplinas", description = "API para gerenciar disciplinas")
public class DisciplinaController {

    private final DisciplinaService disciplinaService;

    @PostMapping
    @Operation(summary = "Cadastrar nova disciplina", description = "Cria uma nova disciplina na base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Disciplina cadastrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(schema = @Schema()))
    })
    public ResponseEntity<Void> cadastrar(@Valid @RequestBody DisciplinaRequest disciplinaRequest) {
        log.info("POST -> /api/v1/disciplinas - Cadastrando disciplina: {}", disciplinaRequest);
        this.disciplinaService.cadastrarDisciplina(disciplinaRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar disciplina por ID", description = "Busca uma disciplina específica pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Disciplina encontrada", content = @Content(schema = @Schema(implementation = DisciplinaResponse.class))),
            @ApiResponse(responseCode = "404", description = "Disciplina não encontrada", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(schema = @Schema()))
    })
    public ResponseEntity<DisciplinaResponse> buscarPorId(
            @Parameter(description = "ID da disciplina a ser buscada", example = "1")
            @PathVariable Long id) {
        log.info("GET -> /api/v1/disciplinas/{} - Buscando disciplina por ID", id);
        DisciplinaResponse disciplinaResponse = this.disciplinaService.buscarDisciplinaPorId(id);
        return ResponseEntity.ok(disciplinaResponse);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar disciplina", description = "Atualiza uma disciplina existente na base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Disciplina atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Disciplina não encontrada", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(schema = @Schema()))
    })
    public ResponseEntity<Void> atualizar(
            @Parameter(description = "ID da disciplina a ser atualizada", example = "1")
            @PathVariable Long id, @Valid @RequestBody DisciplinaRequest disciplinaRequest) {
        log.info("PUT -> /api/v1/disciplinas/{} - Atualizando disciplina: {}", id, disciplinaRequest);
        this.disciplinaService.atualizarDisciplina(id, disciplinaRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover disciplina", description = "Remove uma disciplina da base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Disciplina removida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Disciplina não encontrada", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(schema = @Schema()))
    })
    public ResponseEntity<Void> remover(
            @Parameter(description = "ID da disciplina a ser removida", example = "1")
            @PathVariable Long id) {
        log.info("DELETE -> /api/v1/disciplinas/{} - Removendo disciplina", id);
        this.disciplinaService.removerDisciplina(id);
        return ResponseEntity.noContent().build();
    }
}

