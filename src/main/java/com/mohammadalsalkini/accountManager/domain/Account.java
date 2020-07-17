package com.mohammadalsalkini.accountManager.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @project accountManager
 * @auther Mohammad Alsalkini
 * @ceated on 15.07.2020 - 09:51
 */
@ApiModel(description = "All details about the Accounts.")
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToMany(mappedBy = "account")
    @JsonIgnore
    private List<Event> events;

    @OneToMany(mappedBy = "account")
    @JsonIgnore
    private List<EventStatistics> eventStatistics;

    public Account() {
    }

    public Account(String name) {
        this.name = name;
    }

    public Account(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public void addEvent(Event event) {
        if (events == null) {
            events = new ArrayList<>();
        }
        events.add(event);
    }

    public List<EventStatistics> getEventStatistics() {
        return eventStatistics;
    }

    public void setEventStatistics(List<EventStatistics> eventStatistics) {
        this.eventStatistics = eventStatistics;
    }

    public void addEventStatistic(EventStatistics eventStatistic) {
        if (eventStatistics == null) {
            eventStatistics = new ArrayList<>();
        }
        eventStatistics.add(eventStatistic);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id &&
                Objects.equals(name, account.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", events=" + events +
                '}';
    }
}
