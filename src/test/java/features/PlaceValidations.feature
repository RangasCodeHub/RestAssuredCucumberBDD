Feature: Validate Place APIs

  Scenario Outline: Verify if place is added successfully using AddPlace API
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When user calls "AddPlaceAPI" with "POST" Http request
    Then the API call is successfully with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And Validate if the place is added successfully with "GetPlaceAPI" and verify "<name>"

    Examples:
      | name | language  | address            |
      | ABC  | French-IN | World Trade Circle |
   #   | UTU  | French-IN | Electronic City, Phase1 |