package com.example.workforce.mapper;

import com.example.workforce.common.model.TaskManagement;
import com.example.workforce.dto.TaskManagementDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-02T22:40:12+0530",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.12.jar, environment: Java 23-valhalla (Oracle Corporation)"
)
@Component
public class ITaskManagementMapperImpl implements ITaskManagementMapper {

    @Override
    public TaskManagementDto modelToDto(TaskManagement taskManagement) {
        if ( taskManagement == null ) {
            return null;
        }

        TaskManagementDto taskManagementDto = new TaskManagementDto();

        taskManagementDto.setId( taskManagement.getId() );
        taskManagementDto.setReferenceId( taskManagement.getReferenceId() );
        taskManagementDto.setReferenceType( taskManagement.getReferenceType() );
        taskManagementDto.setTask( taskManagement.getTask() );
        taskManagementDto.setDescription( taskManagement.getDescription() );
        taskManagementDto.setStatus( taskManagement.getStatus() );
        taskManagementDto.setAssigneeId( taskManagement.getAssigneeId() );
        taskManagementDto.setTaskDeadlineTime( taskManagement.getTaskDeadlineTime() );
        taskManagementDto.setPriority( taskManagement.getPriority() );

        return taskManagementDto;
    }

    @Override
    public TaskManagement dtoToModel(TaskManagementDto dto) {
        if ( dto == null ) {
            return null;
        }

        TaskManagement taskManagement = new TaskManagement();

        taskManagement.setId( dto.getId() );
        taskManagement.setReferenceId( dto.getReferenceId() );
        taskManagement.setReferenceType( dto.getReferenceType() );
        taskManagement.setTask( dto.getTask() );
        taskManagement.setDescription( dto.getDescription() );
        taskManagement.setStatus( dto.getStatus() );
        taskManagement.setAssigneeId( dto.getAssigneeId() );
        taskManagement.setTaskDeadlineTime( dto.getTaskDeadlineTime() );
        taskManagement.setPriority( dto.getPriority() );

        return taskManagement;
    }

    @Override
    public List<TaskManagementDto> modelListToDtoList(List<TaskManagement> models) {
        if ( models == null ) {
            return null;
        }

        List<TaskManagementDto> list = new ArrayList<TaskManagementDto>( models.size() );
        for ( TaskManagement taskManagement : models ) {
            list.add( modelToDto( taskManagement ) );
        }

        return list;
    }
}
