package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.UserDto;
import org.example.model.User;
import org.example.service.UserService;
import org.example.session.AuthContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final AuthContext authContext;

    @GetMapping
    public String getUsers(final Model model) {
        final List<User> users = userService.findUsers();
        model.addAttribute("users", users);
        model.addAttribute("dto", new UserDto());
        return "users";
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String createUser(
            @Valid @ModelAttribute("dto") final UserDto dto,
            final BindingResult result,
            final Model model
    ) {
        if (!result.hasErrors()) {
            userService.createUser(dto.getName(), dto.getPassword(), dto.getRole());
            authContext.setAuthorized(true);
        }

        final List<User> users = userService.findUsers();
        model.addAttribute("users", users);
        return "users";
    }
}
