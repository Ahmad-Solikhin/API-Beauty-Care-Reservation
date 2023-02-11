package com.gayuh.beautycarereservation.repository;

import com.gayuh.beautycarereservation.domain.UserAccount;
import com.gayuh.beautycarereservation.dto.userAccount.UserAccountResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    public Optional<UserAccount> findByEmail(String email);

    @Query("SELECT DISTINCT new com.gayuh.beautycarereservation.dto.userAccount.UserAccountResponse" +
            "(ua.secureId, ua.name, ua.email, ua.age, ua.birthDate, ua.gender.gender, ua.role) " +
            "FROM UserAccount AS ua " +
            "WHERE LOWER(ua.name) LIKE LOWER(CONCAT('%',:name,'%'))")
    public Page<UserAccountResponse> listUserAccount(String name, Pageable pageable);

}
