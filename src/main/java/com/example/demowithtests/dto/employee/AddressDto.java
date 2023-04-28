package com.example.demowithtests.dto.employee;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;
import java.util.Date;

public class AddressDto {
    @Schema(description = "Is the address active.", example = "false", required = true)
    public Boolean addressHasActive = Boolean.TRUE;

    @Schema(description = "Name of a country of employee.", example = "Ukraine", required = true)
    public String country;

    @Schema(description = "Name of a city of employee.", example = "Odesa", required = true)
    public String city;

    @Schema(description = "Name of a street of employee.", example = "Sakharova", required = true)
    public String street;

    @Schema(description = "Date of adding the employee.", example = "22.11.1987", required = true)
    public Date date = Date.from(Instant.now());

}
