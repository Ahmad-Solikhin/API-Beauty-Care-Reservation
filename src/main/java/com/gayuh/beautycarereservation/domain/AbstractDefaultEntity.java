package com.gayuh.beautycarereservation.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Index;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
@MappedSuperclass
@Table(indexes = {
        @Index(name = "uk_secure_id"
                , columnList = "secure_id")
})
public abstract class AbstractDefaultEntity implements Serializable {
    @Column(name = "secure_id", nullable = false, unique = true)
    private String secureId = UUID.randomUUID().toString();
    @Column(columnDefinition = "boolean default false")
    private Boolean deleted = false;

}
