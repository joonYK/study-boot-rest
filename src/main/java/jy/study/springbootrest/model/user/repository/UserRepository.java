package jy.study.springbootrest.model.user.repository;

import jy.study.springbootrest.model.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
