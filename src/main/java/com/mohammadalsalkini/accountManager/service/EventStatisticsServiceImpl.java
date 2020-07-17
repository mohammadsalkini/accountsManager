package com.mohammadalsalkini.accountManager.service;

import com.mohammadalsalkini.accountManager.domain.Account;
import com.mohammadalsalkini.accountManager.domain.EventStatistics;
import com.mohammadalsalkini.accountManager.repository.EventStatisticsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @project accountManager
 * @auther Mohammad Alsalkini
 * @ceated on 15.07.2020 - 13:55
 */
@Service
public class EventStatisticsServiceImpl implements EventStatisticsService {

    EventStatisticsRepository eventStatisticsRepository;

    public EventStatisticsServiceImpl(EventStatisticsRepository eventStatisticsRepository) {
        this.eventStatisticsRepository = eventStatisticsRepository;
    }

    @Override
    public EventStatistics updateEventStatics(LocalDate day, String type, Account account) {

        Optional<List<EventStatistics>> eventStatisticsOptional =
                eventStatisticsRepository.findAllEventStatisticsByDayAndType(day, type);

        if (eventStatisticsOptional.isEmpty()) {
            EventStatistics eventStatistic = new EventStatistics(day, type, 1);
            eventStatistic.setAccount(account);
            eventStatisticsRepository.save(eventStatistic);
            return eventStatistic;
        }

        for (EventStatistics eventStatistic : eventStatisticsOptional.get()) {
            if (eventStatistic.getAccount().equals(account)) {
                eventStatistic.setCount(eventStatistic.getCount() + 1);
                eventStatisticsRepository.save(eventStatistic);
                return eventStatistic;
            }
        }

        EventStatistics eventStatistic = new EventStatistics(day, type, 1);
        eventStatistic.setAccount(account);
        eventStatisticsRepository.save(eventStatistic);
        return eventStatistic;
    }

    @Override
    public Optional<List<EventStatistics>> findAccountStatistics(Account account) {
        return eventStatisticsRepository.findAllEventStatisticsByAccount(account);
    }

    @Override
    public List<EventStatistics> findAllStatistics() {
        return eventStatisticsRepository.findAll();

    }


}
