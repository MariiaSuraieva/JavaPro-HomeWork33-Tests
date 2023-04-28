package com.example.demowithtests.dto.passport;

import com.example.demowithtests.domain.Visa;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Set;

@ToString
public class PassportRequestDto {
    @Schema(description = "The firstname of passports owner.", example = "Billy", required = true)
    public String firstName;
    @Schema(description = "The lastname of passports owner.", example = "Sullyvan", required = true)
    public String secondName;
    @Schema(description = "The birthday of passports owner.", example = "22.11.1987", required = true)
    public LocalDate birthDate;
    @Schema(description = "This is a list of visas that the passports owner has.", example = "Schengen, 2027. USA 2025.", required = true)
    public Set<Visa> visas;



}
