package com.example.demo.controller;

import com.example.demo.exception.ActivityNotFoundException;
import com.example.demo.exception.PrayerNotFoundException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.models.*;
import com.example.demo.repository.ActivityRepository;
import com.example.demo.repository.PrayerDateRepository;
import com.example.demo.repository.PrayerRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
public class PrayerController {

    @Autowired
    PrayerRepository prayerRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PrayerDateRepository prayerDateRepository;

    //создать запись в таблице prayer
    @PostMapping("/prayers")
    public Prayer createPrayer(@Valid @RequestBody Prayer prayer) {
        return prayerRepository.save(prayer);
    }

    //получить все записи из таблицы prayer
    @GetMapping("/prayers")
    public List getAllPrayers() {
        return prayerRepository.findAll();
    }

    //получить запись по id  из тaблицы prayer
    @GetMapping("/prayers/{id}")
    public Prayer getAllPrayerById(@PathVariable(value = "id") Long prayerId) throws PrayerNotFoundException {
        return prayerRepository.findById(prayerId).orElseThrow(() -> new PrayerNotFoundException(prayerId));
    }

    //удалить запись по id из таблицы prayer
    @DeleteMapping("/prayers/{id}")
    public ResponseEntity deletePrayer(@PathVariable(value = "id") Long prayerId) throws PrayerNotFoundException {
        Prayer prayer = prayerRepository.findById(prayerId).orElseThrow(() -> new PrayerNotFoundException(prayerId));

        prayerRepository.delete(prayer);
        return ResponseEntity.ok().build();

    }

    //обновить запись в таблице prayer
    @PutMapping("/prayers/{id}")
    public Prayer updatePrayer(@PathVariable(value = "id") Long prayerId,
                                   @Valid @RequestBody Prayer prayerDetails) throws PrayerNotFoundException {
        Prayer prayer = prayerRepository.findById(prayerId).orElseThrow(()-> new PrayerNotFoundException(prayerId));

        prayer.setLeaderName(prayerDetails.getLeaderName());
        prayer.setTitlePrayer(prayerDetails.getTitlePrayer());


        Prayer updatePrayer = prayerRepository.save(prayer);
        return updatePrayer;
    }

    //связь id prayer c id prayerDate
    @PostMapping("prayers/{id}/prayerDates/{prayerDateId}")
    public Set<PrayerDate> addPrayerDate(@PathVariable Long id, @PathVariable Long prayerDateId){

        PrayerDate prayerDate = this.prayerDateRepository.findById(prayerDateId).orElseThrow(
                () -> new ResourceNotFoundException("PrayerDate", prayerDateId)
        );


        return this.prayerRepository.findById(id).map((prayer) -> {
            prayer.getPrayerDates().add(prayerDate);
            return this.prayerRepository.save(prayer).getPrayerDates();
        }).orElseThrow(() -> new ResourceNotFoundException("Prayer", id));
    }

    //получить все prayerDate для prayer
    @GetMapping("prayers/{prayerId}/prayerDates")
    public Set<PrayerDate> getPrayerDates(@PathVariable Long prayerId){

        return this.prayerRepository.findById(prayerId).map((prayer) -> {
            return prayer.getPrayerDates();
        }).orElseThrow(() -> new ResourceNotFoundException("Prayer", prayerId));
    }

    // получить всех user-oв по prayer_id
    @GetMapping("prayer/{id}/users")
    public Set<User> getUsers(@PathVariable Long id){

        return this.prayerRepository.findById(id).map((prayer) -> {
            return prayer.getUsers();
        }).orElseThrow(() -> new ResourceNotFoundException("Prayer", id));
    }

}
