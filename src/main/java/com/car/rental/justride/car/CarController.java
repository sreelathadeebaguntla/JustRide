package com.car.rental.justride.car;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;

@RestController
@RequestMapping("/justride")
public class CarController {

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @GetMapping("/cars")
    public List<Car> getAllCars() {
        DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
        List<Car> cars = dynamoDBMapper.scan(Car.class, new DynamoDBScanExpression());
        return cars;
    }
    
    @GetMapping("/cars/{id}")
    public Car getCarById(@PathVariable Integer id) {
        DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
        Car car = dynamoDBMapper.load(Car.class, id);
        return car;
    }
    
    @PostMapping("/cars")
    public Car addCar(@RequestBody Car car) {
        DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
        dynamoDBMapper.save(car);
        return car;
    }
}
