package com.mohammadalsalkini.accountManager.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @project accountManager
 * @auther Mohammad Alsalkini
 * @ceated on 15.07.2020 - 09:58
 */
@ApiModel(description = "All details about the Events.")
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String type;
    private LocalDateTime createdAt;

    @ManyToOne
    @JsonIgnore
    private Account account;

    public Event() {

        createdAt = LocalDateTime.now();
    }

    public Event(String type, LocalDateTime createdAt) {

        this.type = type;
        this.createdAt = createdAt;
    }

    public Event(long id, String type, LocalDateTime createdAt) {

        this.id = id;
        this.type = type;
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
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
        Event event = (Event) o;
        return id == event.id &&
                Objects.equals(createdAt, event.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt);
    }

}
