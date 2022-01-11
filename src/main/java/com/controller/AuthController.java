package com.controller;

import com.model.User;
import com.service.UserService;
import com.util.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;

@Slf4j
@Controller
public class AuthController {

    private final UserValidator userValidator;

    private final UserService userService;

    public AuthController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @GetMapping("sign_up")
    public String getSignUp(Model model) {
        model.addAttribute("user", new User());
        return "sign_up";
    }

    @PostMapping("sign_up")
    public String signUp(@Valid @ModelAttribute("user") User user, BindingResult result) throws SQLException {

        log.info("MVC: Registration request received for user {}, validation in progress ...", user.getEmail());
        userValidator.validate(user, result);

        if (result.hasErrors()) {
            log.info("MVC: Validation for user {} - failed.", user.getEmail());
            return "sign_up";
        }
        userService.save(user);
        log.info("MVC: Registration successful for user - {}", user.getEmail());

        return "sign_in";
    }

    @RequestMapping("login")
    public String login(@RequestParam(name = "error", required = false) Boolean error,
                        Model model) {

        if (Boolean.TRUE.equals(error)) {
            model.addAttribute("error", true);
        }
        return "sign_in";
    }
}
