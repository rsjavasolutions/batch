package com.rsjavasolutions.batch.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CarResponse {
    private String uuid;
    private long id;
    private String band;
    private String model;
    private String bodyType;
    private Integer year;
}
