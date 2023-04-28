package com.example.demowithtests.domain;


import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@ToString
@Getter
@Setter
@Entity
@Table(name = "passports")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private final UUID serialNumber = UUID.randomUUID();
    private String firstName;
    private String secondName;
    private Boolean isFree = Boolean.TRUE;

    @OneToOne(mappedBy = "passport")
    private Employee employee;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "passport_id")
    private Set<Visa> visas = new HashSet<>();

}
