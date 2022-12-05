package finalproject.itsector.controller;

import finalproject.itsector.model.User;
import finalproject.itsector.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Rest Controller for Users CRUD operations
@RestController
@Slf4j
public class UsersController {

    @Autowired
    private UserService service;

    @GetMapping("/its-backoffice/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id")Long id, @RequestHeader (name="Authorization") String token){
        return ResponseEntity.ok()
                .header("Access-Control-Expose-Headers", "*")
                .header("Authorization", token)
                .body(service.getUserById(id));
    }

    @GetMapping("/its-backoffice/filteredusers")
    public ResponseEntity<List<User>> filterUser(@RequestParam(defaultValue = "") String name, @RequestParam(defaultValue = "") String username, @RequestHeader (name="Authorization") String token){
        return ResponseEntity.ok()
                .header("Access-Control-Expose-Headers", "*")
                .header("Authorization", token)
                .body(service.filterUser(name, username));
    }

    @GetMapping("/its-backoffice/users")
    public ResponseEntity<List<User>> retrieveUsers(@RequestHeader (name="Authorization") String token){
        return ResponseEntity.ok()
                .header("Access-Control-Expose-Headers", "*")
                .header("Authorization", token)
                .body(service.retrieveUsers());
    }

    @PostMapping("/its-backoffice/users")
    public ResponseEntity<User> createUser(@RequestBody User user, @RequestHeader (name="Authorization") String token){
        return ResponseEntity.ok()
                .header("Access-Control-Expose-Headers", "*")
                .header("Authorization", token)
                .body(service.createUser(user));
    }

    @DeleteMapping("/its-backoffice/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id, @RequestHeader (name="Authorization") String token){
        service.deleteUser(id);
        return ResponseEntity.ok()
                .header("Access-Control-Expose-Headers", "*")
                .header("Authorization", token)
                .body("");
    }

    @PutMapping("/its-backoffice/users")
    public ResponseEntity<String> updateUser(@RequestBody User model, @RequestHeader (name="Authorization") String token){
        return ResponseEntity.ok()
                .header("Access-Control-Expose-Headers", "*")
                .header("Authorization", token)
                .body(service.updateUser(model));
    }
}