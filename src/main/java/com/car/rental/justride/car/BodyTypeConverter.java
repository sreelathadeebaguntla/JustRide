package com.car.rental.justride.car;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

public class BodyTypeConverter implements DynamoDBTypeConverter<String, BodyType> {

    @Override
    public String convert(BodyType object) {
        return object.toString();
    }

    @Override
    public BodyType unconvert(String object) {
        return BodyType.valueOf(object);
    }
}
