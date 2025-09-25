Feature: Validate Place APIs
  Scenario: Verify if place is added successfully using AddPlace API
    Given Add Place Payload
    When user calls "AddPlaceAPI" with POST Http request
    Then the API call is successfully with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"