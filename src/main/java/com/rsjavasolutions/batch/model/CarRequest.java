package com.rsjavasolutions.batch.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CarRequest {
    private String brand;
    private String model;
    private String bodyType;
    private Integer year;
}
