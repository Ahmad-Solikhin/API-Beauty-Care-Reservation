package com.gayuh.beautycarereservation.service.impl;

import com.gayuh.beautycarereservation.domain.Gender;
import com.gayuh.beautycarereservation.dto.gender.GenderAddRequest;
import com.gayuh.beautycarereservation.dto.gender.GenderUpdateRequest;
import com.gayuh.beautycarereservation.exception.NotFoundException;
import com.gayuh.beautycarereservation.repository.GenderRepository;
import com.gayuh.beautycarereservation.service.GenderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GenderServiceImpl implements GenderService {

    private final GenderRepository genderRepository;

    @Override
    public Gender findById(Long id) {
        Gender gender = genderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Gender with id: " + id + " not found"));

        return gender;
    }

    @Override
    public void addGender(GenderAddRequest request) {
        Gender gender = new Gender();
        gender.setGender(request.getGenderName());
        genderRepository.save(gender);
    }

    @Override
    public void updateGender(Long id, GenderUpdateRequest request) {
        Gender gender = genderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Gender with id: " + id + " not found"));
        gender.setGender(request.getGenderName());
        genderRepository.save(gender);
    }

    @Override
    public void deleteGender(Long id) {
        Gender gender = genderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Gender with id: " + id + " not found"));
        genderRepository.delete(gender);
    }
}
