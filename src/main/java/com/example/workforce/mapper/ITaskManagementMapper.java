package com.example.workforce.mapper;

import com.example.workforce.dto.TaskManagementDto;
import com.example.workforce.common.model.TaskManagement;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ITaskManagementMapper {
    ITaskManagementMapper INSTANCE = Mappers.getMapper(ITaskManagementMapper.class);

    TaskManagementDto modelToDto(TaskManagement taskManagement);

    TaskManagement dtoToModel(TaskManagementDto dto);

    List<TaskManagementDto> modelListToDtoList(List<TaskManagement> models);
}
