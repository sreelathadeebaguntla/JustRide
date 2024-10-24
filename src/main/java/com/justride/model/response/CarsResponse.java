package com.justride.model.response;

import java.util.List;

import com.justride.model.Car;

public class CarsResponse {

	private List<Car> carList;
	
	private boolean fallbackFlag = false;

	public CarsResponse() {

	}

	public List<Car> getCarList() {
		return carList;
	}

	public void setCarList(List<Car> carList) {
		this.carList = carList;
	}

	public boolean isFallbackFlag() {
		return fallbackFlag;
	}

	public void setFallbackFlag(boolean fallbackFlag) {
		this.fallbackFlag = fallbackFlag;
	}
	
	

}