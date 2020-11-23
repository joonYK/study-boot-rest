package jy.study.springbootrest.service;

import jy.study.springbootrest.model.user.dto.UserDto;
import jy.study.springbootrest.model.user.entity.User;
import jy.study.springbootrest.model.user.exception.UserException;
import jy.study.springbootrest.model.user.sv.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void deleteTest() throws UserException {
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

    @Test
    public void duplicatedUserInsert() throws UserException {
        //given
        UserDto userDto = UserDto.builder()
                .name("테스트")
                .email("asdfsadf@asdsa.com")
                .build();

        userService.insertUser(userDto);

        //when & then
        assertThrows(UserException.class, () -> userService.insertUser(userDto));
    }
}
