package com.example.demo.repository;

import com.example.demo.models.PrayerDate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrayerDateRepository extends JpaRepository<PrayerDate, Long> {

}
