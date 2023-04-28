package com.example.demowithtests.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demowithtests.domain.Passport;
public interface PassportRepository extends JpaRepository<Passport, Integer> {
}
