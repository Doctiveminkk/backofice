package finalproject.itsector.service;

import finalproject.itsector.dao.UserDao;
import finalproject.itsector.model.RegisteredUser;
import finalproject.itsector.model.User;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;


import java.util.ArrayList;
import java.util.Collection;

@Service
@ComponentScan(basePackages = "finalproject.itsector")
@Slf4j
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserDao dao;

    public User getUserByUsername(String username){
        User user = dao.findUserByusername(username);
        if (user == null) {
            log.info("User doesn't exist");
        }else{
            log.info("User found");
        }
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = dao.findUserByusername(username);
        if (user == null){
            log.info("User doesn't exist");
            throw new UsernameNotFoundException("User doesn't exist");
        } else{
            log.info("User found");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("admin"));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
    }
    /*public RegisteredUser login(RegisteredUser regUser){
        User user = dao.findUserByusername(regUser.getUsername());
        if (!user.getUsername().equals(regUser.getUsername())){
            System.out.println("username doesn´t match");
            return null;
        }
        if (!user.getPassword().equals(regUser.getPassword())){
            System.out.println("password doesn´t match");
            return null;
        }
        return regUser;
    }*/
}
