Feature: Retrieve version
  Retrieving the server version

  Scenario: Client makes call to /version
    When the client calls "/version"
    Then the client receives status code of 200
    And the client receives server version "1.0"