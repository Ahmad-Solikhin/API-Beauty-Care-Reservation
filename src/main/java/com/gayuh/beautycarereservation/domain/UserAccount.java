package com.gayuh.beautycarereservation.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "user_account", indexes ={
        @Index(name = "uk_username", columnList = "username"),
        @Index(name = "uk_email", columnList = "email")
})
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserAccount extends AbstractDefaultEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
    @SequenceGenerator(name = "user_generator", sequenceName = "user_id_seq")
    private Long id;
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "age", nullable = false)
    private Integer age;
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @OneToOne
    @JoinColumn(name = "id_gender", nullable = false)
    private Gender gender;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userAccount")
    private List<Reservation> reservations;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "userAccount")
    private UserImage image;
}
