package com.codehunter.countdowntimernative.business;

import com.codehunter.countdowntimernative.domain.User;
import com.codehunter.countdowntimernative.jpa.JpaUser;
import com.codehunter.countdowntimernative.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public User createUser(User user) {
        if (userRepository.existsById(user.getId())) {
            throw new RuntimeException(String.format("User existed:  %s", user));
        }
        JpaUser jpaUser = new JpaUser();
        jpaUser.setName(user.getName());
        jpaUser.setId(user.getId());
        jpaUser.setEventList(Collections.emptyList());
        JpaUser newUser = userRepository.save(jpaUser);
        return toUser(newUser);
    }

    private static User toUser(JpaUser newUser) {
        return User.builder()
                .id(newUser.getId())
                .name(newUser.getName())
                .build();
    }

    public User findById(String id) {
        Optional<JpaUser> existedUser = userRepository.findById(id);
        return existedUser.map(UserService::toUser).orElse(null);
    }
}
