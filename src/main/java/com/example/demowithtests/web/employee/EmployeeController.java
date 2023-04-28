package com.example.demowithtests.web.employee;

import com.example.demowithtests.dto.employee.EmployeeDto;
import com.example.demowithtests.dto.employee.EmployeeReadDto;
import com.example.demowithtests.dto.workspace.WorkspaceResponseDto;
import com.example.demowithtests.util.UserIsNotExistException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

public interface EmployeeController {
    @Operation(summary = "This is endpoint to add a new employee.", description = "Create request to add a new employee.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED. The new employee is successfully created and added to database."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    EmployeeDto saveEmployee(@RequestBody EmployeeDto employeeDto);


    @Operation(summary = "This is endpoint to get a list of all users.", description = "Create request to get a list of all users.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESSFULLY. The list of users has been successfully received."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Database wasnt found."),
            @ApiResponse(responseCode = "500", description = "Unexpectable server error")})
    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    List<EmployeeReadDto> getAllUsers();


    @Operation(summary = "This is endpoint to get an user by ID.", description = "Create request to get a user by Id.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESSFULLY. The user has been successfully received."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. The user with this ID wasnt found."),
            @ApiResponse(responseCode = "400", description = "You put wrong data, please, it should be a number"),
            @ApiResponse(responseCode = "500", description = "Unexpectable server error")})
    @GetMapping(value = "/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    EmployeeReadDto getEmployeeById(@PathVariable String id);


    @Operation(summary = "This is endpoint to renew some information about user.", description = "Create request to renew information about user.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESSFULLY. The user has been successfully updated."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. The user with this ID wasnt found."),
            @ApiResponse(responseCode = "400", description = "You put wrong data."),
            @ApiResponse(responseCode = "500", description = "Unexpectable server error")})
    @PutMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    EmployeeReadDto refreshEmployee(@PathVariable("id") String id, @RequestBody EmployeeDto employeeDto) throws UserIsNotExistException;


    @Operation(summary = "This is endpoint to remove the user by ID.", description = "Create request to remove the user.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESSFULLY. The user has been successfully deleted."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. The user with this ID wasnt found."),
            @ApiResponse(responseCode = "400", description = "You put wrong data, it should be a number."),
            @ApiResponse(responseCode = "500", description = "Unexpectable server error")})
    @PatchMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void removeEmployeeById(@PathVariable String id);


    @Operation(summary = "This is endpoint to remove all users from database.", description = "Create request to remove all users from database.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "SUCCESSFULLY. The users have been successfully deleted."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. The database wasnt found."),
            @ApiResponse(responseCode = "401", description = "You should be authorized."),
            @ApiResponse(responseCode = "500", description = "Unexpectable server error")})
    @DeleteMapping("/users")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void removeAllUsers();


    @Operation(summary = "This is endpoint to sending emails for all users from country you need.", description = "Create request to send emails for users from country you need.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESSFULLY. The users successfully recieved emails."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. The database wasnt found."),
            @ApiResponse(responseCode = "500", description = "Unexpectable server error")})
    @PostMapping("/sendEmailByCountry")
    @ResponseStatus(HttpStatus.OK)
    void sendEmailByCountry(@RequestParam String country, @RequestParam String text);


    @Operation(summary = "This is endpoint to sending emails for all users from city you need.", description = "Create request to send emails for users from city you need.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESSFULLY. The users successfully recieved emails."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. The database wasnt found."),
            @ApiResponse(responseCode = "500", description = "Unexpectable server error")})
    @PostMapping("/sendEmailByCity")
    @ResponseStatus(HttpStatus.OK)
    void sendEmailByCity(@RequestParam String cities, @RequestBody String text);


    @Operation(summary = "This is endpoint to filling database by so much basic users as you wish.", description = "Create request to filling database by basic users.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESSFULLY. The database has been filling."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. The database wasnt found."),
            @ApiResponse(responseCode = "400", description = "You put wrong data, it should be a number."),
            @ApiResponse(responseCode = "500", description = "Unexpectable server error")})
    @PostMapping("/fillingDataBase/{quantity}")
    @ResponseStatus(HttpStatus.OK)
    void fillingDataBase(@PathVariable String quantity);


    @Operation(summary = "This is endpoint to long updating of users parameter country.", description = "Create request to long updating for users parameter country.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESSFULLY. The database has been updated."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. The database wasnt found."),
            @ApiResponse(responseCode = "400", description = "You put wrong data, it should be list of countries in text."),
            @ApiResponse(responseCode = "500", description = "Unexpectable server error")})
    @PostMapping("/updateBaseByCountryFully")
    @ResponseStatus(HttpStatus.OK)
    void updateByCountryFully(@RequestParam String countries);


    @Operation(summary = "This is endpoint to fast updating of users parameter country.", description = "Create request to fast updating for users parameter country.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESSFULLY. The database has been updated."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. The database wasnt found."),
            @ApiResponse(responseCode = "400", description = "You put wrong data, it should be list of countries in text."),
            @ApiResponse(responseCode = "500", description = "Unexpectable server error")})
    @PostMapping("/updateBaseByCountrySmart")
    @ResponseStatus(HttpStatus.OK)
    void updateByCountrySmart(@RequestParam String countries);


    @Operation(summary = "This is endpoint to updating database by replacing nulls.", description = "Create request to update database by replacing nulls.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESSFULLY. The database has been updated."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. The database wasnt found."),
            @ApiResponse(responseCode = "500", description = "Unexpectable server error")})
    @PostMapping("/replaceNull")
    @ResponseStatus(HttpStatus.OK)
    void replaceNull();


    @Operation(summary = "This is endpoint to sending emails for all users who have to renew their photo.", description = "Create request to send emails for users who have to renew their photo.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESSFULLY. The users successfully recieved emails."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. The database wasnt found."),
            @ApiResponse(responseCode = "500", description = "Unexpectable server error")})
    @PostMapping("/sendEmailPhotoChange")
    @ResponseStatus(HttpStatus.OK)
    void emailSenderPhotoChange();


    @Operation(summary = "This is endpoint to sending emails for all users who have to come back home.", description = "Create request to send emails for users have to come back home.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESSFULLY. The users successfully recieved emails."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. The database wasnt found."),
            @ApiResponse(responseCode = "500", description = "Unexpectable server error")})
    @PostMapping("/WelcomeBackSender")
    @ResponseStatus(HttpStatus.OK)
    void emailSenderWhoMovedFromUkraine(@RequestParam String country);

    @Operation(summary = "This is endpoint to connect to employee the passport.", description = "Create request to connect to employee the passport.", tags = {"Employee", "Passport"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESSFULLY. The employee succefully was connected with the passport."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. The database wasnt found."),
            @ApiResponse(responseCode = "500", description = "Unexpectable server error")})
    @PatchMapping("/addPassport")
    @ResponseStatus(HttpStatus.OK)
    EmployeeReadDto addPassport(@RequestParam Integer employeeId, @RequestParam Integer passportId);

    @Operation(summary = "This is endpoint to connect to employee the passport, safe addition.", description = "Create request to connect to employee the passport, safe addition.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESSFULLY. The employee succefully was connected with the passport, it was safely."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. The database wasnt found."),
            @ApiResponse(responseCode = "500", description = "Unexpectable server error")})
    @PatchMapping("/users/{uid}/passports/{pid}")
    @ResponseStatus(HttpStatus.OK)
    EmployeeReadDto addPassportSafely(@PathVariable("uid") Integer employeeId,
                                      @PathVariable("pid") Integer passportId);

    @Operation(summary = "This is endpoint to connect to employee the passport, safe addition.", description = "Create request to connect to employee the passport, safe addition.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESSFULLY. The employee succefully was connected with the passport, it was safely."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. The database wasnt found."),
            @ApiResponse(responseCode = "500", description = "Unexpectable server error")})
    @PutMapping("/users/addPassports/{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    EmployeeReadDto addPassport(@PathVariable("employeeId") Integer employeeId);

    @Operation(summary = "This is endpoint to connect employee to workspace.", description = "Create request to connect employee to workspace", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESSFULLY. The employee successfully was connected to workspace."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. The database wasnt found."),
            @ApiResponse(responseCode = "500", description = "Unexpectable server error")})
    @PostMapping("/users/connectEmployee/{employeeId}/workspace/{workspaceId}")
    @ResponseStatus(HttpStatus.OK)
    EmployeeReadDto connectEmployeeToWorkspace(@PathVariable("employeeId") String employeeId, @PathVariable("workspaceId") String workspaceId);

    @Operation(summary = "This is endpoint to disconnect employee to workspace.", description = "Create request to disconnect employee from workspace", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESSFULLY. The employee successfully was disconnected from workspace."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. The database wasnt found."),
            @ApiResponse(responseCode = "500", description = "Unexpectable server error")})
    @PostMapping("/users/disconnectEmployee/{employeeId}/workspace/{workspaceId}")
    @ResponseStatus(HttpStatus.OK)
    EmployeeReadDto disconnectEmployeeToWorkspace(@PathVariable("employeeId") String employeeId, @PathVariable("workspaceId") String workspaceId);

    @Operation(summary = "This is endpoint to get all employees workspaces.", description = "Create request to get all employees workspaces", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESSFULLY. The employee successfully was connected to workspace."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. The database wasnt found."),
            @ApiResponse(responseCode = "500", description = "Unexpectable server error")})
    @GetMapping("/users/showMyWorkspaces/{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    Set<WorkspaceResponseDto> getWorkspacesByEmployeeId(@PathVariable("employeeId") String employeeId);

    @Operation(summary = "This is endpoint to connect employee to any free workspace.", description = "Create request to connect employee to any free workspace", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESSFULLY. The employee successfully was connected to any free workspace."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. The database wasnt found."),
            @ApiResponse(responseCode = "500", description = "Unexpectable server error")})
    @PutMapping("/users/connectEmployee/{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    EmployeeReadDto connectEmployeeToAnyWorkspace(@PathVariable("employeeId") String employeeId);

}
