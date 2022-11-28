package finalproject.itsector.controller;

import finalproject.itsector.model.RegisteredUser;
import finalproject.itsector.model.User;
import finalproject.itsector.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController{
    @Autowired
    private AuthenticationService service;

    @GetMapping("/its-backoffice/login")
    public User getUser(@RequestParam String username){
        return service.getUserByUsername(username);
    }

    @PostMapping("/its-backoffice/login")
    ResponseEntity<String> customHeader() {
        return ResponseEntity.ok()
                .header("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJjaGlraXQiLCJuYW1lIjoiSm9obiBEb2UiLCJpYXQiOjE2Njk2NTkwMDB9.-D9bng8poWPfH6Lua4aKR0Iig0sUELjR43UW0XWo6GA")
                .body("Custom header set");
    }

    /*@PostMapping("/its-backoffice/login")
    public UserDetails login(@RequestParam String username){
        return service.loadUserByUsername(username);
    }*/
}