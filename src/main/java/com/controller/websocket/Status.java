package com.controller.websocket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class Status {
    @RequestMapping(value = "/chat", method = GET, headers = "Connection!=Upgrade")
    public String status() {
        return "chat";
    }
}
