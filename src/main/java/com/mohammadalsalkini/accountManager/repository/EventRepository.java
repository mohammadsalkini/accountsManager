package com.mohammadalsalkini.accountManager.repository;

import com.mohammadalsalkini.accountManager.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * @project accountManager
 * @auther Mohammad Alsalkini
 * @ceated on 15.07.2020 - 10:12
 */
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    void deleteByCreatedAtBefore(LocalDateTime minutes);


}
