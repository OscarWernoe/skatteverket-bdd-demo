Feature: Authentication
  Authenticate an account holder with name and pin

  Scenario: Holder submits name and pin successfully
    When holder enters name "Bob" and pin "1234"
    Then holder sees message "Welcome"

  Scenario Outline: Holder submits name and pin unsuccessfully
    When holder enters name <name> and pin <pin>
    Then holder sees message "Not welcome"

    Examples:
      | name  | pin    |
      | "Bob" | "9999" |
      | "Dob" | "1234" |
      | "Dob" | "9999" |