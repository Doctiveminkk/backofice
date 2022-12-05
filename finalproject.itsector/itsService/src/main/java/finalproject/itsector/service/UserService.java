package finalproject.itsector.service;

import finalproject.itsector.dao.UserDao;
import finalproject.itsector.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//Service Layer to Accommodate Business Logic
@Service
@Slf4j
@ComponentScan(basePackages = "finalproject.itsector")
public class UserService{
    @Autowired
    private UserDao dao;
    @Autowired
    private PasswordEncoder encoder;

    public List<User> retrieveUsers(){
            return (List<User>) dao.findAll();
        }

    public List<User> filterUser(String name, String username){
        List<User> list = new ArrayList<>();
        if (!username.equals("")){
            Optional<User> foundUser = dao.findUserByusername(username);
            foundUser.ifPresent(list::add);
        }
        if (!name.equals("")){
            Optional<User> foundUser = dao.findUserByname(name);
            foundUser.ifPresent(list::add);
        }
        return list;
    }

    public User createUser(User user){
        Optional<User> twinUser = dao.findUserByusername(user.getUsername());
        if (twinUser.isPresent()) {
            log.info("Username already exists");
            return null;
        }else{
            log.info("User created");
            String password = encoder.encode(user.getPassword());
            user.setPassword(password);
            dao.save(user);
            return user;
        }
    }

    public User getUserById(Long id){
        Optional<User> user = dao.findById(id);
        return user.orElse(null);
    }

    public void deleteUser(Long id){
        Optional<User> user = dao.findById(id);
        user.ifPresent(value -> dao.delete(value));
        log.info("User with id: "+id+" has been deleted");
    }

    public String updateUser(User model) {
        Optional<User> user = dao.findUserByusername(model.getUsername());
        if (user.isPresent()){
            user.get().setName(model.getName());
            user.get().setPassword(encoder.encode(model.getPassword()));
            user.get().setLastUpdated(LocalDateTime.now());
            dao.save(user.get());
            return "User: "+user.get().getUsername()+" was updated";
        }
        return "Must create a user first";
    }
}