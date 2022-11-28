package finalproject.itsector.service;

import finalproject.itsector.dao.UserDao;
import finalproject.itsector.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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

    public User createUser(User user){
        String password = encoder.encode(user.getPassword());
        user.setPassword(password);
        dao.save(user);
        return user;
    }

    public User getUserByUsername(String username){
        User user =dao.findUserByusername(username);
        if (user == null) {
            log.info("User doesn't exist");
        }else{
            log.info("User found");
        }
        return user;
    }

    public void deleteUser(String username){
        User user = dao.findUserByusername(username);
        dao.delete(user);
    }

    public User updateUser(String username, String name, String password) {
        User user = dao.findUserByusername(username);
        if (name != null && !name.equals(user.getName())|| password != null && !password.equals(user.getPassword())){
            if(name != null){
                user.setName(name);
            }
            if(password != null){
                user.setPassword(password);
            }
            user.setUpdatedAt(LocalDate.now());
            dao.save(user);
            return user;
        }
        return user;
    }
}
