package com.marlon.CleanArchTeste.Adapters.Http.Controllers;

import com.marlon.CleanArchTeste.Application.UseCases.User.CreateUser.CreateUserInputBoundary;
import com.marlon.CleanArchTeste.Application.UseCases.User.CreateUser.CreateUserUseCase;
import com.marlon.CleanArchTeste.Application.UseCases.User.DeleteUser.DeleteUserInputBoundary;
import com.marlon.CleanArchTeste.Application.UseCases.User.DeleteUser.DeleteUserUseCase;
import com.marlon.CleanArchTeste.Application.UseCases.User.GetAllUsers.GetAllUsersInputBoundary;
import com.marlon.CleanArchTeste.Application.UseCases.User.GetAllUsers.GetAllUsersUseCase;
import com.marlon.CleanArchTeste.Application.UseCases.User.GetUser.GetUserInputBoundary;
import com.marlon.CleanArchTeste.Application.UseCases.User.GetUser.GetUserUseCase;
import com.marlon.CleanArchTeste.Application.UseCases.User.UpdateUser.OutputBoundary;
import com.marlon.CleanArchTeste.Application.UseCases.User.UpdateUser.UpdateUserInputBoundary;
import com.marlon.CleanArchTeste.Application.UseCases.User.UpdateUser.UpdateUserUseCase;
import com.marlon.CleanArchTeste.Domain.Exceptions.DomainException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UsersController
{
    private final GetAllUsersUseCase getAllUsersUseCase;
    private final GetUserUseCase getUserUseCase;
    private final CreateUserUseCase createUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

    public UsersController(
            GetAllUsersUseCase getAllUsersUseCase,
            GetUserUseCase getUserUseCase,
            CreateUserUseCase createUserUseCase,
            UpdateUserUseCase updateUserUseCase,
            DeleteUserUseCase deleteUserUseCase
    ) {
        this.getAllUsersUseCase = getAllUsersUseCase;
        this.getUserUseCase = getUserUseCase;
        this.createUserUseCase = createUserUseCase;
        this.updateUserUseCase = updateUserUseCase;
        this.deleteUserUseCase = deleteUserUseCase;
    }

    @GetMapping(path = {"/users"})
    public Map<String, Object> list(
            @RequestParam Map<String, String> requestParameters
    ) {
        var input = new GetAllUsersInputBoundary();
        var output = getAllUsersUseCase.handle(input);
        var response = new HashMap<String, Object>();
        response.put("message", output.getMessage());
        response.put("data", output.toMap());
        return response;
    }

    @GetMapping(path = {"/users/{id}"})
    public Map<String, Object> show(@PathVariable int id) {
        var input = new GetUserInputBoundary(id);
        var output = getUserUseCase.handle(input);
        var response = new HashMap<String, Object>();
        response.put("message", output.getMessage());
        response.put("data", output.toMap());
        return response;
    }

    @PostMapping(path = {"/users"})
    public Map<String, Object> create(@RequestBody Map<String, String> requestBody) {
        var response = new HashMap<String, Object>();
        try {
            var input = new CreateUserInputBoundary(
                    requestBody.get("firstName"),
                    requestBody.get("lastName"),
                    requestBody.get("emailAddress"),
                    requestBody.get("password")
            );
            var output = createUserUseCase.handle(input);
            response.put("message", output.getMessage());
        } catch (Exception e) {
            response.put("error", e.getMessage());
        }
        return response;
    }

    @PutMapping(path = {"/users/{id}"})
    public Map<String, Object> update(@PathVariable int id, @RequestBody Map<String, String> requestBody) {
        var response = new HashMap<String, Object>();
        try {
            var input = new UpdateUserInputBoundary(id, requestBody);
            var output = updateUserUseCase.handle(input);
            response.put("message", output.getMessage());
        } catch (Exception e) {
            response.put("error", e.getMessage());
        }
        return response;
    }

    @DeleteMapping(path = {"/users/{id}"})
    public Map<String, Object> delete(@PathVariable int id) {
        var input = new DeleteUserInputBoundary(id);
        var output = deleteUserUseCase.handle(input);
        var response = new HashMap<String, Object>();
        response.put("message", output.getMessage());
        return response;
    }

}
