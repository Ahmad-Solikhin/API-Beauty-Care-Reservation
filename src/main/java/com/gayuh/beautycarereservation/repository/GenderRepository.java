package com.gayuh.beautycarereservation.repository;

import com.gayuh.beautycarereservation.domain.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenderRepository extends JpaRepository<Gender, Long> {

}
