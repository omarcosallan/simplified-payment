package com.marcos.simplified_payment.repository;

import com.marcos.simplified_payment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    boolean existsByDocument(String document);
    boolean existsByEmail(String email);
}
