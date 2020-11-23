package jy.study.springbootrest.controller;

import jy.study.springbootrest.controller.data.ApiErrResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(value = "jy.study.springbootrest.controller")
public class Advice {

    @ExceptionHandler (IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrResult userErr(IllegalArgumentException exception) {
        return new ApiErrResult(exception.getMessage());
    }
}
