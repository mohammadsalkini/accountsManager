package com.mohammadalsalkini.accountManager.resource;

import com.mohammadalsalkini.accountManager.domain.Account;
import com.mohammadalsalkini.accountManager.exception.AccountNotFoundException;
import com.mohammadalsalkini.accountManager.repository.AccountRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * @project accountManager
 * @auther Mohammad Alsalkini
 * @ceated on 15.07.2020 - 10:14
 */
@RestController
@RequestMapping("/api/accounts")
public class AccountResource {

    private final AccountRepository accountRepository;

    public AccountResource(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping(path = {"", "/"}, produces = "application/json")
    public ResponseEntity<List<Account>> retrieveAllAccounts() {
        System.err.println("test");
        List<Account> accountsList = accountRepository.findAll();

        if (accountsList.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(accountsList, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<Account> retrieveAccount(@PathVariable long id) {

        return accountRepository.findById(id)
                .map(account -> new ResponseEntity<>(account, HttpStatus.OK))
                .orElseThrow(() -> new AccountNotFoundException("id: " + id));
    }

    @PostMapping(path = {"", "/"}, consumes = "application/json", produces = "application/json")
    public ResponseEntity<Account> createAccount(@RequestBody Account account, UriComponentsBuilder builder) {

        Account savedAccount = accountRepository.save(account);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("api/accounts/{id}").buildAndExpand(account.getId()).toUri());

        return new ResponseEntity<>(savedAccount, headers, HttpStatus.CREATED);
    }

    @PutMapping(path = "{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Account> updateAccount(@PathVariable("id") long id, @RequestBody Account account) {
        return accountRepository.findById(id)
                .map(accountToUpdate -> {
                    accountToUpdate.setName(account.getName());
                    accountToUpdate.setEvents(account.getEvents());

                    return new ResponseEntity<>(accountToUpdate, HttpStatus.OK);
                })
                .orElseThrow(() -> new AccountNotFoundException("id: " + id));
    }
}
