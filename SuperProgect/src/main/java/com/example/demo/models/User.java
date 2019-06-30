package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fullName")
    private String fullName;

    @Column(name = "dateOfBirth")
    private String dateOfBirth;

    @Column(name = "phoneNumber")
    private Long phoneNumber;

    @Column(name = "email")
    private String email;

    //связь с таблицей ativity
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    @JoinTable(
            name = "user_activity",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "activity_id")}

    )

    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Set<Activity> activitys = new HashSet<>();

    //связь с таблицей prayer
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    @JoinTable(
            name = "user_prayer",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "prayer_id")}

    )

    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Set<Prayer> prayers = new HashSet<>();

    //связь с таблицей homeGroup
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "homeGroup_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private HomeGroup homeGroup;

    //связь с таблицей home_group_dates
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    @JoinTable(
            name = "user_homeGroupDate",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "homeGroupDate_id")}

    )

    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Set<HomeGroupDate> homeGroupDates = new HashSet<>();

    //связь с таблицей prayer_dates
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    @JoinTable(
            name = "user_prayerDate",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "prayerDate_id")}

    )

    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Set<PrayerDate> prayerDates = new HashSet<>();

    //конструкторы
    public User() {
    }

    public User(String fullName, String dateOfBirth, Long phoneNumber, String email, Set<Activity> activitys) {
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.activitys = activitys;
    }

    public User(String fullName, String dateOfBirth, Long phoneNumber, String email, Set<Activity> activitys, Set<Prayer> prayers) {
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.activitys = activitys;
        this.prayers = prayers;
    }

    public User(String fullName, String dateOfBirth, Long phoneNumber, String email, HomeGroup homeGroup) {
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.homeGroup = homeGroup;
    }

    public User(String fullName, String dateOfBirth, Long phoneNumber, String email, Set<Activity> activitys, Set<Prayer> prayers, HomeGroup homeGroup) {
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.activitys = activitys;
        this.prayers = prayers;
        this.homeGroup = homeGroup;
    }

    public User(String fullName, String dateOfBirth, Long phoneNumber, String email, Set<Activity> activitys, Set<Prayer> prayers, HomeGroup homeGroup, Set<HomeGroupDate> homeGroupDates) {
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.activitys = activitys;
        this.prayers = prayers;
        this.homeGroup = homeGroup;
        this.homeGroupDates = homeGroupDates;
    }

    public User(String fullName, String dateOfBirth, Long phoneNumber, String email, Set<Activity> activitys, Set<Prayer> prayers, HomeGroup homeGroup, Set<HomeGroupDate> homeGroupDates, Set<PrayerDate> prayerDates) {
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.activitys = activitys;
        this.prayers = prayers;
        this.homeGroup = homeGroup;
        this.homeGroupDates = homeGroupDates;
        this.prayerDates = prayerDates;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Activity> getActivitys() {
        return activitys;
    }

    public void setActivitys(Set<Activity> activitys) {
        this.activitys = activitys;
    }

    public Set<Prayer> getPrayers() {
        return prayers;
    }

    public void setPrayers(Set<Prayer> prayers) {
        this.prayers = prayers;
    }

    public HomeGroup getHomeGroup() {
        return homeGroup;
    }

    public void setHomeGroup(HomeGroup homeGroup) {
        this.homeGroup = homeGroup;
    }

    public Set<HomeGroupDate> getHomeGroupDates() {
        return homeGroupDates;
    }

    public void setHomeGroupDates(Set<HomeGroupDate> homeGroupDates) {
        this.homeGroupDates = homeGroupDates;
    }

    public Set<PrayerDate> getPrayerDates() {
        return prayerDates;
    }

    public void setPrayerDates(Set<PrayerDate> prayerDates) {
        this.prayerDates = prayerDates;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                ", activitys=" + activitys +
                ", prayers=" + prayers +
                ", homeGroup=" + homeGroup +
                ", homeGroupDates=" + homeGroupDates +
                ", prayerDates=" + prayerDates +
                '}';
    }
}
