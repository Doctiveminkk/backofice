package finalproject.itsector.service;

import finalproject.itsector.dao.UserDao;
import finalproject.itsector.model.RegisteredUser;
import finalproject.itsector.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Service layer to accommodate Authentication Logic
@Service
@Slf4j
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserDao dao;

    @Override
    public RegisteredUser loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = dao.findUserByusername(username);
        if (user.isPresent()){
            log.info("User found");
        } else{
            log.info("User doesn't exist");
        }
        List<String> authorities = new ArrayList<>();
        authorities.add("admin");
        return new RegisteredUser(user.get().getUsername(),user.get().getPassword(), authorities);
    }
}