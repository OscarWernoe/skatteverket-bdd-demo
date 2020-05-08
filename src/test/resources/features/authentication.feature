Feature: Authentication
  Authenticate an account holder with name and pin

  Scenario: Holder submits name and pin successfully
    When holder enters name "Bob" and pin "1111"
    Then holder sees message "Welcome"

  Scenario Outline: Holder submits name and pin unsuccessfully
    When holder enters name <name> and pin <pin>
    Then holder sees message "Authentication failed"

    Examples:
      | name  | pin    |
      | "Bob" | "9999" |
      | "Dob" | "1111" |
      | "Dob" | "9999" |