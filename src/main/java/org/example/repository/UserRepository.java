package org.example.repository;

import org.example.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> findUsers();
    Optional<User> getUser(String name);
    void createUser(String name, String password, String role);
}
