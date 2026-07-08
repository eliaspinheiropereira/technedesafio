package io.github.eliaspinheiropereira.technedesafio.controller;

import io.github.eliaspinheiropereira.technedesafio.dto.request.EnderecoRequest;
import io.github.eliaspinheiropereira.technedesafio.dto.response.EnderecoResponse;
import io.github.eliaspinheiropereira.technedesafio.service.EnderecoService;
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
public class EnderecoController {

    private final EnderecoService enderecoService;

    @PostMapping
    public ResponseEntity<Void> cadastrar(@Valid @RequestBody EnderecoRequest enderecoRequest) {
        log.info("POST -> /api/v1/enderecos - Cadastrando endereço: {}", enderecoRequest);
        this.enderecoService.cadastrarEndereco(enderecoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoResponse> buscarPorId(@PathVariable Long id) {
        log.info("GET -> /api/v1/enderecos/{} - Buscando endereço por ID", id);
        EnderecoResponse enderecoResponse = this.enderecoService.buscarEnderecoPorId(id);
        return ResponseEntity.ok(enderecoResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @Valid @RequestBody EnderecoRequest enderecoRequest) {
        log.info("PUT -> /api/v1/enderecos/{} - Atualizando endereço: {}", id, enderecoRequest);
        this.enderecoService.atualizarEndereco(id, enderecoRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        log.info("DELETE -> /api/v1/enderecos/{} - Removendo endereço", id);
        this.enderecoService.removerEndereco(id);
        return ResponseEntity.noContent().build();
    }
}

