package com.example.demowithtests.util.config.workspace;

import com.example.demowithtests.domain.Workspace;
import com.example.demowithtests.domain.Workspace.WorkspaceBuilder;
import com.example.demowithtests.dto.workspace.WorkspaceRequestDto;
import com.example.demowithtests.dto.workspace.WorkspaceResponseDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-24T13:55:19+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.5 (Oracle Corporation)"
)
@Component
public class WorkspaceMapperImpl implements WorkspaceMapper {

    @Override
    public Workspace fromRequestDto(WorkspaceRequestDto workspaceRequestDto) {
        if ( workspaceRequestDto == null ) {
            return null;
        }

        WorkspaceBuilder workspace = Workspace.builder();

        workspace.name( workspaceRequestDto.name );
        workspace.capacity( workspaceRequestDto.capacity );
        workspace.occupancy( workspaceRequestDto.occupancy );
        workspace.isActive( workspaceRequestDto.isActive );

        return workspace.build();
    }

    @Override
    public WorkspaceResponseDto fromEntity(Workspace workspace) {
        if ( workspace == null ) {
            return null;
        }

        WorkspaceResponseDto workspaceResponseDto = new WorkspaceResponseDto();

        workspaceResponseDto.name = workspace.getName();
        workspaceResponseDto.capacity = workspace.getCapacity();
        workspaceResponseDto.occupancy = workspace.getOccupancy();
        workspaceResponseDto.isActive = workspace.getIsActive();

        return workspaceResponseDto;
    }
}
