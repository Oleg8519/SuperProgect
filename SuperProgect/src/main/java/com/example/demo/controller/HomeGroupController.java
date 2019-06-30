package com.example.demo.controller;

import com.example.demo.exception.HomeGroupNotFoundException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.models.Activity;
import com.example.demo.models.HomeGroup;
import com.example.demo.models.HomeGroupDate;
import com.example.demo.models.Prayer;
import com.example.demo.repository.ActivityRepository;
import com.example.demo.repository.HomeGroupDateRepository;
import com.example.demo.repository.HomeGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
public class HomeGroupController {

    @Autowired
    HomeGroupRepository homeGroupRepository;

    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    HomeGroupDateRepository homeGroupDateRepository;

    //создать запись в таблице
    @PostMapping("/homeGroups")
    public HomeGroup createGroup(@Valid @RequestBody HomeGroup homeGroup){
        return homeGroupRepository.save(homeGroup);
    }
    //получить все записи из таблицы home_group
    @GetMapping("/homeGroups")
    public List getAllHomeGroups() {
        return homeGroupRepository.findAll();
    }

    //получить запись по id  из тaблицы home_group
    @GetMapping("/homeGroups/{id}")
    public HomeGroup getAllHomeGroupById(@PathVariable(value = "id") Long homeGroupId) throws HomeGroupNotFoundException {
        return homeGroupRepository.findById(homeGroupId).orElseThrow(() -> new HomeGroupNotFoundException(homeGroupId));
    }

    //удалить запись по id из таблицы home_group
    @DeleteMapping("/homeGroups/{id}")
    public ResponseEntity deleteHomeGroups(@PathVariable(value = "id") Long homeGroupId) throws HomeGroupNotFoundException {
        HomeGroup homeGroup = homeGroupRepository.findById(homeGroupId).orElseThrow(() -> new HomeGroupNotFoundException(homeGroupId));

        homeGroupRepository.delete(homeGroup);
        return ResponseEntity.ok().build();

    }

    //обновить запись в таблице home_group
    @PutMapping("/homeGroups/{id}")
    public HomeGroup updateHomeGroup(@PathVariable(value = "id") Long homeGroupId,
                                     @Valid @RequestBody HomeGroup homeGroupDetails) throws HomeGroupNotFoundException {
        HomeGroup homeGroup = homeGroupRepository.findById(homeGroupId).orElseThrow(()-> new HomeGroupNotFoundException(homeGroupId));

        homeGroup.setAddress(homeGroupDetails.getAddress());
        homeGroup.setNameOfLeader(homeGroupDetails.getNameOfLeader());

        HomeGroup updateHomeGroup = homeGroupRepository.save(homeGroup);
        return updateHomeGroup;
    }

    //связь id home_groups c id activity
    @PostMapping("homeGroups/{id}/activitys/{activityId}")
    public Set<Activity> addActivity(@PathVariable Long id, @PathVariable Long activityId){

        Activity activity = this.activityRepository.findById(activityId).orElseThrow(
                () -> new ResourceNotFoundException("Activity", activityId)
        );


        return this.homeGroupRepository.findById(id).map((homeGroup) -> {
            homeGroup.getActivitys().add(activity);
            return this.homeGroupRepository.save(homeGroup).getActivitys();
        }).orElseThrow(() -> new ResourceNotFoundException("HomeGroup", id));
    }

    //получить все activity для homeGroup
    @GetMapping("homeGroups/{homeGroupId}/activitys")
    public Set<Activity> getActivitys(@PathVariable Long homeGroupId){

        return this.homeGroupRepository.findById(homeGroupId).map((homeGroup) -> {
            return homeGroup.getActivitys();
        }).orElseThrow(() -> new ResourceNotFoundException("HomeGroup", homeGroupId));
    }

    //связь id homeGroup c id HomeGroupDate
    @PostMapping("homeGroups/{id}/homeGroupDates/{homeGroupDateId}")
    public Set<HomeGroupDate> addHomeGroupDate(@PathVariable Long id, @PathVariable Long homeGroupDateId){

        HomeGroupDate homeGroupDate= this.homeGroupDateRepository.findById(homeGroupDateId).orElseThrow(
                () -> new ResourceNotFoundException("HomeGroupDate", homeGroupDateId)
        );


        return this.homeGroupRepository.findById(id).map((homeGroup) -> {
            homeGroup.getHomeGroupDates().add(homeGroupDate);
            return this.homeGroupRepository.save(homeGroup).getHomeGroupDates();
        }).orElseThrow(() -> new ResourceNotFoundException("HomeGroup", id));
    }

    //получить все homeGroupData для homeGroup
    @GetMapping("homeGroups/{homeGroupId}/homeGroupDates")
    public Set<HomeGroupDate> getHomeGroupDates(@PathVariable Long homeGroupId){

        return this.homeGroupRepository.findById(homeGroupId).map((homeGroup) -> {
            return homeGroup.getHomeGroupDates();
        }).orElseThrow(() -> new ResourceNotFoundException("HomeGroup", homeGroupId));
    }

}