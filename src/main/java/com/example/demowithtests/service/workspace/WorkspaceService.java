package com.example.demowithtests.service.workspace;

import com.example.demowithtests.domain.Passport;
import com.example.demowithtests.domain.Workspace;

import java.util.List;

public interface WorkspaceService {
    Workspace create(Workspace workspace);

    List<Workspace> getAll();

    Workspace getById(String id);
    Workspace updateById(String id, Workspace workspace);

    Workspace closeById(String id);

    Workspace openById(String id);

    List<Workspace> getAllNotOccupied();
}
