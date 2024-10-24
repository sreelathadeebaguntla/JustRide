package com.justride.service;

import java.util.Collections;
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
import com.justride.exception.CarNotFoundException;
import com.justride.exception.CarServiceException;
import com.justride.model.Car;
import com.justride.model.response.CarPostResponse;
import com.justride.model.response.CarResponse;
import com.justride.model.response.CarsResponse;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Component
public class CarServiceImpl implements CarService {

	private static final Logger logger = LogManager.getLogger(CarServiceImpl.class);

	@Autowired
	private AmazonDynamoDB amazonDynamoDB;

	@Override
	@CircuitBreaker(name = "carService", fallbackMethod = "fallbackGetCars")
	public CarsResponse getCars() {
		try {
			logger.info("Fetching all cars");
			DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
			List<Car> cars = dynamoDBMapper.scan(Car.class, new DynamoDBScanExpression());
			CarsResponse response = new CarsResponse();
			response.setCarList(cars);
			return response;
		} catch (Exception e) {
			logger.error("Error fetching all cars", e);
			throw new CarServiceException("Unable to fetch cars");
		}
	}
	
	//Fallback method
	public CarsResponse fallbackGetCars(Throwable t) {
	    logger.error("Fallback method triggered due to: {}", t.getMessage());

	    CarsResponse fallbackResponse = new CarsResponse();
	    fallbackResponse.setCarList(Collections.emptyList()); // Returning an empty list
	    fallbackResponse.setFallbackFlag(true);
	    return fallbackResponse;
	}
	
	@Override
	public CarResponse getCarById(String id) {
		try {
			logger.info("Fetching car with id: " + id);
			DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
			Car car = dynamoDBMapper.load(Car.class, id);
			if (car == null) {
				throw new CarNotFoundException("Car with id " + id + " not found");
			}
			CarResponse response = new CarResponse();
			response.setCar(car);
			return response;
		} catch (CarNotFoundException e) {
			throw e;
		} catch (Exception e) {
			logger.error("Error fetching car by id", e);
			throw new CarServiceException("Unable to fetch car by id");
		}
	}

	@Override
	public CarPostResponse addCar(Car car) {
		try {
			logger.info("Adding new car");
			DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
			dynamoDBMapper.save(car);
			CarPostResponse response = new CarPostResponse();
			response.setCreatedCar(car);
			return response;
		} catch (Exception e) {
			logger.error("Error adding car", e);
			throw new CarServiceException("Unable to add car");
		}
	}

	@Override
	public String updateCar(String id, Car car) {
		try {
			logger.info("Updating car with id: " + id);
			DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
			if (dynamoDBMapper.load(Car.class, id) != null) {
				dynamoDBMapper.save(car, saveExpression(car));
				return "Car Data Successfully updated";

			} else {
				throw new CarNotFoundException("No car found with id: " + id);
			}
		} catch (CarNotFoundException e) {
			throw e;
		} catch (Exception e) {
			logger.error("Error updating car", e);
			throw new CarServiceException("Unable to update car");
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
		try {
			logger.info("Deleting all cars");
			DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
			List<Car> cars = dynamoDBMapper.scan(Car.class, new DynamoDBScanExpression());
			if (!cars.isEmpty()) {
				dynamoDBMapper.batchDelete(cars);
				return "Successfully deleted all cars data";
			} else {
				return "No cars data exists";
			}
		} catch (Exception e) {
			logger.error("Error deleting all cars", e);
			throw new CarServiceException("Unable to delete all cars");
		}
	}
}
