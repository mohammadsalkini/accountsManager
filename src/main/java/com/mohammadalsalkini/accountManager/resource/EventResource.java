package com.mohammadalsalkini.accountManager.resource;

import com.mohammadalsalkini.accountManager.domain.Account;
import com.mohammadalsalkini.accountManager.domain.Event;
import com.mohammadalsalkini.accountManager.exception.AccountNotFoundException;
import com.mohammadalsalkini.accountManager.repository.AccountRepository;
import com.mohammadalsalkini.accountManager.repository.EventRepository;
import com.mohammadalsalkini.accountManager.service.EventStatisticsService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

/**
 * @project accountManager
 * @auther Mohammad Alsalkini
 * @ceated on 15.07.2020 - 11:28
 */
@RestController
@RequestMapping("/api/accounts")
public class EventResource {

    private final AccountRepository accountRepository;
    private final EventRepository eventRepository;
    private final EventStatisticsService eventStatisticsService;

    public EventResource(AccountRepository accountRepository, EventRepository eventRepository, EventStatisticsService eventStatisticsService) {
        this.accountRepository = accountRepository;
        this.eventRepository = eventRepository;
        this.eventStatisticsService = eventStatisticsService;
    }

    @GetMapping(path = "/{id}/events", produces = "application/json")
    public ResponseEntity<List<Event>> retrieveAllEvents(@PathVariable long id) {

        Optional<Account> optionalAccount = accountRepository.findById(id);

        if (optionalAccount.isEmpty()) {
            throw new AccountNotFoundException("id: " + id);
        }

        List<Event> events = optionalAccount.get().getEvents();

        if (events.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @PostMapping(path = "/{id}/events", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Event> createEvent(@PathVariable long id, @RequestBody Event event,
                                             UriComponentsBuilder builder) {

        Optional<Account> optionalAccount = accountRepository.findById(id);

        if (optionalAccount.isEmpty()) {
            throw new RuntimeException();
        }

        Account account = optionalAccount.get();
        event.setAccount(account);
        account.addEvent(event);

        eventStatisticsService.updateEventStatics(event.getCreatedAt().toLocalDate(), event.getType(), account);

        Event savedEvent = eventRepository.save(event);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("api/accounts/{id}").buildAndExpand(account.getId()).toUri());
        return new ResponseEntity<>(savedEvent, headers, HttpStatus.CREATED);

    }

}
