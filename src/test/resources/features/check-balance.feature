Feature: Check balance
  Checking the balance of an account holder's account.

  Background: successful authentication
    When holder enters name "Bob" and pin "1111"
    Then holder sees message "Welcome"

  Scenario: Account holder checks balance
    Given the account balance is $100
    When the account holder checks the balance
    Then it should show $100