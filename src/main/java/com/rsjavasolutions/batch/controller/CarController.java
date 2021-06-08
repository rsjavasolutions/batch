package com.rsjavasolutions.batch.controller;


import com.rsjavasolutions.batch.model.CarRequest;
import com.rsjavasolutions.batch.model.CarResponse;
import com.rsjavasolutions.batch.service.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping("{uuid}")
    public CarResponse getCarByUuid(@PathVariable String uuid) {
        return carService.getCarByUuid(uuid);
    }

    @GetMapping
    public List<CarResponse> getCars() {
        return carService.getAllCars();
    }

    @GetMapping("load")
    public BatchStatus loadCars() {
        return carService.loadCars();
    }

    @PostMapping
    public String saveCar(@RequestBody CarRequest request) {
        return carService.saveCar(request);
    }

    @DeleteMapping("{uuid}")
    public void deleteCarByUuid(@PathVariable String uuid) {
        carService.deleteCarByUuid(uuid);
    }

    @PutMapping("{uuid}")
    public CarResponse updateCar(@PathVariable String uuid, @RequestBody CarRequest request) {
        return carService.updateCar(uuid, request);
    }
}
