package com.example.demo.controller;

import com.example.demo.exception.ActivityNotFoundException;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.models.Activity;

import com.example.demo.models.HomeGroup;
import com.example.demo.models.User;
import com.example.demo.repository.ActivityRepository;

import com.example.demo.repository.HomeGroupRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
public class ActivityController {

    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    HomeGroupRepository homeGroupRepository;


    //создать запись в таблице activitys
    @PostMapping("/activitys")
    public Activity createActivity(@Valid @RequestBody Activity activity) {
        return activityRepository.save(activity);
    }

    //получить все записи из таблицы activitys
    @GetMapping("/activitys")
    public List getAllActivitys() {
        return activityRepository.findAll();
    }

    //получить запись по id  из тaблицы activitys
    @GetMapping("/activitys/{id}")
    public Activity getAllActivityById(@PathVariable(value = "id") Long activityId) throws ActivityNotFoundException {
        return activityRepository.findById(activityId).orElseThrow(() -> new ActivityNotFoundException(activityId));
    }

    //удалить запись по id из таблицы activity
    @DeleteMapping("/activitys/{id}")
    public ResponseEntity deleteActivity(@PathVariable(value = "id") Long activityId) throws ActivityNotFoundException {
        Activity activity = activityRepository.findById(activityId).orElseThrow(() -> new ActivityNotFoundException(activityId));

        activityRepository.delete(activity);
        return ResponseEntity.ok().build();

    }

    //обновить запись в таблице activity
    @PutMapping("/activitys/{id}")
    public Activity updateActivity(@PathVariable(value = "id") Long activityId,
                           @Valid @RequestBody Activity activityDetails) throws ActivityNotFoundException {
        Activity activity = activityRepository.findById(activityId).orElseThrow(()-> new ActivityNotFoundException(activityId));

        activity.setFavoriteActivity(activityDetails.getFavoriteActivity());

        Activity updateActivity = activityRepository.save(activity);
        return updateActivity;
    }

    // получить всех user-oв по activity_id
    @GetMapping("activitys/{id}/users")
    public Set<User> getUsers(@PathVariable Long id){

        return this.activityRepository.findById(id).map((activity) -> {
            return activity.getUsers();
        }).orElseThrow(() -> new ResourceNotFoundException("Activity", id));
    }

    // получить все homeGroup по activity_id
    @GetMapping("activitys/{id}/homeGroups")
    public Set<HomeGroup> getHomeGroups(@PathVariable Long id){

        return this.activityRepository.findById(id).map((activity) -> {
            return activity.getHomeGroups();
        }).orElseThrow(() -> new ResourceNotFoundException("Activity", id));
    }
}
