package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findUsers() {
        return userRepository.findUsers();
    }

    public boolean checkIsUsernameIsFree(String name) {
        Optional<User> checkingUser = userRepository.getUser(name);
        return checkingUser.isPresent();
    }

    public void createUser(String name, String password, String role) {
        if (userRepository.getUser(name).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        userRepository.createUser(name, password, role);
    }
}
