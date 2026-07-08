package io.github.eliaspinheiropereira.technedesafio.mapper;

import io.github.eliaspinheiropereira.technedesafio.dto.request.AlunoRequest;
import io.github.eliaspinheiropereira.technedesafio.dto.response.AlunoResponse;
import io.github.eliaspinheiropereira.technedesafio.model.Aluno;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {EnderecoMapper.class})
public interface AlunoMapper {

    @Mapping(source = "endereco", target = "endereco")
    Aluno toEntity(AlunoRequest alunoRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "endereco", target = "endereco")
    Aluno toUpdateAluno(AlunoRequest alunoRequest, @MappingTarget Aluno aluno);

    @Mapping(source = "endereco", target = "endereco")
    AlunoResponse toResponse(Aluno aluno);
}

