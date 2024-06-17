package com.example.SampleDemoProject.Controller;

import com.example.SampleDemoProject.Dtos.DeleteUserDtoResponse;
import com.example.SampleDemoProject.Dtos.Response;
import com.example.SampleDemoProject.Dtos.UserRequest;
import com.example.SampleDemoProject.Dtos.UserResponse;
import com.example.SampleDemoProject.Exceptions.InvalidUserRequestException;
import com.example.SampleDemoProject.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("FetchUser/{Id}")
    public UserResponse FetchUser(@PathVariable int Id) throws InvalidUserRequestException {
        UserResponse response = new UserResponse();
        try {
            if (Id <= 0) {
                throw new InvalidUserRequestException("Id must be greater than 0");
            }
            UserResponse user = this.userService.fetchUser(Id);
            response.setId(user.getId());
            response.setName(user.getName());
            response.setEmail(user.getEmail());
            response.setResponse(Response.getSuccess());
        } catch (Exception e) {
            response.setResponse(Response.getFailure(e.getMessage()));
        }
        return response;
    }

    @PostMapping("/CreateUser")
    public UserResponse CreateUser(@RequestBody UserRequest request) throws InvalidUserRequestException {
        UserResponse response = new UserResponse();
        try {
            validateUserRequest(request);
            UserResponse user = this.userService.createUser(request);
            response.setId(user.getId());
            response.setName(user.getName());
            response.setEmail(user.getEmail());
            response.setResponse(Response.getSuccess());
        } catch (Exception e) {
            response.setResponse(Response.getFailure(e.getMessage()));
        }
        return response;
    }

    @DeleteMapping("/DeleteUser/{Id}")
    public DeleteUserDtoResponse DeleteUser(@PathVariable int Id) throws InvalidUserRequestException {
        DeleteUserDtoResponse response = new DeleteUserDtoResponse();
        try {
            if (Id <= 0) {
                throw new InvalidUserRequestException("Id must be greater than 0");
            }
            this.userService.deleteUser(Id);
            response.setStatus(true);
            response.setResponse(Response.getSuccess());
        } catch (Exception e) {
            response.setResponse(Response.getFailure(e.getMessage()));
        }
        return response;
    }

    private void validateUserRequest(UserRequest request) throws InvalidUserRequestException {
        if (request.getName().isEmpty() || request.getEmail().isEmpty()) {
            throw new InvalidUserRequestException("Name or email address cannot be empty");
        }
    }
}
