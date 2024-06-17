package com.example.SampleDemoProject.Dtos;

import lombok.Data;

@Data
public class UserResponse {
    private int id;
    private String name;
    private String email;
    private Response response;
}
