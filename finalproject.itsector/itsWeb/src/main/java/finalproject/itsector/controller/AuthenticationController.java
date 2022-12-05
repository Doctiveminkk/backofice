package finalproject.itsector.controller;

import finalproject.itsector.JWT.provider.JwtTokenProvider;
import finalproject.itsector.model.RegisteredUser;
import finalproject.itsector.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


//Rest Controller For Authentication EndPoint
@RestController
@Slf4j
public class AuthenticationController{
    @Autowired
    private AuthenticationService service;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @PostMapping("/its-backoffice/login")
    public ResponseEntity customHeader(@RequestBody RegisteredUser data) {
        try {
            String username = data.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            String token = "Bearer "+jwtTokenProvider.createToken(username, service.loadUserByUsername(username).getRoles());
            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("token", token);
            return ResponseEntity.ok()
                    .header("Access-Control-Expose-Headers", "*")
                    .header("Authorization", token)
                    .body(model);
        } catch (AuthenticationException e) {
            log.error("Error: "+ e);
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }
}