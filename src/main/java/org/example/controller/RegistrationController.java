package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.dto.UserDto;
import org.example.model.User;
import org.example.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;
import org.example.session.AuthContext;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {
    private final UserService userService;
    private final AuthContext authContext;


    @GetMapping
    protected String userRegistration(Model model) {
        model.addAttribute("dto", new UserDto());
        return "registration";
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public RedirectView userRegistration(@Valid @ModelAttribute("dto") final UserDto dto) {
        boolean isFree = userService.checkIsUsernameIsFree(dto.getName());
        System.out.println(isFree);
        if (isFree) {
            return new RedirectView("/registration");
        } else {
            userService.createUser(dto.getName(), dto.getPassword(), dto.getRole());
            authContext.setAuthorized(true);
            return new RedirectView("/users");
        }
//        boolean isFree = userService.checkIsUsernameIsFree(dto.getName());
//        System.out.println(isFree);
//        userService.createUser(dto.getName(), dto.getPassword(), dto.getRole());
//        authContext.setAuthorized(true);
//        return "users";
    }

}
