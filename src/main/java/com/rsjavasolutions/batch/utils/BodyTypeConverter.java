package com.rsjavasolutions.batch.utils;

import com.rsjavasolutions.batch.model.BodyType;

import static com.rsjavasolutions.batch.model.BodyType.*;
import static com.rsjavasolutions.batch.model.BodyType.HATCHBACK;

public class BodyTypeConverter {

    private static final String HATCHBACK_BODY_TYPE_CODE = "HB";
    private static final String COMBI_BODY_TYPE_CODE = "CB";
    private static final String COUPE_BODY_TYPE_CODE = "CP";
    private static final String SEDAN_BODY_TYPE_CODE = "SD";
    private static final String UNKNOWN_BODY_TYPE_CODE = "UK";

    public static BodyType convertToBodyType(String bodyTypeCode) {
        switch (bodyTypeCode.toUpperCase()) {
            case HATCHBACK_BODY_TYPE_CODE:
                return HATCHBACK;
            case COMBI_BODY_TYPE_CODE:
                return COMBI;
            case COUPE_BODY_TYPE_CODE:
                return COUPE;
            case SEDAN_BODY_TYPE_CODE:
                return SEDAN;
            default:
                return UNKNOWN;
        }
    }

    public static String convertToBodyTypeCode(BodyType bodyType) {
        switch (bodyType) {
            case HATCHBACK:
                return HATCHBACK_BODY_TYPE_CODE;
            case COMBI:
                return COMBI_BODY_TYPE_CODE;
            case COUPE:
                return COUPE_BODY_TYPE_CODE;
            case SEDAN:
                return SEDAN_BODY_TYPE_CODE;
            default:
                return UNKNOWN_BODY_TYPE_CODE;
        }
    }
}
