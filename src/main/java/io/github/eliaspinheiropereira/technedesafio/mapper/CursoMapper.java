package io.github.eliaspinheiropereira.technedesafio.mapper;

import io.github.eliaspinheiropereira.technedesafio.dto.request.CursoRequest;
import io.github.eliaspinheiropereira.technedesafio.dto.response.CursoResponse;
import io.github.eliaspinheiropereira.technedesafio.model.Curso;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CursoMapper {

    Curso toEntity(CursoRequest cursoRequest);

    CursoResponse toResponse(Curso curso);

    @Mapping(target = "id", ignore = true)
    Curso toUpdateEntity(CursoRequest cursoRequest, @MappingTarget Curso curso);
}

