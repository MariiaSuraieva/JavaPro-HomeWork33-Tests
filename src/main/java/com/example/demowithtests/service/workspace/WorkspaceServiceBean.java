package com.example.demowithtests.service.workspace;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.domain.Workspace;
import com.example.demowithtests.repository.EmployeeRepository;
import com.example.demowithtests.repository.WorkspaceRepository;
import com.example.demowithtests.util.EmptyDataException;
import com.example.demowithtests.util.IdIsNotExistException;
import com.example.demowithtests.util.ListHasNoAnyElementsException;
import com.example.demowithtests.util.WrongDataException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class WorkspaceServiceBean implements WorkspaceService {

    private final WorkspaceRepository workspaceRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public Workspace create(Workspace workspace) {
        log.debug("workspaceService create() started for workspace "+ workspace.getName());
        Optional.of(workspace)
                .filter(w -> w.getCapacity() != null && w.getName() != null)
                .map(w -> {
                    w.setOccupancy(0);
                    return w;
                })
                .map(workspaceRepository::save)
                .orElseThrow(EmptyDataException::new);
        log.debug("workspaceService create() completed for workspace "+workspace.getName());
        return workspace;
    }

    @Override
    public List<Workspace> getAll() {
        log.debug("workspaceService getAll() started");
        List<Workspace> workspaces = workspaceRepository.findAll();
        if (workspaces.size() == 0) throw new ListHasNoAnyElementsException();
        log.debug("workspace getAll() finished, you get " + workspaces.size() + " workspaces");
        return workspaces;
    }

    @Override
    public Workspace getById(String id) {
        log.debug("workspace getById() - start: id = {}", id);
        try {
            var workspaceId = Integer.parseInt(id);
            var workspace = workspaceRepository.findById(workspaceId)
                    .orElseThrow(IdIsNotExistException::new);
            log.debug("getById() - end: workspace = {}", workspace);
            return workspace;
        } catch (NumberFormatException exception) {
            throw new WrongDataException();
        }
    }

    @Override
    public Workspace updateById(String id, Workspace workspaceNew) {
        log.debug("workspaceService updateById() started for workspaceId " + id);
        Integer idInteger = Integer.parseInt(id);
        Workspace workspace = workspaceRepository.findById(idInteger)
                .map(entity -> {
                    entity.setName(
                            workspaceNew.getName() == null ?
                                    entity.getName() : workspaceNew.getName()
                    );
                    entity.setCapacity(workspaceNew.getCapacity());
                    return workspaceRepository.save(entity);
                })
                .orElseThrow(IdIsNotExistException::new);
        log.debug("workspaceService updateById() completed for workspaceId "+id);
        return workspace;
    }

    @Transactional
    @Override
    public Workspace closeById(String id) {
        log.debug("workspaceService closeById() started for workspaceId "+id);
        var workspace = getById(id);
        List<Employee> employees = employeeRepository.findByWorkspacesContaining(workspace);
        for (Employee employee : employees) {
                employee.getWorkspaces().remove(workspace);
                employeeRepository.save(employee);
        }
        workspace.getEmployees().clear();
        workspace.setOccupancy(0);
        workspace.setIsActive(false);
        workspaceRepository.save(workspace);
        log.debug("workspaceService closeById() completed for workspaceId "+id);
        return workspace;
    }

    @Override
    public Workspace openById(String id) {
        log.debug("workspaceService openById() started for workspaceId "+id);
        Workspace workspace = getById(id);
        workspace.setIsActive(Boolean.TRUE);
        workspaceRepository.save(workspace);
        log.debug("workspaceService openById() completed for workspaceId "+id);
        return workspace;
    }

    @Override
    public List<Workspace> getAllNotOccupied() {
        log.debug("workspaceService getAllNotOccupied() started");
        List<Workspace> workspaces = workspaceRepository.findAll();
        List<Workspace> notOccupiedWorkspaces = workspaces.stream()
                .filter(w -> w.getCapacity() > w.getOccupancy())
                .collect(Collectors.toList());
        if (notOccupiedWorkspaces.isEmpty()) {
            throw new ListHasNoAnyElementsException();
        }
        log.debug("workspace getAllNotOccupied() finished, you get " + notOccupiedWorkspaces.size() + " workspacesNotOccupied");
        return notOccupiedWorkspaces;
    }
}
