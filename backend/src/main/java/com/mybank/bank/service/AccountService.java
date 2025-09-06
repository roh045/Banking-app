package com.mybank.bank.service;

import com.mybank.bank.model.Account;
import com.mybank.bank.model.User;
import com.mybank.bank.repo.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final Random random = new Random();

    public Account createAccount(User user) {
        String accountNumber = generateAccountNumber();
        Account account = new Account();
        account.setAccountNumber(accountNumber);
        account.setBalance(BigDecimal.ZERO);
        account.setUser(user);
        return accountRepository.save(account);
    }

    public Optional<Account> getAccountByUser(User user) {
        List<Account> accounts = accountRepository.findByUser(user);
        return accounts.isEmpty() ? Optional.empty() : Optional.of(accounts.get(0));
    }

    public Optional<Account> getAccountByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }

    public Account updateBalance(Account account, BigDecimal newBalance) {
        account.setBalance(newBalance);
        return accountRepository.save(account);
    }

    private String generateAccountNumber() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            sb.append(random.nextInt(10));
            if (i == 3 || i == 7) {
                sb.append("-");
            }
        }
        return sb.toString();
    }
}

