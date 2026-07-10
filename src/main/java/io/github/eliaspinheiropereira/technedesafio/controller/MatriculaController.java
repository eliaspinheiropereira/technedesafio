package io.github.eliaspinheiropereira.technedesafio.controller;

import io.github.eliaspinheiropereira.technedesafio.dto.request.MatriculaRequest;
import io.github.eliaspinheiropereira.technedesafio.dto.request.MatriculaUpdateRequest;
import io.github.eliaspinheiropereira.technedesafio.dto.response.MatriculaResponse;
import io.github.eliaspinheiropereira.technedesafio.service.MatriculaService;
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

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/matriculas")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Matrículas", description = "API para gerenciar matrículas de alunos")
public class MatriculaController {

    private final MatriculaService matriculaService;

    @PostMapping
    @Operation(summary = "Cadastrar nova matrícula", description = "Cria uma nova matrícula vinculando um aluno a uma turma")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Matrícula cadastrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "409", description = "Conflito - aluno já matriculado nesta turma", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(schema = @Schema()))
    })
    public ResponseEntity<Void> cadastrar(@Valid @RequestBody MatriculaRequest matriculaRequest) {
        log.info("POST -> /api/v1/matriculas - Cadastrando matrícula: aluno ID {} em turma ID {}",
                 matriculaRequest.alunoId(), matriculaRequest.turmaId());
        this.matriculaService.cadastrarMatricula(matriculaRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar status de matrícula", description = "Atualiza o status de uma matrícula existente (PENDENTE, CONFIRMADA, CANCELADA)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Status da matrícula atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Matrícula não encontrada", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(schema = @Schema()))
    })
    public ResponseEntity<Void> atualizar(
            @Parameter(description = "ID da matrícula a ser atualizada", example = "550e8400-e29b-41d4-a716-446655440000")
            @PathVariable UUID id, @Valid @RequestBody MatriculaUpdateRequest matriculaUpdateRequest) {
        log.info("PUT -> /api/v1/matriculas/{} - Atualizando status matrícula para: {}", id, matriculaUpdateRequest.statusMatricula());
        this.matriculaService.atualizarMatricula(id, matriculaUpdateRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover matrícula", description = "Remove uma matrícula da base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Matrícula removida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Matrícula não encontrada", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(schema = @Schema()))
    })
    public ResponseEntity<Void> remover(
            @Parameter(description = "ID da matrícula a ser removida", example = "550e8400-e29b-41d4-a716-446655440000")
            @PathVariable UUID id) {
        log.info("DELETE -> /api/v1/matriculas/{} - Removendo matrícula", id);
        this.matriculaService.removerMatricula(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/aluno/{nome}")
    @Operation(summary = "Buscar matrículas por aluno", description = "Busca todas as matrículas de um aluno específico pelo seu nome (parcial ou completo)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Matrículas encontradas", content = @Content(schema = @Schema(implementation = MatriculaResponse.class))),
            @ApiResponse(responseCode = "404", description = "Nenhuma matrícula encontrada", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(schema = @Schema()))
    })
    public ResponseEntity<List<MatriculaResponse>> buscarMatriculasPorAluno(
            @Parameter(description = "Nome do aluno (parcial ou completo)", example = "João")
            @PathVariable String nome) {
        log.info("GET -> /api/v1/matriculas/aluno/{} - Buscando matrículas do aluno", nome);
        List<MatriculaResponse> matriculaResponse = this.matriculaService.buscarMatriculasPorAluno(nome);
        return ResponseEntity.ok(matriculaResponse);
    }

    @GetMapping("/turma/{periodo}")
    @Operation(summary = "Buscar matrículas por turma", description = "Busca todas as matrículas de uma turma específica pelo seu período")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Matrículas encontradas", content = @Content(schema = @Schema(implementation = MatriculaResponse.class))),
            @ApiResponse(responseCode = "404", description = "Nenhuma matrícula encontrada", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(schema = @Schema()))
    })
    public ResponseEntity<List<MatriculaResponse>> buscarMatriculasPorTurma(
            @Parameter(description = "Período da turma (ex: 2024/1, 2024/2)", example = "2024/1")
            @PathVariable String periodo) {
        log.info("GET -> /api/v1/matriculas/turma/{} - Buscando matrículas da turma", periodo);
        List<MatriculaResponse> matriculaResponse = this.matriculaService.buscarMatriculasPorTurma(periodo);
        return ResponseEntity.ok(matriculaResponse);
    }
}



