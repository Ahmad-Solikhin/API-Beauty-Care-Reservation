package com.gayuh.beautycarereservation.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Table(name = "reservation")
@Data
@Entity
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Reservation extends AbstractDefaultEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "date", nullable = false)
    private LocalDateTime date;
    @Column(name = "total_price")
    private BigInteger totalPrice;
    @ManyToMany
    @JoinTable(name = "reservation_treatment", joinColumns = {
            @JoinColumn(name = "id_reservation", referencedColumnName = "id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "id_tratment", referencedColumnName = "id")
    })
    private List<Treatment> treatments;
    @ManyToOne
    @JoinColumn(name = "id_user_account", nullable = false)
    private UserAccount userAccount;
}
