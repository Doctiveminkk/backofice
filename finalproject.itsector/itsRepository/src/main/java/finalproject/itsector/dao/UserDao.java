package finalproject.itsector.dao;

import finalproject.itsector.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//Entity Repository for Database Access
@Repository
public interface UserDao extends CrudRepository<User, Long> {
    Optional<User> findUserByusername(String username);
    Optional<User> findUserByname(String name);
}