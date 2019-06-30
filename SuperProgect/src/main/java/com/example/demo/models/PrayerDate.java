package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "prayer_date")

public class PrayerDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private String date;

    @Column(name = "time")
    private String time;

    //связь с таблицей users
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            mappedBy = "prayerDates"
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Set<User> users = new HashSet<>();


    //связь с таблицей prayer
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            mappedBy = "prayerDates"
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Set<Prayer> prayers = new HashSet<>();

    //конструкторы

    public PrayerDate() {
    }

    public PrayerDate(String date, String time, Set<User> users) {
        this.date = date;
        this.time = time;
        this.users = users;
    }

    public PrayerDate(String date, String time, Set<User> users, Set<Prayer> prayers) {
        this.date = date;
        this.time = time;
        this.users = users;
        this.prayers = prayers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Prayer> getPrayers() {
        return prayers;
    }

    public void setPrayers(Set<Prayer> prayers) {
        this.prayers = prayers;
    }

    @Override
    public String toString() {
        return "PrayerDate{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", users=" + users +
                ", prayers=" + prayers +
                '}';
    }
}
