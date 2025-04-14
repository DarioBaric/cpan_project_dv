package com.CPAN_PROJECT_DV.cpan228_project_dv.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class LoginController {

    @GetMapping("/login")
    public String login() {
        log.info("Accessed login page");
        return "login";
    }
}