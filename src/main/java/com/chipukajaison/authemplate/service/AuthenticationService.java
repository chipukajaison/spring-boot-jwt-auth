package com.chipukajaison.authemplate.service;

import com.chipukajaison.authemplate.model.User;
import com.chipukajaison.authemplate.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.chipukajaison.authemplate.dto.request.RegisterRequest;
import com.chipukajaison.authemplate.model.enums.UserRole;

/**
 * @author Jaison.Chipuka on 6/6/2024
 * @project Auth Template
 * @email chipukajaison@gmail.com
 */
@Service
public class AuthenticationService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public UserDetails register(RegisterRequest registerRequest) throws Exception {
        if (userRepository.findByUsername(registerRequest.username()) != null) {
            throw new Exception("Username already exists");
        }
        String encodedPassword = passwordEncoder.encode(registerRequest.password());
        User newUser = new User(registerRequest.username(), encodedPassword, UserRole.valueOf(registerRequest.role().toUpperCase()));
        return userRepository.save(newUser);
    }
}
