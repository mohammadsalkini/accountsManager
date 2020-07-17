package com.mohammadalsalkini.accountManager.service;

import com.mohammadalsalkini.accountManager.domain.Account;
import com.mohammadalsalkini.accountManager.domain.EventStatistics;
import com.mohammadalsalkini.accountManager.repository.EventStatisticsRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @project accountManager
 * @auther Mohammad Alsalkini
 * @ceated on 16.07.2020 - 12:37
 */
public class EventStatisticsServiceImplTest {

    EventStatisticsServiceImpl eventStatisticsService;

    @Mock
    EventStatisticsRepository eventStatisticsRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        eventStatisticsService = new EventStatisticsServiceImpl(eventStatisticsRepository);
    }

    @Test
    public void updateEventStatics() {

        EventStatistics eventStatistics =
                eventStatisticsService.updateEventStatics(LocalDate.now(), "someType", new Account());

        assertEquals("someType", eventStatistics.getType());
    }

    @Test
    public void findAccountStatistics() {
        EventStatistics eventStatistic1 = new EventStatistics();
        EventStatistics eventStatistic2 = new EventStatistics();
        Account account = new Account();
        account.addEventStatistic(eventStatistic1);
        account.addEventStatistic(eventStatistic2);

        when(eventStatisticsService.findAccountStatistics(account)).thenReturn(Optional.ofNullable(account.getEventStatistics()));
        Optional<List<EventStatistics>> accountStatistics = eventStatisticsService.findAccountStatistics(account);
        assertEquals(accountStatistics.get().size(), 2);

    }

    @Test
    public void findAllStatistics() {

        EventStatistics eventStatistic1 = new EventStatistics();
        EventStatistics eventStatistic2 = new EventStatistics();
        List<EventStatistics> statistics = new ArrayList<>();
        statistics.add(eventStatistic1);
        statistics.add(eventStatistic2);

        when(eventStatisticsService.findAllStatistics()).thenReturn(statistics);

        List<EventStatistics> allStatistics = eventStatisticsService.findAllStatistics();
        assertEquals(statistics.size(), 2);
        verify(eventStatisticsRepository, times(1)).findAll();


    }
}