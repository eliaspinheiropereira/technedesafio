package io.github.eliaspinheiropereira.technedesafio.service;

import io.github.eliaspinheiropereira.technedesafio.dto.request.EnderecoRequest;
import io.github.eliaspinheiropereira.technedesafio.dto.response.EnderecoResponse;
import io.github.eliaspinheiropereira.technedesafio.exception.EnderecoNaoEncontradoException;
import io.github.eliaspinheiropereira.technedesafio.mapper.EnderecoMapper;
import io.github.eliaspinheiropereira.technedesafio.model.Endereco;
import io.github.eliaspinheiropereira.technedesafio.repository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final EnderecoMapper enderecoMapper;

    public void cadastrarEndereco(EnderecoRequest dto) {
        Endereco endereco = this.enderecoMapper.toEntity(dto);
        this.enderecoRepository.save(endereco);
    }

    public void atualizarEndereco(Long id, EnderecoRequest dto) {
        Endereco buscarEndereco = getEndereco(id);
        Endereco atualizarEndereco = this.enderecoMapper.toUpdateEntity(dto, buscarEndereco);
        this.enderecoRepository.save(atualizarEndereco);
    }

    public void removerEndereco(Long id){
        Endereco buscarEndereco = getEndereco(id);
        this.enderecoRepository.deleteById(id);
    }

    public EnderecoResponse buscarEnderecoPorId(Long id) {
        Endereco buscarEndereco = getEndereco(id);
        return this.enderecoMapper.toResponse(buscarEndereco);
    }

    private Endereco getEndereco(Long id) {
        return this.enderecoRepository.findById(id)
                .orElseThrow(() -> new EnderecoNaoEncontradoException("Endereço não encontrado na base de dados."));
    }

}
