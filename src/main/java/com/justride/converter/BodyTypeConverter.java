package com.justride.converter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.justride.model.BodyType;

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
