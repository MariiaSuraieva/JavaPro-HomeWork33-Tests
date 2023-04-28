package com.example.demowithtests.web.workspace;

import com.example.demowithtests.domain.Workspace;
import com.example.demowithtests.dto.workspace.WorkspaceRequestDto;
import com.example.demowithtests.dto.workspace.WorkspaceResponseDto;
import com.example.demowithtests.service.workspace.WorkspaceService;
import com.example.demowithtests.util.config.workspace.WorkspaceMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class WorkspaceControllerBean implements WorkspaceController {
    private final WorkspaceService workspaceService;
    private final WorkspaceMapper workspaceMapper;

    @Override
    public WorkspaceResponseDto saveWorkspace(WorkspaceRequestDto requestDto) {
        log.info("workspaceController saveWorkspace() started");
        Workspace workspace = workspaceMapper.fromRequestDto(requestDto);
        WorkspaceResponseDto workspaceResponseDto = workspaceMapper
                .fromEntity(workspaceService.create(workspace));
        log.info("workspaceController saveWorkspace() completed");
        return workspaceResponseDto;
    }

    @Override
    public List<WorkspaceResponseDto> getAllWorkspaces() {
        log.info("workspaceController getAllWorkspaces() started");
        List<Workspace> workspaces = workspaceService.getAll();
        List<WorkspaceResponseDto> workspaceResponseDtos = new ArrayList<>();
        for (Workspace workspace : workspaces) {
            workspaceResponseDtos.add(workspaceMapper.fromEntity(workspace));
        }
        log.info("workspaceController getAllWorkspaces() completed");
        return workspaceResponseDtos;
    }

    @Override
    public WorkspaceResponseDto getWorkspaceById(String id) {
        log.info("workspaceController getWorkspaceById() started");
        Workspace workspace = workspaceService.getById(id);
        WorkspaceResponseDto workspaceResponseDto = workspaceMapper.fromEntity(workspace);
        log.info("workspaceController getWorkspaceById() completed");
        return workspaceResponseDto;
    }

    @Override
    public WorkspaceResponseDto updateWorkspaceById(String id, WorkspaceRequestDto workspaceRequestDto) {
        log.info("workspaceController updateWorkspaceById() started");
        WorkspaceResponseDto workspaceResponseDto = workspaceMapper.fromEntity(
                workspaceService.updateById(id, workspaceMapper.fromRequestDto(workspaceRequestDto))
        );
        log.info("workspaceController updateWorkspaceById() completed");
        return workspaceResponseDto;
    }

    @Override
    public WorkspaceResponseDto closeWorkspace(String id) {
        log.info("workspaceController closeWorkspace() started");
        WorkspaceResponseDto workspaceResponseDto = workspaceMapper
                .fromEntity(workspaceService.closeById(id));
        log.info("workspaceController closeWorkspace() completed");
        return workspaceResponseDto;
    }

    @Override
    public WorkspaceResponseDto openWorkspace(String id) {
        log.info("workspaceController openWorkspace() started");
        WorkspaceResponseDto workspaceResponseDto = workspaceMapper
                .fromEntity(workspaceService.openById(id));
        log.info("workspaceController openWorkspace() completed");
        return workspaceResponseDto;
    }

    @Override
    public List<WorkspaceResponseDto> getAllNotOccupiedWorkspaces() {
        log.info("workspaceController getAllOccupiedWorkspaces() started");
        List<Workspace> workspaces = workspaceService.getAllNotOccupied();
        List<WorkspaceResponseDto> workspaceResponseDtos = new ArrayList<>();
        for (Workspace workspace : workspaces) {
            workspaceResponseDtos.add(workspaceMapper.fromEntity(workspace));
        }
        log.info("workspaceController getAllOccupiedWorkspaces() completed");
        return workspaceResponseDtos;
    }
}
