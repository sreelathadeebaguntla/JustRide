package com.justride.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.justride.model.Car;

//@EnableScan
public interface CarRepository extends CrudRepository<Car, String> {

	Optional<Car> findById(Integer id);

}
