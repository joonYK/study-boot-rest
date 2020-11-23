package jy.study.springbootrest.controller.data;

import lombok.Getter;

@Getter
public class ApiErrResult {

    private String errorMessage;

    public ApiErrResult(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
