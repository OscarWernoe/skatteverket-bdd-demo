Feature: Withdraw cash

  Background:
    Given successful authentication

  Scenario Outline: Account holder withdraws cash
    Given the account balance is $<initialAmount>
    When the account holder <name> withdraws $<withdrawAmount>
    Then it should show $<remainingAmount>
    Examples:
      | initialAmount | name  | withdrawAmount | remainingAmount |
      | 100           | "Bob" | 50             | 50              |