package com.justride.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.amazonaws.services.kms.model.NotFoundException;
import com.justride.model.Car;
import com.justride.model.response.CarPostResponse;
import com.justride.model.response.CarResponse;
import com.justride.model.response.CarsResponse;

@Component
public class CarServiceImpl implements CarService {

	private static final Logger logger = LogManager.getLogger(CarServiceImpl.class);

	@Autowired
	private AmazonDynamoDB amazonDynamoDB;

	@Override
	public CarsResponse getCars() {
		DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
		List<Car> cars = dynamoDBMapper.scan(Car.class, new DynamoDBScanExpression());
		CarsResponse response = new CarsResponse();
		response.setCarList(cars);
		return response;
	}

	@Override
	public CarResponse getCarById(String id) {
		DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
		Car car = dynamoDBMapper.load(Car.class, id);
		CarResponse response = new CarResponse();
		response.setCar(car);
		return response;
	}

	@Override
	public CarPostResponse addCar(Car car) {
		DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
		dynamoDBMapper.save(car);
		CarPostResponse response = new CarPostResponse();
		response.setCreatedCar(car);
		return response;

	}

	@Override
	public String updateCar(String id, Car car) {
		DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
		if (dynamoDBMapper.load(Car.class, id) != null) {
			dynamoDBMapper.save(car, saveExpression(car));
			return "Car Data Successfully updated";

		} else {
			throw new NotFoundException("No Car found with id: " + id);
		}
	}

	private DynamoDBSaveExpression saveExpression(Car car) {

		DynamoDBSaveExpression saveExpression = new DynamoDBSaveExpression();
		Map<String, ExpectedAttributeValue> expectedMap = new HashMap<>();
		expectedMap.put("id", new ExpectedAttributeValue(new AttributeValue().withS(car.getId())));
		saveExpression.setExpected(expectedMap);

		logger.info("Updated the car info with: {%s}", car.toString());
		System.out.printf("%s", car.toString());
		return saveExpression;
	}

	@Override
	public String deleteCarById(String id) {
		DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
		Car car = dynamoDBMapper.load(Car.class, id);
		if (car != null) {
			dynamoDBMapper.delete(car);
			return "Successfully Deleted Car:" + id;
		}
		return "No Cars Exists with id: " + id;
	}

	@Override
	public String deleteAllCars() {
		DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
		List<Car> cars = dynamoDBMapper.scan(Car.class, new DynamoDBScanExpression());
		if (!cars.isEmpty()) {
			dynamoDBMapper.batchDelete(cars);
			return "Successfully Deleted All Cars Data";
		}
		return "No Cars Data exists";

	}

}
