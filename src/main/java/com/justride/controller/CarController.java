package com.justride.controller;

import java.net.URI;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.justride.exception.CarNotFoundException;
import com.justride.exception.CarServiceException;
import com.justride.model.Car;
import com.justride.model.response.CarPostResponse;
import com.justride.model.response.CarResponse;
import com.justride.model.response.CarsResponse;
import com.justride.service.CarService;

@RestController
@RequestMapping("/justride")
public class CarController {

	private static final Logger logger = LogManager.getLogger(CarController.class);

	@Autowired
	private CarService carService;

	@GetMapping("/cars")
	public ResponseEntity<List<Car>> getAllCars() {
		try {
			CarsResponse cars = carService.getCars();
			if (!cars.isFallbackFlag()) {
				logger.info("Fetched all cars: " + cars);
				return new ResponseEntity<>(cars.getCarList(), HttpStatus.OK);
			}else {
				logger.error("Unable to fetch Cars, Fallback Method Executed");
				return new ResponseEntity<>(cars.getCarList(), HttpStatus.SERVICE_UNAVAILABLE);
			}
			
		} catch (Exception e) {
			logger.error("Error fetching cars", e);
			throw new CarServiceException("Unable to fetch cars");
		}
	}

	@GetMapping("/cars/{id}")
	public ResponseEntity<CarResponse> getCarById(@PathVariable String id) {
		try {
			CarResponse car = carService.getCarById(id);
			if (car == null) {
				throw new CarNotFoundException("Car with id " + id + " not found");
			}
			return ResponseEntity.ok(car);
		} catch (CarNotFoundException e) {
			throw e;
		} catch (Exception e) {
			logger.error("Error fetching car by id", e);
			throw new CarServiceException("Unable to fetch car by id");
		}
	}

	@PostMapping("/cars")
	public ResponseEntity<CarPostResponse> addCar(@RequestBody Car car) {
		try {
			CarPostResponse carReponse = carService.addCar(car);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(carReponse.getCreatedCar().getId()).toUri();
			return ResponseEntity.created(location).build();
		} catch (Exception e) {
			logger.error("Error adding car", e);
			throw new CarServiceException("Unable to add car");
		}
	}

	@PutMapping("/cars/{id}")
	public ResponseEntity<String> updateCar(@PathVariable String id, @RequestBody Car car) {
		try {
			return ResponseEntity.ok(carService.updateCar(id, car));
		} catch (Exception e) {
			logger.error("Error updating car", e);
			throw new CarServiceException("Unable to update car");
		}
	}

	@DeleteMapping("/cars/{id}")
	public ResponseEntity<String> deleteCar(@PathVariable String id) {
		try {
			return ResponseEntity.ok(carService.deleteCarById(id));
		} catch (Exception e) {
			logger.error("Error deleting car", e);
			throw new CarServiceException("Unable to delete car");
		}
	}

	@DeleteMapping("/cars")
	public ResponseEntity<String> deleteAllCars() {
		try {
			return ResponseEntity.ok(carService.deleteAllCars());
		} catch (Exception e) {
			logger.error("Error deleting all cars", e);
			throw new CarServiceException("Unable to delete all cars");
		}
	}
}
