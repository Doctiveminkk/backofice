package finalproject.itsector.dao;

import finalproject.itsector.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
//Entity Repository for Database Access
@Repository
public interface UserDao extends CrudRepository<User, Long> {
    User findUserByusername(String username);
}