package com.mohammadalsalkini.accountManager.repository;

import com.mohammadalsalkini.accountManager.domain.Account;
import com.mohammadalsalkini.accountManager.domain.EventStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @project accountManager
 * @auther Mohammad Alsalkini
 * @ceated on 15.07.2020 - 12:50
 */
@Repository
public interface EventStatisticsRepository extends JpaRepository<EventStatistics, Long> {

    Optional<List<EventStatistics>> findAllEventStatisticsByDayAndType(LocalDate day, String type);

    Optional<List<EventStatistics>> findAllEventStatisticsByAccount(Account account);
}
