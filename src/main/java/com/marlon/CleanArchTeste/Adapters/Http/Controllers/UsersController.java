package com.marlon.CleanArchTeste.Adapters.Http.Controllers;

import com.marlon.CleanArchTeste.Application.UseCases.User.CreateUser.CreateUserUseCase;
import com.marlon.CleanArchTeste.Application.UseCases.User.DeleteUser.DeleteUserUseCase;
import com.marlon.CleanArchTeste.Application.UseCases.User.GetAllUsers.GetAllUsersUseCase;
import com.marlon.CleanArchTeste.Application.UseCases.User.GetUser.GetUserUseCase;
import com.marlon.CleanArchTeste.Application.UseCases.User.UpdateUser.UpdateUserUseCase;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class UsersController
{

    @GetMapping(path = {"/users"})
    public String list(
            GetAllUsersUseCase getAllUsersUseCase,
            @RequestParam Map<String, String> requestParameters
    ) {
        return "list";
    }

    @GetMapping(path = {"/users/{id}"})
    public String show(
            GetUserUseCase getUserUseCase,
            @PathVariable int id
    ) {
        return "show";
    }

    @PostMapping(path = {"/users"})
    public String create(
            CreateUserUseCase createUserUseCase,
            @RequestBody String requestBody
    ) {
        return "create";
    }

    @PutMapping(path = {"/users/{id}"})
    public String update(
            UpdateUserUseCase updateUserUseCase,
            @PathVariable int id
    ) {
        return "update";
    }

    @DeleteMapping(path = {"/users/{id}"})
    public String delete(
            DeleteUserUseCase deleteUserUseCase,
            @PathVariable int id
    ) {
        return "delete";
    }

}
