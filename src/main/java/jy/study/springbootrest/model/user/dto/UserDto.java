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

    public void requiredValueCheck() throws IllegalArgumentException {
        if(name == null || "".equals(name))
            throw new IllegalArgumentException("이름이 없습니다.");
        else if(email == null || "".equals(email))
            throw new IllegalArgumentException("이메일이 없습니다.");
    }

}
