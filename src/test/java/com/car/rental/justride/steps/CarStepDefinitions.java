package com.car.rental.justride.steps;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.justride.model.BodyType;
import com.justride.model.Car;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CarStepDefinitions extends SpringIntegrationTest {

	@LocalServerPort
	String port;

	ResponseEntity<String> response;

	@When("^create a new car using POST request {String}$")
	public void createPostRequestToJustrideCars(String url) {
		getRequestBodyForCreatingCar();
		new RestTemplate().postForEntity(url, getRequestBodyForCreatingCar(), Car.class);
	}

	private Car getRequestBodyForCreatingCar() {
		return new Car("Grand Cherokee", "Jeep", BodyType.SUV, getRandomVINNumber(), "Red", getCarDetails(),
				getCarFeatures());
	}

	private List<String> getCarFeatures() {
		// TODO Auto-generated method stub
		return null;
	}

	private String getRandomVINNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	private List<String> getCarDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@When("^I send a GET request to {string}$")
	public void iSendAGetRequestToJustrideCars(String url) {
		response = new RestTemplate().exchange("http://localhost:" + port + url, HttpMethod.GET, null, String.class);
	}

	@Then("^the response status should be {int}$")
	public void theResponseStatusShouldBe(int expected) {
		Assertions.assertNotNull(response);
		Assertions.assertNotNull(response.getStatusCode());
		assertTrue(response.getStatusCode().is2xxSuccessful());
	}

	/*
	 * @And("^the response should contain a list of cars$") public void
	 * validateListOfCars() { assertFalse(response.getBody().getCars().isEmpty()); }
	 */

}
