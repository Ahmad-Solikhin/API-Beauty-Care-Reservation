package com.gayuh.beautycarereservation.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@Table(name = "user_account", indexes ={
        @Index(name = "uk_email", columnList = "email", unique = true)
})
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserAccount extends AbstractDefaultEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
    @SequenceGenerator(name = "user_generator", sequenceName = "user_id_seq")
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "email", nullable = false)
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
    @Enumerated(EnumType.STRING)
    private Role role;

    /**
     * Pengaturan di user details unutk entity yang digunakan untuk mendapatkan token
     * GrantedAuthority : Mengembalikan list dari role
     * */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername(){
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
