package com.rsjavasolutions.batch.controller;


import com.rsjavasolutions.batch.model.CarRequest;
import com.rsjavasolutions.batch.model.CarResponse;
import com.rsjavasolutions.batch.service.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;
    private final JobLauncher jobLauncher;
    private final Job job;

    @GetMapping("cars/{uuid}")
    public CarResponse getCarByUuid(@PathVariable String uuid) {
        return carService.getCarByUuid(uuid);
    }

    @GetMapping("cars")
    public List<CarResponse> getCars() {
        return carService.getAllCars();
    }

    @GetMapping("cars/load")
    public BatchStatus loadCars() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        Map<String, JobParameter> maps = new HashMap<>();
        maps.put("time", new JobParameter(System.currentTimeMillis()));

        JobParameters parameters = new JobParameters(maps);
        JobExecution jobExecution = jobLauncher.run(job, parameters);

        log.info("JobExcecution: " + jobExecution.getStatus());

        return jobExecution.getStatus();
    }

    @PostMapping("cars")
    public String saveCar(@RequestBody CarRequest request) {
        return carService.saveCar(request);
    }

    @DeleteMapping("cars/{uuid}")
    public void deleteCarByUuid(@PathVariable String uuid) {
        carService.deleteCarByUuid(uuid);
    }

    @PutMapping("cars/{uuid}")
    public CarResponse updateCar(@PathVariable String uuid, @RequestBody CarRequest request) {
        return carService.updateCar(uuid, request);
    }
}
