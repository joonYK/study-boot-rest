package jy.study.springbootrest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jy.study.springbootrest.model.user.dto.UserDto;
import jy.study.springbootrest.model.user.entity.User;
import jy.study.springbootrest.model.user.repository.UserRepository;
import jy.study.springbootrest.model.user.sv.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    private UserDto userDto;

    @BeforeEach
    public void setUp() {
        userDto = UserDto.builder()
                .name("김준엽")
                .email("test@email.com")
                .build();
    }

    @Test
    public void createUserTest() throws Exception {
        //when & then
        mockMvc.perform(post("/api/users")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .accept(MediaType.APPLICATION_JSON_VALUE)
                    .content(objectMapper.writeValueAsString(userDto))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("_links.self").exists())
                .andExpect(jsonPath("_links.get-user").exists())
        ;
    }

    @Test
    public void getUserTest() throws Exception {
        //given
        User user = userService.insertUser(userDto);

        //when & then
        mockMvc.perform(get("/api/users/{id}", user.getId()))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("name").value(user.getName()))
                .andExpect(jsonPath("id").value(user.getId()))
                .andExpect(jsonPath("email").value(user.getEmail()))
                .andExpect(jsonPath("_links.self").exists())
                .andExpect(jsonPath("_links.update-user").exists())
                .andExpect(jsonPath("_links.delete-user").exists())
        ;

    }
}