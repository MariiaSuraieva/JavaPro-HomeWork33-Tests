package com.example.demowithtests.web.passport;

import com.example.demowithtests.dto.passport.PassportRequestDto;
import com.example.demowithtests.dto.passport.PassportResponseDto;
import com.example.demowithtests.dto.passport.VisaDto;
import com.example.demowithtests.util.UserIsNotExistException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

public interface PassportController {
    @Operation(summary = "This is endpoint to create a new passport.", description = "Create request to create a new passport.", tags = {"Passport"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "SUCCESSFULLY. The passport has been created."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. The database wasnt found."),
            @ApiResponse(responseCode = "500", description = "Unexpectable server error")})
    @PostMapping("/passports")
    @ResponseStatus(HttpStatus.CREATED)
    PassportResponseDto savePassport(@RequestBody PassportRequestDto requestDto);

    @Operation(summary = "This is endpoint to receiving all passports from database.", description = "Create request to receive all passports from database.", tags = {"Passport"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESSFULLY. The passports have been received."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. The database wasnt found."),
            @ApiResponse(responseCode = "500", description = "Unexpectable server error")})
    @GetMapping("/passports")
    @ResponseStatus(HttpStatus.OK)
    List<PassportResponseDto> getAllPassports();

    @Operation(summary = "This is endpoint to get the passport by id.", description = "Create request to get the passport by id.", tags = {"Passport"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESSFULLY. The passport has been received."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. The database wasnt found."),
            @ApiResponse(responseCode = "500", description = "Unexpectable server error")})
    @GetMapping("/passports/{id}")
    @ResponseStatus(HttpStatus.OK)
    PassportResponseDto getPassportById(@PathVariable String id);

    @Operation(summary = "This is endpoint to renew the passport.", description = "Create request to renew the passport.", tags = {"Passport"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESSFULLY. The passport has been renewed."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. The database wasnt found."),
            @ApiResponse(responseCode = "500", description = "Unexpectable server error")})
    @PutMapping("/passports/{id}")
    @ResponseStatus(HttpStatus.OK)
    PassportResponseDto refreshPassport(@PathVariable("id") Integer id, @RequestBody PassportRequestDto requestDto) throws UserIsNotExistException;

    @Operation(summary = "This is endpoint to connect to passport visa.", description = "Create request to connect to the passport new visa.", tags = {"Passport"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESSFULLY. The visa has been added to the passport."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. The database wasnt found."),
            @ApiResponse(responseCode = "500", description = "Unexpectable server error")})
    @PatchMapping("/passports/addVisa/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    PassportResponseDto addVisa(@PathVariable("id") String id, @RequestBody VisaDto visaDto);

    @Operation(summary = "This is endpoint to get all visas that the passport has.", description = "Create request to get all visas that the passport has.", tags = {"Passport"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESSFULLY. All visas from the passport have been got."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. The database wasnt found."),
            @ApiResponse(responseCode = "500", description = "Unexpectable server error")})
    @GetMapping("/passports/getVisas/{passportId}")
    @ResponseStatus(HttpStatus.OK)
    Set<VisaDto> getVisas(@PathVariable String passportId);
}
