package com.example.workforce.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.example.workforce.common.model.enums.ReferenceType;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AssignByReferenceRequest {
    private Long referenceId;
    private ReferenceType referenceType;
    private Long assigneeId;
}
