package com.example.demowithtests.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String country;
    private String email;
    private Boolean isDeleted = Boolean.FALSE;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private Set<Address> addresses = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private Set<Photo> photos = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "passport_id", referencedColumnName = "id")
    private Passport passport;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ToString.Exclude
    private Set<Workspace> workspaces = new HashSet<>();

    public Employee(String name, String country, String email, Boolean isDeleted) {
        this.name = name;
        this.country = country;
        this.email = email;
        this.isDeleted = isDeleted;
    }

    public Employee(String name, String country, String email) {
        this.name = name;
        this.country = country;
        this.email = email;
    }
}