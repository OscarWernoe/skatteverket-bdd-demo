Feature: IU Processing

  Scenario: Successful IU is stored as Inkommen
    Given IU information filled by the user
    When the user submits the information
    Then the IU is validated
    Given that the validation has no errors
    Then the IU is stored as Inkommen

  Scenario: Unsuccessful IU is logged
    Given IU information filled by the user
    When the user submits the information
    Then the IU is validated
    Given that the validation has errors
    Then the IU is logged with personnr, orgnr, failed controls, today's date

    # simpler formulation of scenarios
  Scenario: IU is accepted
    Given IU information is valid
    When the user submits the information
    Then the IU is stored as Inkommen

  Scenario: IU is not accepted
    Given IU information is invalid
    When the user submits the information
    Then the IU is logged with personnr, orgnr, failed controls, today's date