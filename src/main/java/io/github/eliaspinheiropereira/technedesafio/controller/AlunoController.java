package io.github.eliaspinheiropereira.technedesafio.controller;

import io.github.eliaspinheiropereira.technedesafio.dto.request.AlunoRequest;
import io.github.eliaspinheiropereira.technedesafio.dto.response.AlunoResponse;
import io.github.eliaspinheiropereira.technedesafio.service.AlunoService;
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
@RequestMapping("/api/v1/alunos")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Alunos", description = "API para gerenciar alunos")
public class AlunoController {

    private final AlunoService alunoService;


    @PostMapping
    @Operation(summary = "Cadastrar novo aluno", description = "Cria um novo aluno na base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Aluno cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(schema = @Schema()))
    })
    public ResponseEntity<Void> cadastrar(@Valid @RequestBody AlunoRequest alunoRequest) {
        log.info("POST -> /api/v1/alunos - Cadastrando alunos: {}", alunoRequest);
        this.alunoService.cadastrarAluno(alunoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar aluno por ID", description = "Busca um aluno específico pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aluno encontrado", content = @Content(schema = @Schema(implementation = AlunoResponse.class))),
            @ApiResponse(responseCode = "404", description = "Aluno não encontrado", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(schema = @Schema()))
    })
    public ResponseEntity<AlunoResponse> buscarPorId(
            @Parameter(description = "ID do aluno a ser buscado", example = "1")
            @PathVariable Long id) {
        log.info("GET -> /api/v1/alunos/{} - Buscando alunos por ID", id);
        AlunoResponse alunoResponse = this.alunoService.buscarAlunoPorId(id);
        return ResponseEntity.ok(alunoResponse);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar aluno", description = "Atualiza um aluno existente na base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Aluno atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Aluno não encontrado", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(schema = @Schema()))
    })
    public ResponseEntity<Void> atualizar(
            @Parameter(description = "ID do aluno a ser atualizado", example = "1")
            @PathVariable Long id, @Valid @RequestBody AlunoRequest alunoRequest) {
        log.info("PUT -> /api/v1/alunoss/{} - Atualizando aluno: {}", id, alunoRequest);
        this.alunoService.atualizarAluno(id, alunoRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover aluno", description = "Remove um aluno da base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Aluno removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Aluno não encontrado", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(schema = @Schema()))
    })
    public ResponseEntity<Void> remover(
            @Parameter(description = "ID do aluno a ser removido", example = "1")
            @PathVariable Long id) {
        log.info("DELETE -> /api/v1/alunos/{} - Removendo aluno", id);
        this.alunoService.removerAluno(id);
        return ResponseEntity.noContent().build();
    }
}

