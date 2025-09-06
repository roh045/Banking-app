package com.mybank.bank.web;

import com.mybank.bank.model.Account;
import com.mybank.bank.model.User;
import com.mybank.bank.repo.UserRepository;
import com.mybank.bank.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AccountController {

    private final AccountService accountService;
    private final UserRepository userRepository;

    @GetMapping("/balance")
    public ResponseEntity<?> getBalance() {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userRepository.findByEmail(auth.getName())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            Account account = accountService.getAccountByUser(user)
                    .orElseGet(() -> accountService.createAccount(user));

            Map<String, Object> response = new HashMap<>();
            response.put("accountNumber", account.getAccountNumber());
            response.put("balance", account.getBalance());
            response.put("userName", user.getName());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestBody Map<String, Object> request) {
        try {
            BigDecimal amount = new BigDecimal(request.get("amount").toString());
            if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                return ResponseEntity.badRequest().body("Amount must be positive");
            }

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userRepository.findByEmail(auth.getName())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            Account account = accountService.getAccountByUser(user)
                    .orElseGet(() -> accountService.createAccount(user));

            BigDecimal newBalance = account.getBalance().add(amount);
            account = accountService.updateBalance(account, newBalance);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Deposit successful");
            response.put("newBalance", account.getBalance());
            response.put("amount", amount);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/withdraw")
    public ResponseEntity<?> withdraw(@RequestBody Map<String, Object> request) {
        try {
            BigDecimal amount = new BigDecimal(request.get("amount").toString());
            if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                return ResponseEntity.badRequest().body("Amount must be positive");
            }

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userRepository.findByEmail(auth.getName())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            Account account = accountService.getAccountByUser(user)
                    .orElseGet(() -> accountService.createAccount(user));

            if (account.getBalance().compareTo(amount) < 0) {
                return ResponseEntity.badRequest().body("Insufficient balance");
            }

            BigDecimal newBalance = account.getBalance().subtract(amount);
            account = accountService.updateBalance(account, newBalance);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Withdrawal successful");
            response.put("newBalance", account.getBalance());
            response.put("amount", amount);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}

