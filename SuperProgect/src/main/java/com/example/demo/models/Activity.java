package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "activity")

public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "favoriteActivity")
    private String favoriteActivity;

    @Column(name = "LeaderName")
    private String LeaderName;

    //связь с таблицей users
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            mappedBy = "activitys"
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Set<User> users = new HashSet<>();

    //связь с таблицей home_groups
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            mappedBy = "activitys"
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Set<HomeGroup> homeGroups = new HashSet<>();

    //конструкторы

    public Activity() {
    }

    public Activity(String favoriteActivity, String leaderName, Set<User> users) {
        this.favoriteActivity = favoriteActivity;
        LeaderName = leaderName;
        this.users = users;
    }

    public Activity(String favoriteActivity, String leaderName, Set<User> users, Set<HomeGroup> homeGroups) {
        this.favoriteActivity = favoriteActivity;
        LeaderName = leaderName;
        this.users = users;
        this.homeGroups = homeGroups;
    }

    // геттеры и сеттеры


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFavoriteActivity() {
        return favoriteActivity;
    }

    public void setFavoriteActivity(String favoriteActivity) {
        this.favoriteActivity = favoriteActivity;
    }

    public String getLeaderName() {
        return LeaderName;
    }

    public void setLeaderName(String leaderName) {
        LeaderName = leaderName;
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
        return "Activity{" +
                "id=" + id +
                ", favoriteActivity='" + favoriteActivity + '\'' +
                ", LeaderName='" + LeaderName + '\'' +
                ", users=" + users +
                ", homeGroups=" + homeGroups +
                '}';
    }
}
