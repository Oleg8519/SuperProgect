package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "home_group_dates")

public class HomeGroupDate {

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
            mappedBy = "homeGroupDates"
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Set<User> users = new HashSet<>();

    //связь с таблицей home_groups
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            mappedBy = "homeGroupDates"
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Set<HomeGroup> homeGroups = new HashSet<>();

    //конструкторы


    public HomeGroupDate() {
    }

    public HomeGroupDate(String date, String time, Set<User> users) {
        this.date = date;
        this.time = time;
        this.users = users;
    }

    public HomeGroupDate(String date, String time, Set<User> users, Set<HomeGroup> homeGroups) {
        this.date = date;
        this.time = time;
        this.users = users;
        this.homeGroups = homeGroups;
    }

    //геттеры и сеттеры

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

    public Set<HomeGroup> getHomeGroups() {
        return homeGroups;
    }

    public void setHomeGroups(Set<HomeGroup> homeGroups) {
        this.homeGroups = homeGroups;
    }

    @Override
    public String toString() {
        return "HomeGroupDate{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", users=" + users +
                ", homeGroups=" + homeGroups +
                '}';
    }
}