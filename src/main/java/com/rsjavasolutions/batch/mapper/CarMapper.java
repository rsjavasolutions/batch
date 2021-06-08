package com.rsjavasolutions.batch.mapper;


import com.rsjavasolutions.batch.model.CarEntity;
import com.rsjavasolutions.batch.model.CarRequest;
import com.rsjavasolutions.batch.model.CarResponse;

import static com.rsjavasolutions.batch.utils.BodyTypeConverter.convertToBodyType;
import static com.rsjavasolutions.batch.utils.BodyTypeConverter.convertToBodyTypeCode;

public class CarMapper {

    public static CarEntity mapToEntity(CarRequest request) {
        return new CarEntity(
                request.getBrand(),
                request.getModel(),
                convertToBodyType(request.getBodyType()),
                request.getYear()
        );
    }

    public static CarResponse mapToResponse(CarEntity carEntity) {
        return new CarResponse(
                carEntity.getUuid(),
                carEntity.getId(),
                carEntity.getBrand(),
                carEntity.getModel(),
                convertToBodyTypeCode(carEntity.getBodyType()),
                carEntity.getYear()
        );
    }
}
