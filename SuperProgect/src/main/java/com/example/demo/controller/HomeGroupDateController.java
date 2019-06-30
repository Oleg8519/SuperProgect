package com.example.demo.controller;

import com.example.demo.exception.HomeGroupDateNotFoundException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.models.HomeGroup;
import com.example.demo.models.HomeGroupDate;
import com.example.demo.models.User;
import com.example.demo.repository.HomeGroupDateRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
public class HomeGroupDateController {

    @Autowired
    HomeGroupDateRepository homeGroupDateRepository;

    @Autowired
    UserRepository userRepository;

    //создать запись в таблице home_group_dates
    @PostMapping("/homeGroupDates")
    public HomeGroupDate createHomeGroupDate(@Valid @RequestBody HomeGroupDate homeGroupDate) {
        return homeGroupDateRepository.save(homeGroupDate);
    }

    //получить все записи из таблицы home_group_dates
    @GetMapping("/homeGroupDates")
    public List getAllHomeGroupDate() {
        return homeGroupDateRepository.findAll();
    }

    //получить запись по id  из тaблицы home_group_dates
    @GetMapping("/homeGroupDates/{id}")
    public HomeGroupDate getAllHomeGroupDateById(@PathVariable(value = "id") Long homeGroupDateId) throws HomeGroupDateNotFoundException {
        return homeGroupDateRepository.findById(homeGroupDateId).orElseThrow(() -> new HomeGroupDateNotFoundException(homeGroupDateId));
    }

    //удалить запись по id из таблицы home_group_dates
    @DeleteMapping("/homeGroupDates/{id}")
    public ResponseEntity deleteHomeGroupDate(@PathVariable(value = "id") Long homeGroupDateId) throws HomeGroupDateNotFoundException {
        HomeGroupDate homeGroupDate = homeGroupDateRepository.findById(homeGroupDateId).orElseThrow(() -> new HomeGroupDateNotFoundException(homeGroupDateId));

        homeGroupDateRepository.delete(homeGroupDate);
        return ResponseEntity.ok().build();

    }

    //обновить запись в таблице home_group_dates
    @PutMapping("/homeGroupDates/{id}")
    public HomeGroupDate updateHomeGroupDate(@PathVariable(value = "id") Long homeGroupDateId,
                               @Valid @RequestBody HomeGroupDate homeGroupDateDetails) throws HomeGroupDateNotFoundException {
        HomeGroupDate homeGroupDate = homeGroupDateRepository.findById(homeGroupDateId).orElseThrow(()-> new HomeGroupDateNotFoundException(homeGroupDateId));

        homeGroupDate.setDate(homeGroupDateDetails.getDate());
        homeGroupDate.setTime(homeGroupDateDetails.getTime());

        HomeGroupDate updateHomeGroupDate = homeGroupDateRepository.save(homeGroupDate);
        return updateHomeGroupDate;
    }

    // получить всех user-oв по homeGroupDate_id
    @GetMapping("homeGroupDates/{id}/users")
    public Set<User> getUsers(@PathVariable Long id){

        return this.homeGroupDateRepository.findById(id).map((homeGroupDate) -> {
            return homeGroupDate.getUsers();
        }).orElseThrow(() -> new ResourceNotFoundException("HomeGroupDate", id));
    }

    // получить все homeGroup по homeGroupDate_id
    @GetMapping("homeGroupDates/{id}/homeGroups")
    public Set<HomeGroup> getHomeGroups(@PathVariable Long id){

        return this.homeGroupDateRepository.findById(id).map((homeGroupDate) -> {
            return homeGroupDate.getHomeGroups();
        }).orElseThrow(() -> new ResourceNotFoundException("HomeGroupDate", id));
    }
}

