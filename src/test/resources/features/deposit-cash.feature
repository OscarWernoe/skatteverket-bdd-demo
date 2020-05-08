Feature: Deposit cash

  Background:
    Given successful authentication

  Scenario: Account holder deposit cash
    Given the account balance is $0
    When the account holder deposit $100
    Then it should show $100