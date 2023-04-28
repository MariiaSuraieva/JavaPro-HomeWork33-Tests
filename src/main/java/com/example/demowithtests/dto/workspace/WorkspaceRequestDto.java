package com.example.demowithtests.dto.workspace;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.ToString;

@ToString
public class WorkspaceRequestDto {
    @Schema(description = "Name of a workspace.", example = "New-York", required = true)
    public String name;
    @Schema(description = "Max number of employees that could be in this workspace.", example = "10", required = true)
    public Integer capacity;
    @Schema(description = "Number of employees that are in this workspace now.", example = "5", required = true)
    public Integer occupancy;
    @Schema(description = "This parameter tell us is it possible to connect to this workspace.", example = "true", required = true)
    public Boolean isActive = Boolean.TRUE;
}
