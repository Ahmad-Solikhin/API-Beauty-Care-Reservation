package com.gayuh.beautycarereservation.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ResultPaginationResponse<T> {
    private List<T> result;

    private Long page;

    private Long elements;
}
