package com.example.demo.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "prayer")

public class Prayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "LeaderName")
    private String LeaderName;

    @Column(name = "titlePrayer")
    private String titlePrayer;

    //связь с таблицей user
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            mappedBy = "prayers"
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Set<User> users = new HashSet<>();

    //связь с таблицей prayer_date
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    @JoinTable(
            name = "prayer_prayerDate",
            joinColumns = {@JoinColumn(name = "prayer_id")},
            inverseJoinColumns = {@JoinColumn(name = "prayerDate_id")}

    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Set<PrayerDate> prayerDates = new HashSet<>();

    public Prayer() {
    }

    public Prayer(String leaderName, String titlePrayer, Set<User> users) {
        LeaderName = leaderName;
        this.titlePrayer = titlePrayer;
        this.users = users;
    }

    public Prayer(String leaderName, String titlePrayer, Set<User> users, Set<PrayerDate> prayerDates) {
        LeaderName = leaderName;
        this.titlePrayer = titlePrayer;
        this.users = users;
        this.prayerDates = prayerDates;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLeaderName() {
        return LeaderName;
    }

    public void setLeaderName(String leaderName) {
        LeaderName = leaderName;
    }

    public String getTitlePrayer() {
        return titlePrayer;
    }

    public void setTitlePrayer(String titlePrayer) {
        this.titlePrayer = titlePrayer;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<PrayerDate> getPrayerDates() {
        return prayerDates;
    }

    public void setPrayerDates(Set<PrayerDate> prayerDates) {
        this.prayerDates = prayerDates;
    }

    @Override
    public String toString() {
        return "Prayer{" +
                "id=" + id +
                ", LeaderName='" + LeaderName + '\'' +
                ", titlePrayer='" + titlePrayer + '\'' +
                ", users=" + users +
                ", prayerDates=" + prayerDates +
                '}';
    }
}
