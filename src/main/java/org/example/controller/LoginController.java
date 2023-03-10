package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.LoginDto;
import org.example.dto.UserDto;
import org.example.service.UserService;
import org.example.session.AuthContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

@Controller
@Validated
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {
    private final UserService userService;
    private final AuthContext authContext;

    @GetMapping
    protected String userLogin(Model model) {
        model.addAttribute("dto", new LoginDto());
        return "login";
    }

    @PostMapping
    protected RedirectView userAuthorization(Model model, @Valid @ModelAttribute("dto") LoginDto dto) {
        boolean isUserCreated = userService.findByUsernameAndPassword(dto.getName(), dto.getPassword());
        System.out.println(isUserCreated);
        if (isUserCreated) {
            return new RedirectView("/main");
        } else {
            return new RedirectView("login");
        }
    }
}
