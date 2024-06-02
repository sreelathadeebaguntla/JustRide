Feature: Get all Cars
  Scenario: Get all cars successfully
    When I send a GET request to "/justride/cars"
    Then the response status should be 200
    And the response should contain a list of cars