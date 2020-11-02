package jy.study.springbootrest.controller;

import jy.study.springbootrest.controller.data.ApiResult;
import jy.study.springbootrest.model.user.dto.UserDto;
import jy.study.springbootrest.model.user.entity.User;
import jy.study.springbootrest.model.user.sv.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public EntityModel<User> createUser(@RequestBody UserDto userDto) {
        User user = userService.insertUser(userDto);

        EntityModel<User> entityModel = EntityModel.of(user);
        entityModel.add(linkTo(UserController.class).withSelfRel());
        entityModel.add(linkTo(UserController.class).slash(user.getId()).withRel("get-user"));

        return entityModel;
    }

    @GetMapping("/{id}")
    public EntityModel<User> getUser(@PathVariable Integer id) {
        User user = userService.getUser(id);

        EntityModel<User> entityModel = EntityModel.of(user);
        entityModel.add(linkTo(UserController.class).slash(user.getId()).withSelfRel());
        entityModel.add(linkTo(UserController.class).withRel("create-user"));
        entityModel.add(linkTo(UserController.class).slash(user.getId()).withRel("update-user"));
        entityModel.add(linkTo(UserController.class).slash(user.getId()).withRel("delete-user"));

        return entityModel;
    }

    @PutMapping("/{id}")
    public EntityModel<User> updateUser(@PathVariable Integer id, @RequestBody UserDto userDto) {
        User user = userService.updateUser(id, userDto);

        EntityModel<User> entityModel = EntityModel.of(user);
        entityModel.add(linkTo(UserController.class).slash(user.getId()).withSelfRel());
        entityModel.add(linkTo(UserController.class).withRel("create-user"));
        entityModel.add(linkTo(UserController.class).slash(user.getId()).withRel("get-user"));
        entityModel.add(linkTo(UserController.class).slash(user.getId()).withRel("delete-user"));

        return entityModel;
    }

    @DeleteMapping("/{id}")
    public EntityModel<ApiResult> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);

        EntityModel<ApiResult> entityModel = EntityModel.of(new ApiResult(true, "delete success"));
        entityModel.add(linkTo(UserController.class).slash(id).withSelfRel());
        entityModel.add(linkTo(UserController.class).withRel("create-user"));

        return entityModel;
    }
}
