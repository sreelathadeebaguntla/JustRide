package com.justride.controller;

import java.net.URI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	public ResponseEntity<CarsResponse> getAllCars() {
		CarsResponse cars = carService.getCars();
		logger.info("This is info" + cars);
		return ResponseEntity.ok(cars);
	}

	@GetMapping("/cars/{id}")
	public ResponseEntity<CarResponse> getCarById(@PathVariable String id) {
		return ResponseEntity.ok(carService.getCarById(id));
	}

	@PostMapping("/cars")
	public ResponseEntity<CarPostResponse> addCar(@RequestBody Car car) {
		CarPostResponse carReponse = carService.addCar(car);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(carReponse.getCreatedCar().getId()).toUri();
		return ResponseEntity.created(location).build();
	}
}
