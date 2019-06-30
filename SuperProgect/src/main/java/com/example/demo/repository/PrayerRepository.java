package com.example.demo.repository;

import com.example.demo.models.Prayer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrayerRepository extends JpaRepository<Prayer, Long> {
}
