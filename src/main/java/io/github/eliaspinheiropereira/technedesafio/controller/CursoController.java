package io.github.eliaspinheiropereira.technedesafio.controller;

import io.github.eliaspinheiropereira.technedesafio.dto.request.CursoRequest;
import io.github.eliaspinheiropereira.technedesafio.dto.response.CursoResponse;
import io.github.eliaspinheiropereira.technedesafio.service.CursoService;
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
@RequestMapping("/api/v1/cursos")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Cursos", description = "API para gerenciar cursos")
public class CursoController {

    private final CursoService cursoService;

    @PostMapping
    @Operation(summary = "Cadastrar novo curso", description = "Cria um novo curso na base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Curso cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(schema = @Schema()))
    })
    public ResponseEntity<Void> cadastrar(@Valid @RequestBody CursoRequest cursoRequest) {
        log.info("POST -> /api/v1/cursos - Cadastrando curso: {}", cursoRequest);
        this.cursoService.cadastrarCurso(cursoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar curso por ID", description = "Busca um curso específico pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Curso encontrado", content = @Content(schema = @Schema(implementation = CursoResponse.class))),
            @ApiResponse(responseCode = "404", description = "Curso não encontrado", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(schema = @Schema()))
    })
    public ResponseEntity<CursoResponse> buscarPorId(
            @Parameter(description = "ID do curso a ser buscado", example = "1")
            @PathVariable Long id) {
        log.info("GET -> /api/v1/cursos/{} - Buscando curso por ID", id);
        CursoResponse cursoResponse = this.cursoService.buscarCursoPorId(id);
        return ResponseEntity.ok(cursoResponse);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar curso", description = "Atualiza um curso existente na base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Curso atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Curso não encontrado", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(schema = @Schema()))
    })
    public ResponseEntity<Void> atualizar(
            @Parameter(description = "ID do curso a ser atualizado", example = "1")
            @PathVariable Long id, @Valid @RequestBody CursoRequest cursoRequest) {
        log.info("PUT -> /api/v1/cursos/{} - Atualizando curso: {}", id, cursoRequest);
        this.cursoService.atualizarCurso(id, cursoRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover curso", description = "Remove um curso da base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Curso removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Curso não encontrado", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(schema = @Schema()))
    })
    public ResponseEntity<Void> remover(
            @Parameter(description = "ID do curso a ser removido", example = "1")
            @PathVariable Long id) {
        log.info("DELETE -> /api/v1/cursos/{} - Removendo curso", id);
        this.cursoService.removerCurso(id);
        return ResponseEntity.noContent().build();
    }
}

