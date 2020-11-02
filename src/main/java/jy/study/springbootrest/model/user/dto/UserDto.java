package jy.study.springbootrest.model.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UserDto {

    private String name;

    private String email;

}
