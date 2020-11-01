package jy.study.springbootrest.model.user.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserDto {

    private String name;

    private String email;

}
