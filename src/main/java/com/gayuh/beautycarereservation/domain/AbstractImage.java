package com.gayuh.beautycarereservation.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Index;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;

@Data
@MappedSuperclass
@Table(indexes = @Index(name = "name_index", columnList = "name"))
public abstract class AbstractImage implements Serializable {
    @Column(name = "original_name", nullable = false)
    private String originalName;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "type", nullable = false)
    private String type;
    @Column(name = "size", nullable = false)
    private Long size;
}
