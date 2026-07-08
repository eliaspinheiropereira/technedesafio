package io.github.eliaspinheiropereira.technedesafio.mapper;

import io.github.eliaspinheiropereira.technedesafio.dto.request.EnderecoRequest;
import io.github.eliaspinheiropereira.technedesafio.dto.response.EnderecoResponse;
import io.github.eliaspinheiropereira.technedesafio.model.Endereco;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

    Endereco toEntity(EnderecoRequest enderecoRequest);
    @Mapping(target = "id", ignore = true)
    Endereco toUpdateEntity(EnderecoRequest enderecoRequest, @MappingTarget Endereco endereco);
    EnderecoResponse toResponse(Endereco endereco);

}
