package io.github.eliaspinheiropereira.technedesafio.controller;

import io.github.eliaspinheiropereira.technedesafio.dto.request.EnderecoRequest;
import io.github.eliaspinheiropereira.technedesafio.dto.response.EnderecoResponse;
import io.github.eliaspinheiropereira.technedesafio.service.EnderecoService;
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
@RequestMapping("/api/v1/enderecos")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Endereços", description = "API para gerenciar endereços de alunos")
public class EnderecoController {

    private final EnderecoService enderecoService;

    @PostMapping
    @Operation(summary = "Cadastrar novo endereço", description = "Cria um novo endereço na base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Endereço cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(schema = @Schema()))
    })
    public ResponseEntity<Void> cadastrar(@Valid @RequestBody EnderecoRequest enderecoRequest) {
        log.info("POST -> /api/v1/enderecos - Cadastrando endereço: {}", enderecoRequest);
        this.enderecoService.cadastrarEndereco(enderecoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar endereço por ID", description = "Busca um endereço específico pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço encontrado", content = @Content(schema = @Schema(implementation = EnderecoResponse.class))),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(schema = @Schema()))
    })
    public ResponseEntity<EnderecoResponse> buscarPorId(
            @Parameter(description = "ID do endereço a ser buscado", example = "1")
            @PathVariable Long id) {
        log.info("GET -> /api/v1/enderecos/{} - Buscando endereço por ID", id);
        EnderecoResponse enderecoResponse = this.enderecoService.buscarEnderecoPorId(id);
        return ResponseEntity.ok(enderecoResponse);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar endereço", description = "Atualiza um endereço existente na base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Endereço atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(schema = @Schema()))
    })
    public ResponseEntity<Void> atualizar(
            @Parameter(description = "ID do endereço a ser atualizado", example = "1")
            @PathVariable Long id,
            @Valid @RequestBody EnderecoRequest enderecoRequest) {
        log.info("PUT -> /api/v1/enderecos/{} - Atualizando endereço: {}", id, enderecoRequest);
        this.enderecoService.atualizarEndereco(id, enderecoRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover endereço", description = "Remove um endereço da base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Endereço removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(schema = @Schema()))
    })
    public ResponseEntity<Void> remover(
            @Parameter(description = "ID do endereço a ser removido", example = "1")
            @PathVariable Long id) {
        log.info("DELETE -> /api/v1/enderecos/{} - Removendo endereço", id);
        this.enderecoService.removerEndereco(id);
        return ResponseEntity.noContent().build();
    }
}

