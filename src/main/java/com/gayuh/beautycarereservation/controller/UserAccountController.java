package com.gayuh.beautycarereservation.controller;

import com.gayuh.beautycarereservation.dto.ResultPaginationResponse;
import com.gayuh.beautycarereservation.dto.userAccount.UserAccountResponse;
import com.gayuh.beautycarereservation.service.UserAccountService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "bearerAuth")
public class UserAccountController {

    private final UserAccountService userAccountService;

    @GetMapping("/v1/user/list")
    public ResponseEntity<ResultPaginationResponse<UserAccountResponse>> findUserAccountList(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "limit", defaultValue = "5") Integer limit,
            @RequestParam(name = "direction", defaultValue = "asc") String direction,
            @RequestParam(name = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(name = "userAccountName", required = false, defaultValue = "") String userAccountName
    ){
        ResultPaginationResponse<UserAccountResponse> response = userAccountService.findUserAccountList(page, limit, sortBy, direction, userAccountName);
        return ResponseEntity.ok().body(response);
    }
}
