@BDDSTORY-SDC-5
Feature: servera kaffe
  Som en kaffedrickare
  Kan jag få kaffe från kaffemaskinen
  Så att jag kan fokusera bättre

  Background:
    Given att kaffemaskinen är på
    And att det finns vatten och kaffebönor

  @BDDTEST-SDC-7
  Scenario: Det är möjligt att ta 10 koppar efter att "Fyll på vatten" visas

  @BDDTEST-SDC-6
  Scenario: Meddelande "Fyll på vatten" visas efter 50 koppar

    Given att 50 koppar har serverats
    When jag beställer en kopp
    Then visas "Fyll på vatten" på displayen

