package com.mohammadalsalkini.accountManager;

import com.mohammadalsalkini.accountManager.repository.EventRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

/**
 * @project accountManager
 * @auther Mohammad Alsalkini
 * @ceated on 15.07.2020 - 16:10
 */
@Service
public class Cleaner /*implements ApplicationListener<ContextRefreshedEvent>*/ {

    final EventRepository eventRepository;

    @Value("${clean.days}")
    private int cleaningPeriod;

    public Cleaner(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    //@Override
    @Transactional
    @Scheduled(cron = "${scheduled.cron.expression}")
    public void onApplicationEvent() {
        eventRepository.deleteByCreatedAtBefore(LocalDateTime.now().minusDays(cleaningPeriod));
    }
}
