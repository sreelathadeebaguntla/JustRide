package com.justride.service;

import org.springframework.stereotype.Service;

import com.justride.model.Car;
import com.justride.model.response.CarPostResponse;
import com.justride.model.response.CarResponse;
import com.justride.model.response.CarsResponse;

@Service
public interface CarService {
	
	public CarsResponse getCars();

	public CarResponse getCarById(String id);

	public CarPostResponse addCar(Car car);

}
