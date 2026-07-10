package io.github.eliaspinheiropereira.technedesafio.mapper;

import io.github.eliaspinheiropereira.technedesafio.dto.request.MatriculaRequest;
import io.github.eliaspinheiropereira.technedesafio.dto.request.MatriculaUpdateRequest;
import io.github.eliaspinheiropereira.technedesafio.dto.response.MatriculaResponse;
import io.github.eliaspinheiropereira.technedesafio.model.Matricula;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MatriculaMapper {

    Matricula toEntity(MatriculaRequest matriculaRequest);

    @Mapping(source = "aluno.id", target = "alunoId")
    @Mapping(source = "aluno.nome", target = "alunoNome")
    @Mapping(source = "turma.id", target = "turmaId")
    @Mapping(source = "turma.periodo", target = "turmaPeriodo")
    MatriculaResponse toResponse(Matricula matricula);

    @Mapping(target = "codigoMatricula", ignore = true)
    @Mapping(target = "aluno", ignore = true)
    @Mapping(target = "turma", ignore = true)
    @Mapping(source = "statusMatricula", target = "statusMatricula")
    Matricula toUpdateEntity(MatriculaUpdateRequest matriculaUpdateRequest, @MappingTarget Matricula matricula);
}

