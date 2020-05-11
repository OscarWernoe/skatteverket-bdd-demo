Feature: Deposit cash

  Background: successful authentication
    When holder enters name "Bob" and pin "1111"
    Then holder sees message "Welcome"

  Scenario: Account holder deposit cash
    Given the account balance is $0
    When the account holder deposit $100
    Then it should show $100