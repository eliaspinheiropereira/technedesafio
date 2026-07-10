package io.github.eliaspinheiropereira.technedesafio.controller;

import io.github.eliaspinheiropereira.technedesafio.dto.request.TurmaRequest;
import io.github.eliaspinheiropereira.technedesafio.dto.response.TurmaResponse;
import io.github.eliaspinheiropereira.technedesafio.service.TurmaService;
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
@RequestMapping("/api/v1/turmas")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Turmas", description = "API para gerenciar turmas")
public class TurmaController {

    private final TurmaService turmaService;

    @PostMapping
    @Operation(summary = "Cadastrar nova turma", description = "Cria uma nova turma na base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Turma cadastrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(schema = @Schema()))
    })
    public ResponseEntity<Void> cadastrar(@Valid @RequestBody TurmaRequest turmaRequest) {
        log.info("POST -> /api/v1/turmas - Cadastrando turma: {}", turmaRequest);
        this.turmaService.cadastrarTurma(turmaRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar turma por ID", description = "Busca uma turma específica pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Turma encontrada", content = @Content(schema = @Schema(implementation = TurmaResponse.class))),
            @ApiResponse(responseCode = "404", description = "Turma não encontrada", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(schema = @Schema()))
    })
    public ResponseEntity<TurmaResponse> buscarPorId(
            @Parameter(description = "ID da turma a ser buscada", example = "1")
            @PathVariable Long id) {
        log.info("GET -> /api/v1/turmas/{} - Buscando turma por ID", id);
        TurmaResponse turmaResponse = this.turmaService.buscarTurmaPorId(id);
        return ResponseEntity.ok(turmaResponse);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar turma", description = "Atualiza uma turma existente na base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Turma atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Turma não encontrada", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(schema = @Schema()))
    })
    public ResponseEntity<Void> atualizar(
            @Parameter(description = "ID da turma a ser atualizada", example = "1")
            @PathVariable Long id, @Valid @RequestBody TurmaRequest turmaRequest) {
        log.info("PUT -> /api/v1/turmas/{} - Atualizando turma: {}", id, turmaRequest);
        this.turmaService.atualizarTurma(id, turmaRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover turma", description = "Remove uma turma da base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Turma removida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Turma não encontrada", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(schema = @Schema()))
    })
    public ResponseEntity<Void> remover(
            @Parameter(description = "ID da turma a ser removida", example = "1")
            @PathVariable Long id) {
        log.info("DELETE -> /api/v1/turmas/{} - Removendo turma", id);
        this.turmaService.removerTurma(id);
        return ResponseEntity.noContent().build();
    }
}

