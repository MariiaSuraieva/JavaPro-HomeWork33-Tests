package com.example.demowithtests.dto.passport;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
@ToString
@Getter
@Setter
public class VisaDto {
    @Schema(description = "Name of visa.", example = "Schengen", required = true)
    public String name;
    @Schema(description = "The year when the visa ends.", example = "2027", required = true)
    public Integer yearEnding;
}
