package com.bilgeadam.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ErrorMessage {


    private int code;
    private String message;
    private HttpStatus httpStatus;

}
