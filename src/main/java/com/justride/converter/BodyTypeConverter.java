package com.justride.converter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.justride.model.BodyTypeEnum;

public class BodyTypeConverter implements DynamoDBTypeConverter<String, BodyTypeEnum> {

    @Override
    public String convert(BodyTypeEnum object) {
        return object.toString();
    }

    @Override
    public BodyTypeEnum unconvert(String object) {
        return BodyTypeEnum.valueOf(object);
    }
}
