package com.example.SampleDemoProject.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response {
    private ResponseType status;
    private String errorMessage;

    public static Response getSuccess() {
        return new Response(ResponseType.SUCCESS, null);
    }
    public static Response getFailure(String errorMessage) {
        return new Response(ResponseType.FAILURE, errorMessage);
    }
}
