Feature: Withdraw cash

  Background: successful authentication
    When holder enters name "Bob" and pin "1111"
    Then holder sees message "Welcome"

  Scenario Outline: Account holder withdraws cash
    Given the account balance is $<initialAmount>
    When the account holder <name> withdraws $<withdrawAmount>
    Then it should show $<remainingAmount>

    Examples:
      | initialAmount | name  | withdrawAmount | remainingAmount |
      | 100           | "Bob" | 50             | 50              |
      | 50            | "Bob" | 50             | 0               |

  Scenario Outline: Account holder withdraws too much cash
    Given the account balance is $<initialAmount>
    When the account holder <name> withdraws $<withdrawAmount>
    Then it should show <message>
    And the account balance is $<initialAmount>

    Examples:
      | initialAmount | name  | withdrawAmount | message              |
      | 100           | "Bob" | 200            | "Insufficient Funds" |
      | 100           | "Bob" | 100.5          | "Insufficient Funds" |
