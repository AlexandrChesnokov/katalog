package com.util;


import com.model.User;
import com.service.UserService;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class RestValidator {

    private final UserService userService;

    private static final String EMAIL_REGEXP = "^[-a-z0-9!#$%&'*+/=?^_`{|}~ ]+(?:\\.[-a-z0-9!#$%&'*+/=?^_`{|}~]+" +
            ")*@(?:[a-z0-9]([-a-z0-9]{0,61}[a-z0-9])?\\.)*(?:aero|arpa|" +
            "asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum" +
            "|name|net|org|pro|tel|travel|[a-z][a-z])$";

    private static final String PHONE_REGEX = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$";

    private static final String NAME_REGEX = "^([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$";

    private static final String STATUS_OK = "ok";

    public RestValidator(UserService userService) {
        this.userService = userService;
    }

    public String registerFormValidate(User user) {

        if (!IsCorrectValue(user.getEmail(), EMAIL_REGEXP)) {
            return "invalid email format";
        }

        if (!IsCorrectValue(user.getPhone_number(), PHONE_REGEX)) {
            return "invalid phone format";
        }

        if (!IsCorrectValue(user.getFirstname(), NAME_REGEX)) {
            return "firstname invalid format";
        }

        if (!IsCorrectValue(user.getLastname(), NAME_REGEX)) {
            return "lastname invalid format";
        }

        if (user.getPassword().length() < 8 || user.getPassword().length() > 255) {
            return "password must be between 8 and 255 characters long";
        }

        if (userService.findByEmail(user.getEmail()) != null) {
            return "this email is already in use";
        }

        return STATUS_OK;

    }

    private boolean IsCorrectValue(String value, String regex) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(value);
        return m.matches();
    }



}
