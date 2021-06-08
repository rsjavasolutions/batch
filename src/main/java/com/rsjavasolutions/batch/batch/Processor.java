package com.rsjavasolutions.batch.batch;

import com.rsjavasolutions.batch.model.CarEntity;
import com.rsjavasolutions.batch.model.CarRequest;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import static com.rsjavasolutions.batch.utils.BodyTypeConverter.convertToBodyType;

@Component
public class Processor implements ItemProcessor<CarRequest, CarEntity> {

    @Override
    public CarEntity process(CarRequest carRequest) {
        return new CarEntity(
                carRequest.getBrand().toUpperCase(),
                carRequest.getModel(),
                convertToBodyType(carRequest.getBodyType()),
                carRequest.getYear()
        );
    }
}
