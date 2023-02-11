package com.gayuh.beautycarereservation.service.impl;

import com.gayuh.beautycarereservation.dto.ResultPaginationResponse;
import com.gayuh.beautycarereservation.dto.userAccount.UserAccountResponse;
import com.gayuh.beautycarereservation.repository.UserAccountRepository;
import com.gayuh.beautycarereservation.service.UserAccountService;
import com.gayuh.beautycarereservation.util.PaginationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {

    public final UserAccountRepository userAccountRepository;
    @Override
    public ResultPaginationResponse<UserAccountResponse> findUserAccountList(Integer page, Integer limit, String sortBy, String direction, String userAccountName) {
        Sort sort = Sort.by(new Sort.Order(PaginationUtil.getSortBy(direction), sortBy));
        PageRequest pageable = PageRequest.of(page, limit, sort);
        Page<UserAccountResponse> pageResult = userAccountRepository.listUserAccount(userAccountName, pageable);

        List<UserAccountResponse> responses = pageResult.stream().map(user -> UserAccountResponse.builder()
                .id(user.getId())
                .userAccountName(user.getUserAccountName())
                .age(user.getAge())
                .email(user.getEmail())
                .gender(user.getGender())
                .birthDate(user.getBirthDate())
                .role(user.getRole())
                .build()).collect(Collectors.toList());

        return PaginationUtil.createResultPageResponse(responses, pageResult.getTotalElements(), (long) pageResult.getTotalPages());
    }
}
