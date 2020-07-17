package com.mohammadalsalkini.accountManager.domain;

import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

/**
 * @project accountManager
 * @auther Mohammad Alsalkini
 * @ceated on 15.07.2020 - 12:46
 */
@ApiModel(description = "All details about the Event Statistics.")
@Entity
public class EventStatistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDate day;
    private String type;
    private int count;

    @ManyToOne
    private Account account;

    public EventStatistics() {
    }

    public EventStatistics(LocalDate day, String type, int count) {
        this.day = day;
        this.type = type;
        this.count = count;
    }

    public long getId() {
        return id;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventStatistics that = (EventStatistics) o;
        return id == that.id &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }
}
