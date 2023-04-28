package com.example.demowithtests.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "workspaces")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Workspace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer capacity;
    private Integer occupancy = 0;
    private Boolean isActive = Boolean.TRUE;
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "workspaces", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Employee> employees = new HashSet<>();
}
