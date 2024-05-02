package com.car.rental.justride.service;

import com.car.rental.justride.modal.Car;
import com.car.rental.justride.modal.CarCreatedResponse;
import com.car.rental.justride.modal.CarResponse;
import com.car.rental.justride.modal.CarsResponse;

public interface CarService {
	
	public CarsResponse getCars();

	public CarResponse getCarById(int id);

	public CarCreatedResponse addCar(Car car);

}
