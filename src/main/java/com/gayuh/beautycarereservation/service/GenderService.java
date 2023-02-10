package com.gayuh.beautycarereservation.service;

import com.gayuh.beautycarereservation.dto.gender.GenderAddRequest;
import com.gayuh.beautycarereservation.dto.gender.GenderResponse;

public interface GenderService {

    public GenderResponse findById(Long id);

    public void addGender(GenderAddRequest request);

}
