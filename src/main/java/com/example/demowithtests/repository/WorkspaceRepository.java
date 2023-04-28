package com.example.demowithtests.repository;

import com.example.demowithtests.domain.Passport;
import com.example.demowithtests.domain.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkspaceRepository extends JpaRepository<Workspace, Integer> {
}
