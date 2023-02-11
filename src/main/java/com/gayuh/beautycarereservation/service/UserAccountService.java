package com.gayuh.beautycarereservation.service;

import com.gayuh.beautycarereservation.dto.ResultPaginationResponse;
import com.gayuh.beautycarereservation.dto.userAccount.UserAccountResponse;

public interface UserAccountService {

    public ResultPaginationResponse<UserAccountResponse> findUserAccountList(
            Integer page, Integer limit, String sortBy, String direction, String name);

}
