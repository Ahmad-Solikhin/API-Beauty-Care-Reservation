package com.gayuh.beautycarereservation.service;

import com.gayuh.beautycarereservation.domain.Gender;
import com.gayuh.beautycarereservation.dto.gender.GenderAddRequest;
import com.gayuh.beautycarereservation.dto.gender.GenderUpdateRequest;

public interface GenderService {

    public Gender findById(Long id);

    public void addGender(GenderAddRequest request);

    public void updateGender(Long id, GenderUpdateRequest request);

    public void deleteGender(Long id);

}
