package com.mybank.bank.web;

import com.mybank.bank.model.User;
import com.mybank.bank.repo.UserRepository;
import com.mybank.bank.security.JwtService;
import com.mybank.bank.service.AccountService;
import com.mybank.bank.web.dto.AuthDtos;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AccountService accountService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody AuthDtos.SignupRequest request) {
        try {
            if (userRepository.existsByEmail(request.getEmail())) {
                return ResponseEntity.badRequest().body("Email already exists");
            }

            User user = new User();
            user.setName(request.getName());
            user.setEmail(request.getEmail());
            user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
            user.setPhone(request.getPhone());

            User savedUser = userRepository.save(user);
            
            // Create account for the new user
            accountService.createAccount(savedUser);

            UserDetails userDetails = org.springframework.security.core.userdetails.User
                    .withUsername(savedUser.getEmail())
                    .password(savedUser.getPasswordHash())
                    .authorities("USER")
                    .build();

            String token = jwtService.generateToken(userDetails);

            AuthDtos.UserDto userDto = new AuthDtos.UserDto(
                    savedUser.getId(),
                    savedUser.getName(),
                    savedUser.getEmail(),
                    savedUser.getPhone()
            );

            return ResponseEntity.ok(new AuthDtos.AuthResponse(token, userDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Signup failed: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthDtos.LoginRequest request) {
        try {
            User user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new BadCredentialsException("Invalid credentials"));

            if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
                throw new BadCredentialsException("Invalid credentials");
            }

            UserDetails userDetails = org.springframework.security.core.userdetails.User
                    .withUsername(user.getEmail())
                    .password(user.getPasswordHash())
                    .authorities("USER")
                    .build();

            String token = jwtService.generateToken(userDetails);

            AuthDtos.UserDto userDto = new AuthDtos.UserDto(
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getPhone()
            );

            return ResponseEntity.ok(new AuthDtos.AuthResponse(token, userDto));
        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body("Login failed: Invalid credentials");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Login failed: " + e.getMessage());
        }
    }
}
