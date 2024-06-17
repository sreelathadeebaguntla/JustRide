package com.car.rental.justride.steps;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.justride.JustrideApplication;

import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = JustrideApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class SpringIntegrationTest {
}
