package com.rsjavasolutions.batch.batch;

import com.rsjavasolutions.batch.model.CarEntity;
import com.rsjavasolutions.batch.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class DBWriter implements ItemWriter<CarEntity> {

    private final CarRepository carRepository;

    @Override
    public void write(List<? extends CarEntity> cars) {
        log.info("Data Saved for Cars {}", cars);
        carRepository.saveAll(cars);
    }
}
