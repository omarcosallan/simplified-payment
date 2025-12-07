package com.marcos.simplified_payment.config;

import com.marcos.simplified_payment.entity.User;
import com.marcos.simplified_payment.entity.Wallet;
import com.marcos.simplified_payment.entity.enums.UserType;
import com.marcos.simplified_payment.repository.UserRepository;
import com.marcos.simplified_payment.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;

@Configuration
@Profile("test")
@RequiredArgsConstructor
public class TestConfig implements CommandLineRunner {

    private final UserRepository userRepository;
    private final WalletRepository walletRepository;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            User u1 = userRepository.save(new User(null, "John", UserType.COMMON, "11111111111", "john@gmail.com", "12345"));
            walletRepository.save(new Wallet(null, BigDecimal.valueOf(15500), u1));

            User u2 = userRepository.save(new User(null, "Bob", UserType.COMMON, "22222222222", "bob@gmail.com", "12345"));
            walletRepository.save(new Wallet(null, BigDecimal.valueOf(350), u2));

            User u3 = userRepository.save(new User(null, "Alex", UserType.MERCHANT, "33333333333", "alex@gmail.com", "12345"));
            walletRepository.save(new Wallet(null, BigDecimal.valueOf(4000), u3));
        }
    }
}
