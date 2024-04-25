package com.car.rental.justride.repository;

import java.util.Optional;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.car.rental.justride.car.Car;

@EnableScan
public interface CarRepository extends CrudRepository<Car, String> {

	Optional<Car> findById(Integer id);

}
