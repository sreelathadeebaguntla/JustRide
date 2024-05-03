package com.car.rental.justride.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.car.rental.justride.modal.Car;
import com.car.rental.justride.modal.CarCreatedResponse;
import com.car.rental.justride.modal.CarResponse;
import com.car.rental.justride.modal.CarsResponse;

@Component
public class CarServiceImpl implements CarService {

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
	public CarCreatedResponse addCar(Car car) {
		DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
		dynamoDBMapper.save(car);
		CarCreatedResponse response = new CarCreatedResponse();
		response.setCreatedCar(car);
		return response;

	}

}
