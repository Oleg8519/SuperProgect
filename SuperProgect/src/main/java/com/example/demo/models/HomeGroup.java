package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "homeGroups")
public class HomeGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address")
    private String address;

    @Column(name = "nameOfLeader")
    private String nameOfLeader;

    //связь с таблицей activity
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    @JoinTable(
            name = "homeGroup_activity",
            joinColumns = {@JoinColumn(name = "homeGroup_id")},
            inverseJoinColumns = {@JoinColumn(name = "activity_id")}

    )

    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Set<Activity> activitys = new HashSet<>();

    //связь с таблицей home_group_date
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    @JoinTable(
            name = "homeGroup_homeGroupDate",
            joinColumns = {@JoinColumn(name = "homeGroup_id")},
            inverseJoinColumns = {@JoinColumn(name = "homeGroupDate_id")}

    )

    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Set<HomeGroupDate> homeGroupDates = new HashSet<>();

    //конструкторы

    public HomeGroup() {
    }

    public HomeGroup(String address, String nameOfLeader) {
        this.address = address;
        this.nameOfLeader = nameOfLeader;
    }

    public HomeGroup(String address, String nameOfLeader, Set<Activity> activitys) {
        this.address = address;
        this.nameOfLeader = nameOfLeader;
        this.activitys = activitys;
    }

    public HomeGroup(String address, String nameOfLeader, Set<Activity> activitys, Set<HomeGroupDate> homeGroupDates) {
        this.address = address;
        this.nameOfLeader = nameOfLeader;
        this.activitys = activitys;
        this.homeGroupDates = homeGroupDates;
    }

    //геттеры и сеттеры


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNameOfLeader() {
        return nameOfLeader;
    }

    public void setNameOfLeader(String nameOfLeader) {
        this.nameOfLeader = nameOfLeader;
    }

    public Set<Activity> getActivitys() {
        return activitys;
    }

    public void setActivitys(Set<Activity> activitys) {
        this.activitys = activitys;
    }

    public Set<HomeGroupDate> getHomeGroupDates() {
        return homeGroupDates;
    }

    public void setHomeGroupDates(Set<HomeGroupDate> homeGroupDates) {
        this.homeGroupDates = homeGroupDates;
    }

    @Override
    public String toString() {
        return "HomeGroup{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", nameOfLeader='" + nameOfLeader + '\'' +
                ", activitys=" + activitys +
                ", homeGroupDates=" + homeGroupDates +
                '}';
    }
}

