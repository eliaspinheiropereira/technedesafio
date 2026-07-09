package io.github.eliaspinheiropereira.technedesafio.mapper;

import io.github.eliaspinheiropereira.technedesafio.dto.request.TurmaRequest;
import io.github.eliaspinheiropereira.technedesafio.dto.response.TurmaResponse;
import io.github.eliaspinheiropereira.technedesafio.model.Turma;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {DisciplinaMapper.class})
public interface TurmaMapper {

    Turma toEntity(TurmaRequest turmaRequest);

    TurmaResponse toResponse(Turma turma);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "vagaDisponivel", ignore = true)
    @Mapping(target = "disciplinas", ignore = true)
    Turma toUpdateEntity(TurmaRequest turmaRequest, @MappingTarget Turma turma);
}

