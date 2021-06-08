package com.rsjavasolutions.batch.service;

import com.rsjavasolutions.batch.exception.CarNotFoundException;
import com.rsjavasolutions.batch.mapper.CarMapper;
import com.rsjavasolutions.batch.model.CarEntity;
import com.rsjavasolutions.batch.model.CarRequest;
import com.rsjavasolutions.batch.model.CarResponse;
import com.rsjavasolutions.batch.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.rsjavasolutions.batch.mapper.CarMapper.mapToEntity;
import static com.rsjavasolutions.batch.mapper.CarMapper.mapToResponse;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    @Transactional
    public List<CarResponse> getAllCars() {
        return carRepository.findAll()
                .stream()
                .map(CarMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public CarResponse getCarByUuid(String uuid) {
        CarEntity carEntity = carRepository.findByUuid(uuid).orElseThrow(() -> new CarNotFoundException(uuid));
        return mapToResponse(carEntity);
    }

    @Transactional
    public String saveCar(CarRequest request) {
        log.debug("Save car request with params: {}", request);

        return carRepository.save(mapToEntity(request)).getUuid();
    }

    @Transactional
    public CarResponse updateCar(String uuid, CarRequest request) {
        CarEntity carEntity = carRepository.findByUuid(uuid).orElseThrow(() -> new CarNotFoundException(uuid));

        carEntity.setBrand(request.getBrand());
        carEntity.setModel(request.getModel());

        return mapToResponse(carEntity);
    }

    @Transactional
    public void deleteCarByUuid(String uuid) {
        carRepository.deleteByUuid(uuid);
    }
}
