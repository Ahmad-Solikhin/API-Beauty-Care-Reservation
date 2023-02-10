package com.gayuh.beautycarereservation.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "treatment")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Treatment extends AbstractDefaultEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "treatment_generator")
    @SequenceGenerator(name = "treatment_generator", sequenceName = "treatment_id_seq")
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    @Column(name = "price", nullable = false)
    private BigInteger price;
    @Column(name = "date_add")
    private LocalDateTime dateAdd;
    @OneToMany(mappedBy = "treatment", fetch = FetchType.LAZY)
    private List<TreatmentImage> treatmentImages;

}
