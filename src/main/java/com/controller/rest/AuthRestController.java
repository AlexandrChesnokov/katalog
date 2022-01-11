package com.controller.rest;

import com.dto.AuthRequestDto;
import com.model.User;
import com.security.jwt.JwtTokenProvider;
import com.service.UserService;
import com.util.RestValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("api/")
public class AuthRestController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    private final RestValidator validator;

    public AuthRestController(AuthenticationManager authenticationManager, RestValidator validator, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.validator = validator;
    }

    @PostMapping(value = "login", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> login(@RequestBody AuthRequestDto requestDto) {

        try {
            String email = requestDto.getEmail();
            log.info("REST: Login request for user {} received, authentication in progress ...", email);
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, requestDto.getPassword()));
            User user = userService.findByEmail(email);

            if (user == null) {
                log.info("REST: User with {} not found", email);
                throw new UsernameNotFoundException("User with email  " + email + " not found");
            }

            String token = jwtTokenProvider.createToken(email, user.getRole());
            Map<Object, Object> response = new HashMap<>();
            response.put("email", email);
            response.put("token", token);
            log.info("REST: Authentication for user {} successful", email);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            log.info("REST: Authentication failed, incorrect password entered for user - {}", requestDto.getEmail());
            throw new BadCredentialsException("invalid username or password");
        }
    }

    @PostMapping(value = "signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> signup(@RequestBody User user) throws SQLException {

        String validateResult = "";

        log.info("REST: Registration request received for user {}, validation in progress ...", user.getEmail());

        validateResult = validator.registerFormValidate(user);

        if (!validateResult.equals("ok")) {
            log.info("REST: Validation for user {} - failed. Result: {}", user.getEmail(), validateResult);
            return new ResponseEntity<>(validateResult, HttpStatus.BAD_REQUEST);
        }
        userService.save(user);
        log.info("REST: Registration successful for user - {}", user.getEmail());

        return ResponseEntity.ok("success");
    }
}
