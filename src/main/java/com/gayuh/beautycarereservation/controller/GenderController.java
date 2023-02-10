package com.gayuh.beautycarereservation.controller;

import com.gayuh.beautycarereservation.dto.gender.GenderAddRequest;
import com.gayuh.beautycarereservation.dto.gender.GenderResponse;
import com.gayuh.beautycarereservation.dto.gender.GenderUpdateRequest;
import com.gayuh.beautycarereservation.service.GenderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@AllArgsConstructor
@Validated
@RestController
@RequestMapping("/api")
public class GenderController {

    private final GenderService genderService;
    @GetMapping("/v1/gender/{genderId}")
    public ResponseEntity<GenderResponse> finGenderById(@PathVariable(name = "genderId") Long genderId){
        GenderResponse response = genderService.findById(genderId);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/v1/gender")
    public ResponseEntity<Void> addGender(@RequestBody @Valid GenderAddRequest request){
        genderService.addGender(request);
        return ResponseEntity.created(URI.create("/api/v1/gender")).build();
    }

    @PutMapping("/v1/gender/{genderId}")
    public ResponseEntity<Void> updateGender(@PathVariable(name = "genderId") Long genderId, @RequestBody GenderUpdateRequest request){
        genderService.updateGender(genderId, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/v1/gender/{genderId}")
    public ResponseEntity<Void> deleteGender(@PathVariable(name = "genderId") Long genderId){
        genderService.deleteGender(genderId);
        return ResponseEntity.ok().build();
    }
}
