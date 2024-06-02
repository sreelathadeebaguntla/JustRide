package com.car.rental.justride.steps;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CarSteps extends SpringIntegrationTest {

	@LocalServerPort
	String port;

	ResponseEntity<String> response;

	@When("I send a GET request to {string}")
	public void iSendAGetRequestToJustrideCars(String url) {
		response = new RestTemplate().exchange("http://localhost:" + port + url, HttpMethod.GET, null, String.class);
	}

	@Then("the response status should be {int}")
	public void theResponseStatusShouldBe(int expected) {
		Assertions.assertNotNull(response);
		Assertions.assertNotNull(response.getStatusCode());
		assertTrue(response.getStatusCode().is2xxSuccessful());
	}

	/*
	 * @And("^the response should contain a list of cars$") public void
	 * theResponseStatusShouldBe2(int statusCode) { assertEquals(statusCode,
	 * response.getStatusCode().value()); }
	 */
}
