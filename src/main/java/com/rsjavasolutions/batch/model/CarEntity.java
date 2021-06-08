package com.rsjavasolutions.batch.model;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "car")
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @Column(unique = true)
    private String uuid = UUID.randomUUID().toString();
    private String brand;
    private String model;
    @Enumerated(EnumType.STRING)
    private BodyType bodyType;
    private Integer year;

    public CarEntity(String brand,
                     String model,
                     BodyType bodyType,
                     Integer year) {
        this.brand = brand;
        this.model = model;
        this.bodyType = bodyType;
        this.year = year;
    }
}

