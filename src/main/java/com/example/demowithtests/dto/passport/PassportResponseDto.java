package com.example.demowithtests.dto.passport;

import com.example.demowithtests.domain.Visa;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@ToString
@Getter
@Setter
public class PassportResponseDto {
    @Schema(description = "The firstname of passports owner.", example = "Billy", required = true)
    public String firstName;
    @Schema(description = "The lastname of passports owner.", example = "Sullyvan", required = true)
    public String secondName;
    @Schema(description = "Visas that the passports owner has.", example = "Schengen, 2027. USA 2025.", required = true)
    public Set<VisaDto> visasDto;
}
