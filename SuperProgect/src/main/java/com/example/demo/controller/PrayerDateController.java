package com.example.demo.controller;

import com.example.demo.exception.HomeGroupDateNotFoundException;
import com.example.demo.exception.PrayerDateNotFoundException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.models.*;
import com.example.demo.repository.HomeGroupDateRepository;
import com.example.demo.repository.PrayerDateRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
public class PrayerDateController {

    @Autowired
    PrayerDateRepository prayerDateRepository;

    @Autowired
    UserRepository userRepository;



    //создать запись в таблице prayer_date
    @PostMapping("/prayerDates")
    public PrayerDate createPrayerDate(@Valid @RequestBody PrayerDate prayerDate) {
        return prayerDateRepository.save(prayerDate);
    }

    //получить все записи из таблицы prayer_date
    @GetMapping("/prayerDates")
    public List getAllPrayerDate() {
        return prayerDateRepository.findAll();
    }

    //получить запись по id  из тaблицы prayer_date
    @GetMapping("/prayerDates/{id}")
    public PrayerDate getAllPrayerDateById(@PathVariable(value = "id") Long prayerDateId) throws PrayerDateNotFoundException {
        return prayerDateRepository.findById(prayerDateId).orElseThrow(() -> new PrayerDateNotFoundException(prayerDateId));
    }

    //удалить запись по id из таблицы prayer_date
    @DeleteMapping("/prayerDates/{id}")
    public ResponseEntity deletePrayerDate(@PathVariable(value = "id") Long prayerDateId) throws PrayerDateNotFoundException {
        PrayerDate prayerDate = prayerDateRepository.findById(prayerDateId).orElseThrow(() -> new PrayerDateNotFoundException(prayerDateId));

        prayerDateRepository.delete(prayerDate);
        return ResponseEntity.ok().build();

    }

    //обновить запись в таблице prayer_date
    @PutMapping("/prayerDates/{id}")
    public PrayerDate updatePrayerDate(@PathVariable(value = "id") Long prayerDateId,
                                             @Valid @RequestBody PrayerDate prayerDateDetails) throws PrayerDateNotFoundException {
        PrayerDate prayerDate = prayerDateRepository.findById(prayerDateId).orElseThrow(()-> new PrayerDateNotFoundException(prayerDateId));

        prayerDate.setDate(prayerDateDetails.getDate());
        prayerDate.setTime(prayerDateDetails.getTime());

        PrayerDate updatePrayerDate = prayerDateRepository.save(prayerDate);
        return updatePrayerDate;
    }

    // получить всех users по prayerDate_id
    @GetMapping("prayerDates/{id}/users")
    public Set<User> getUsers(@PathVariable Long id){

        return this.prayerDateRepository.findById(id).map((prayerDate) -> {
            return prayerDate.getUsers();
        }).orElseThrow(() -> new ResourceNotFoundException("PrayerDate", id));
    }

    // получить все prayer по prayerDate_id
    @GetMapping("prayerDates/{id}/prayers")
    public Set<Prayer> getPrayers(@PathVariable Long id){

        return this.prayerDateRepository.findById(id).map((prayerDate) -> {
            return prayerDate.getPrayers();
        }).orElseThrow(() -> new ResourceNotFoundException("PrayerDate", id));
    }
}
