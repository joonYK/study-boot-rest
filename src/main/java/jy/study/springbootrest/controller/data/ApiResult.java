package jy.study.springbootrest.controller.data;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResult {

    private boolean success;

    private String message;
}
