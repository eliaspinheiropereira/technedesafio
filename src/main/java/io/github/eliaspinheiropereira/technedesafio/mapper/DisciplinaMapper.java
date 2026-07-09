package io.github.eliaspinheiropereira.technedesafio.mapper;

import io.github.eliaspinheiropereira.technedesafio.dto.request.DisciplinaRequest;
import io.github.eliaspinheiropereira.technedesafio.dto.response.DisciplinaResponse;
import io.github.eliaspinheiropereira.technedesafio.model.Disciplina;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {CursoMapper.class})
public interface DisciplinaMapper {

    @Mapping(target = "curso", ignore = true)
    Disciplina toEntity(DisciplinaRequest disciplinaRequest);

    @Mapping(source = "curso", target = "curso")
    DisciplinaResponse toResponse(Disciplina disciplina);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "curso", ignore = true)
    Disciplina toUpdateEntity(DisciplinaRequest disciplinaRequest, @MappingTarget Disciplina disciplina);
}

