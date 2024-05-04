package com.justride.model.response;

import java.util.List;

import com.justride.model.Car;

public class CarsResponse {

	private List<Car> carList;

	public CarsResponse() {

	}

	public List<Car> getCarList() {
		return carList;
	}

	public void setCarList(List<Car> carList) {
		this.carList = carList;
	}

}