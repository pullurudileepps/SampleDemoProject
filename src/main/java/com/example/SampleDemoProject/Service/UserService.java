package com.example.SampleDemoProject.Service;

import com.example.SampleDemoProject.Dtos.UserRequest;
import com.example.SampleDemoProject.Dtos.UserResponse;

public interface UserService {
    UserResponse fetchUser(int Id);
    UserResponse createUser(UserRequest userRequest);
    void deleteUser(int Id);
}
