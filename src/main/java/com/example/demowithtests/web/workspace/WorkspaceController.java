package com.example.demowithtests.web.workspace;

import com.example.demowithtests.dto.workspace.WorkspaceRequestDto;
import com.example.demowithtests.dto.workspace.WorkspaceResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface WorkspaceController {
    @Operation(summary = "This is endpoint create new workspace.", description = "Create request to create new workspace", tags = {"Workspace"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESSFULLY. The workspace was created."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. The database wasnt found."),
            @ApiResponse(responseCode = "500", description = "Unexpectable server error")})
    @PostMapping("/workspace")
    @ResponseStatus(HttpStatus.CREATED)
    WorkspaceResponseDto saveWorkspace(@RequestBody WorkspaceRequestDto requestDto);

    @Operation(summary = "This is endpoint to get and see all workspaces.", description = "Create request to get and see all workspaces", tags = {"Workspace"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESSFULLY. The workspaces were getting."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. The database wasnt found."),
            @ApiResponse(responseCode = "500", description = "Unexpectable server error")})
    @GetMapping("/workspaces")
    @ResponseStatus(HttpStatus.CREATED)
    List<WorkspaceResponseDto> getAllWorkspaces();

    @Operation(summary = "This is endpoint to get a workspace by id.", description = "Create request to get a workspace by id", tags = {"Workspace"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESSFULLY. The workspace was getting."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. The database wasnt found."),
            @ApiResponse(responseCode = "500", description = "Unexpectable server error")})
    @GetMapping("/workspace/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    WorkspaceResponseDto getWorkspaceById(@PathVariable("id") String id);

    @Operation(summary = "This is endpoint to update workspace by id.", description = "Create request to update a workspace by id", tags = {"Workspace"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESSFULLY. The workspace was updated."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. The database wasnt found."),
            @ApiResponse(responseCode = "500", description = "Unexpectable server error")})
    @PatchMapping("/workspace/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    WorkspaceResponseDto updateWorkspaceById(@PathVariable("id") String id, @RequestBody WorkspaceRequestDto requestDto);

    @Operation(summary = "This is endpoint to close workspace.", description = "Create request to close workspace", tags = {"Workspace"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESSFULLY. The workspace was closed."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. The database wasnt found."),
            @ApiResponse(responseCode = "500", description = "Unexpectable server error")})
    @PatchMapping("/workspace/{id}/down")
    @ResponseStatus(HttpStatus.CREATED)
    WorkspaceResponseDto closeWorkspace(@PathVariable("id") String id);

    @Operation(summary = "This is endpoint to open workspace.", description = "Create request to open workspace", tags = {"Workspace"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESSFULLY. The workspace was opened."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. The database wasnt found."),
            @ApiResponse(responseCode = "500", description = "Unexpectable server error")})
    @PatchMapping("/workspace/{id}/up")
    @ResponseStatus(HttpStatus.CREATED)
    WorkspaceResponseDto openWorkspace(@PathVariable("id") String id);

    @Operation(summary = "This is endpoint to get and see all workspaces that not occupied.", description = "Create request to get and see all workspaces that not occupied", tags = {"Workspace"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESSFULLY. The list of free workspaces was received."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. The database wasnt found."),
            @ApiResponse(responseCode = "500", description = "Unexpectable server error")})
    @GetMapping("/workspacesFree")
    @ResponseStatus(HttpStatus.CREATED)
    List<WorkspaceResponseDto> getAllNotOccupiedWorkspaces();
}
