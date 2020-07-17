package com.mohammadalsalkini.accountManager.resource;

import com.mohammadalsalkini.accountManager.domain.Account;
import com.mohammadalsalkini.accountManager.domain.EventStatistics;
import com.mohammadalsalkini.accountManager.exception.AccountNotFoundException;
import com.mohammadalsalkini.accountManager.repository.AccountRepository;
import com.mohammadalsalkini.accountManager.service.EventStatisticsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * @project accountManager
 * @auther Mohammad Alsalkini
 * @ceated on 15.07.2020 - 15:38
 */
@RestController
@RequestMapping("/api/accounts")
public class EventStatisticsResource {

    AccountRepository accountRepository;
    EventStatisticsService eventStatisticsService;

    public EventStatisticsResource(AccountRepository accountRepository, EventStatisticsService eventStatisticsService) {
        this.accountRepository = accountRepository;
        this.eventStatisticsService = eventStatisticsService;
    }

    @GetMapping(path = "/{id}/statistics", produces = "application/json")
    public ResponseEntity<List<EventStatistics>> retrieveAccountStatistics(@PathVariable("id") long id) {

        Optional<Account> optionalAccount = accountRepository.findById(id);

        if (optionalAccount.isEmpty()) {
            throw new AccountNotFoundException("id: " + id);
        }

        Optional<List<EventStatistics>> accountStatisticsOptional = eventStatisticsService.findAccountStatistics(optionalAccount.get());
        if (accountStatisticsOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(accountStatisticsOptional.get(), HttpStatus.OK);
    }

    @GetMapping(path = "/statistics", produces = "application/json")
    public ResponseEntity<List<EventStatistics>> retrieveAllStatistics() {

        List<EventStatistics> statistics = eventStatisticsService.findAllStatistics();

        if (statistics == null || statistics.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(statistics, HttpStatus.OK);
    }
}
