package org.example.Dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse {
    private  boolean success;
    private  Object data; // Changed from generic T to Object
    private  String message;
    private  Integer errorCode;
    private  Object errorMessage;
    private LocalDateTime timestamp;


}
