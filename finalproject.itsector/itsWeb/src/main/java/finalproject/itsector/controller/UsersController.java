package finalproject.itsector.controller;

import finalproject.itsector.model.User;
import finalproject.itsector.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsersController {

    @Autowired
    private UserService service;

    @GetMapping("/its-backoffice/getUser")
    public User getUser(@RequestBody String username){
        return service.getUserByUsername(username);
    }

    @GetMapping("/its-backoffice/users")
    public List<User> retrieveUsers(){
        return (List<User>) service.retrieveUsers();
    }

    @PostMapping("/its-backoffice/users")
    public User createUser(@RequestBody User user){
        return service.createUser(user);
    }

    @DeleteMapping("/its-backoffice/users")
    public void deleteUser(@RequestParam String username){
        service.deleteUser(username);
    }

    @PatchMapping("/its-backoffice/users")
    public User updateUser(@RequestParam String username, @RequestParam String name, @RequestParam String password){
        return service.updateUser(username, name, password);
    }
}