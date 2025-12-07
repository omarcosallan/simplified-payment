package com.marcos.simplified_payment.service;

import com.marcos.simplified_payment.entity.User;
import com.marcos.simplified_payment.exception.NotFoundException;
import com.marcos.simplified_payment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
