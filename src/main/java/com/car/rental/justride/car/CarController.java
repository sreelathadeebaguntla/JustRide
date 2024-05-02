package com.car.rental.justride.car;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.car.rental.justride.modal.Car;
import com.car.rental.justride.modal.CarCreatedResponse;
import com.car.rental.justride.modal.CarResponse;
import com.car.rental.justride.modal.CarsResponse;
import com.car.rental.justride.service.CarService;

@RestController
@RequestMapping("/justride")
public class CarController {

	@Autowired
	private CarService carService;

	@GetMapping("/cars")
	public ResponseEntity<CarsResponse> getAllCars() {
		return ResponseEntity.ok(carService.getCars());
	}

	@GetMapping("/cars/{id}")
	public ResponseEntity<CarResponse> getCarById(@PathVariable Integer id) {
		return ResponseEntity.ok(carService.getCarById(id.intValue()));
	}

	@PostMapping("/cars")
	public ResponseEntity<CarCreatedResponse> addCar(@RequestBody Car car) {
		CarCreatedResponse carReponse = carService.addCar(car);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(carReponse.getCreatedCar().getId()).toUri();
		return ResponseEntity.created(location).build();
	}
}
