package jy.study.springbootrest.repository;

import jy.study.springbootrest.model.user.entity.User;
import jy.study.springbootrest.model.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void saveTest() {
        //given
        User user = User.builder()
                .name("김준엽")
                .email("email@asd.com")
                .build();

        //when
        user = userRepository.save(user);

        //then
        assertThat(user.getId()).isNotNull();
    }
}