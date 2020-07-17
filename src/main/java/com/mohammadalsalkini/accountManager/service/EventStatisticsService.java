package com.mohammadalsalkini.accountManager.service;

import com.mohammadalsalkini.accountManager.domain.Account;
import com.mohammadalsalkini.accountManager.domain.EventStatistics;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @project accountManager
 * @auther Mohammad Alsalkini
 * @ceated on 15.07.2020 - 13:54
 */
public interface EventStatisticsService {

    EventStatistics updateEventStatics(LocalDate day, String type, Account account);

    Optional<List<EventStatistics>> findAccountStatistics(Account account);

    List<EventStatistics> findAllStatistics();

}
