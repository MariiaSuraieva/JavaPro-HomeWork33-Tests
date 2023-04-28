package com.example.demowithtests.util.config.workspace;

import com.example.demowithtests.domain.Workspace;
import com.example.demowithtests.dto.workspace.WorkspaceRequestDto;
import com.example.demowithtests.dto.workspace.WorkspaceResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkspaceMapper {
    Workspace fromRequestDto(WorkspaceRequestDto workspaceRequestDto);
    WorkspaceResponseDto fromEntity(Workspace workspace);
}
