Feature: Check balance
  Checking the balance of an account holder's account.

  Background:
    Given successful authentication

  Scenario: Account holder checks balance
    Given the account balance is $100
    When the account holder checks the balance
    Then it should show $100