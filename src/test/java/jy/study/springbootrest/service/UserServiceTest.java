package jy.study.springbootrest.service;

import jy.study.springbootrest.model.user.dto.UserDto;
import jy.study.springbootrest.model.user.entity.User;
import jy.study.springbootrest.model.user.sv.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void deleteTest() {
        //given
        UserDto userDto = UserDto.builder()
                .name("테스트")
                .email("asdfsadf@asdsa.com")
                .build();

        User user = userService.insertUser(userDto);

        //when
        userService.deleteUser(user.getId());
        User checkDeleteUser = userService.getUser(user.getId());

        //then
        assertThat(user.getId()).isNotNull();
        assertThat(checkDeleteUser).isNull();
    }
}
