Feature: Cars
	Scenario: Create new car
    When create a new car using POST request "/justride/cars"
    Then the response status is 201
    And new car is created successfully
  Scenario: Get all cars successfully
    When I send a GET request to "/justride/cars"
    Then the response status should be 200
    And the response should contain a list of cars
  
   
