package jy.study.springbootrest.model.user.sv;

import jy.study.springbootrest.model.user.dto.UserDto;
import jy.study.springbootrest.model.user.entity.User;
import jy.study.springbootrest.model.user.exception.UserException;
import jy.study.springbootrest.model.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User insertUser(UserDto userDto) throws IllegalArgumentException, UserException {
        userDto.requiredValueCheck();

        Optional<User> optional = userRepository.findByEmail(userDto.getEmail());
        if(optional.isPresent())
            throw new UserException(UserException.UserExceptionType.DUPLICATION);

        User user = User.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .build();

        return userRepository.save(user);
    }

    public User getUser(Integer id) {
        Optional<User> optional = userRepository.findById(id);
        return optional.orElse(null);
    }

    public User updateUser(Integer id, UserDto userDto) {
        User user = getUser(id);
        BeanUtils.copyProperties(userDto, user, "id");
        return userRepository.save(user);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
