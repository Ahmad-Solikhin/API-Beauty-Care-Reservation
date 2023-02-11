package com.gayuh.beautycarereservation.dto.userAccount;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.gayuh.beautycarereservation.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserAccountResponse {
    private String id;
    private String userAccountName;
    private String email;
    private Integer age;
    private LocalDate birthDate;
    private String gender;
    private Role role;
}
