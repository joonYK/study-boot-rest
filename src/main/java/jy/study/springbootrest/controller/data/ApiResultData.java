package jy.study.springbootrest.controller.data;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class ApiResultData<T> extends ApiResult {

    private T data;

    public ApiResultData(T data) {
        super(true, null);
        this.data = data;
    }

    public ApiResultData(boolean success, String message, T data) {
        super(success, message);
        this.data = data;
    }
}
