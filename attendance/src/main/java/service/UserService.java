package com.classroom.attendance.service;

import com.classroom.attendance.entity.User;
import com.classroom.attendance.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean login(String username, String password) {

        Optional<User> user =
                userRepository.findByUsername(username);

        if (user.isPresent()) {

            User existingUser = user.get();

            return existingUser.getPassword().equals(password);
        }

        return false;
    }
}