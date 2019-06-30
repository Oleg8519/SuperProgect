package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.UserGroupNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.models.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    HomeGroupRepository homeGroupRepository;

    @Autowired
    PrayerRepository prayerRepository;

    @Autowired
    HomeGroupDateRepository homeGroupDateRepository;

    @Autowired
    PrayerDateRepository prayerDateRepository;


    //создать запись в таблице users
    @PostMapping("/homeGroups/{homeGroupId}/users")
    public User createUser(@PathVariable (value = "homeGroupId") Long homeGroupId,
                           @Valid @RequestBody User user) {
        return homeGroupRepository.findById(homeGroupId).map(homeGroup -> {
            user.setHomeGroup(homeGroup);
            return userRepository.save(user);
        }).orElseThrow(() -> new UserGroupNotFoundException("HomeGroupId " + homeGroupId + " not found"));
    }


    //получить все записи из таблицы users
    @GetMapping("/users")
    public List getAllUsers() {
        return userRepository.findAll();
    }

    //получить запись по id  из тaблицы users
    @GetMapping("/users/{id}")
    public User getAllUserById(@PathVariable(value = "id") Long userId) throws UserNotFoundException {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }

    //удалить запись по id из таблицы users
    @DeleteMapping("/users/{id}")
    public ResponseEntity deleteUser(@PathVariable(value = "id") Long userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

        userRepository.delete(user);
        return ResponseEntity.ok().build();

    }

    //обновить запись в таблице users
    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable(value = "id") Long userId,
                           @Valid @RequestBody User userDetails) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException(userId));

        user.setDateOfBirth(userDetails.getDateOfBirth());
        user.setEmail(userDetails.getEmail());
        user.setFullName(userDetails.getFullName());
        user.setPhoneNumber(userDetails.getPhoneNumber());

        User updateUser = userRepository.save(user);
        return updateUser;
    }

    //связь id user-a c id activity
    @PostMapping("users/{id}/activitys/{activityId}")
    public Set<Activity> addActivity(@PathVariable Long id, @PathVariable Long activityId){

        Activity activity = this.activityRepository.findById(activityId).orElseThrow(
                () -> new ResourceNotFoundException("Activity", activityId)
        );


        return this.userRepository.findById(id).map((user) -> {
            user.getActivitys().add(activity);
            return this.userRepository.save(user).getActivitys();
        }).orElseThrow(() -> new ResourceNotFoundException("User", id));
    }

    //получить все activity для user-a
    @GetMapping("users/{userId}/activitys")
    public Set<Activity> getActivitys(@PathVariable Long userId){

        return this.userRepository.findById(userId).map((user) -> {
            return user.getActivitys();
        }).orElseThrow(() -> new ResourceNotFoundException("User", userId));
    }


    //связь id user-a c id prayer
    @PostMapping("users/{id}/prayers/{prayerId}")
    public Set<Prayer> addPrayer(@PathVariable Long id, @PathVariable Long prayerId){

        Prayer prayer = this.prayerRepository.findById(prayerId).orElseThrow(
                () -> new ResourceNotFoundException("Prayer", prayerId)
        );


        return this.userRepository.findById(id).map((user) -> {
            user.getPrayers().add(prayer);
            return this.userRepository.save(user).getPrayers();
        }).orElseThrow(() -> new ResourceNotFoundException("User", id));
    }

    //получить все prayer для user-a
    @GetMapping("users/{userId}/prayers")
    public Set<Prayer> getPrayers(@PathVariable Long userId){

        return this.userRepository.findById(userId).map((user) -> {
            return user.getPrayers();
        }).orElseThrow(() -> new ResourceNotFoundException("User", userId));
    }

    //связь id users c id home_group_date
    @PostMapping("users/{id}/homeGroupDates/{homeGroupDateId}")
    public Set<HomeGroupDate> addHomeGroupDate(@PathVariable Long id, @PathVariable Long homeGroupDateId){

        HomeGroupDate homeGroupDate = this.homeGroupDateRepository.findById(homeGroupDateId).orElseThrow(
                () -> new ResourceNotFoundException("HomeGroupDate", homeGroupDateId)
        );


        return this.userRepository.findById(id).map((user) -> {
            user.getHomeGroupDates().add(homeGroupDate);
            return this.userRepository.save(user).getHomeGroupDates();
        }).orElseThrow(() -> new ResourceNotFoundException("User", id));
    }

    //получить все homeGroupDate для user-a
    @GetMapping("users/{userId}/homeGroupDates")
    public Set<HomeGroupDate> getHomeGroupDates(@PathVariable Long userId){

        return this.userRepository.findById(userId).map((user) -> {
            return user.getHomeGroupDates();
        }).orElseThrow(() -> new ResourceNotFoundException("User", userId));
    }

    //связь id users c id prayer_date
    @PostMapping("users/{id}/prayerDates/{prayerDateId}")
    public Set<PrayerDate> addPrayerDate(@PathVariable Long id, @PathVariable Long prayerDateId){

        PrayerDate prayerDate = this.prayerDateRepository.findById(prayerDateId).orElseThrow(
                () -> new ResourceNotFoundException("PrayerDate", prayerDateId)
        );


        return this.userRepository.findById(id).map((user) -> {
            user.getPrayerDates().add(prayerDate);
            return this.userRepository.save(user).getPrayerDates();
        }).orElseThrow(() -> new ResourceNotFoundException("User", id));
    }

    //получить все prayer_date для user
    @GetMapping("users/{userId}/prayerDates")
    public Set<PrayerDate> getPrayerDates(@PathVariable Long userId){

        return this.userRepository.findById(userId).map((user) -> {
            return user.getPrayerDates();
        }).orElseThrow(() -> new ResourceNotFoundException("User", userId));
    }


}
