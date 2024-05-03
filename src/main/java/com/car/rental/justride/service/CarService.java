package com.car.rental.justride.service;

import org.springframework.stereotype.Service;

import com.car.rental.justride.modal.Car;
import com.car.rental.justride.modal.CarCreatedResponse;
import com.car.rental.justride.modal.CarResponse;
import com.car.rental.justride.modal.CarsResponse;

@Service
public interface CarService {
	
	public CarsResponse getCars();

	public CarResponse getCarById(String id);

	public CarCreatedResponse addCar(Car car);

}
