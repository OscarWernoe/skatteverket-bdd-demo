package com.example.demo.scenarios;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AccountStepDefinitions {

    @Given("successful authorization")
    public void successfulAuthorization() {
    }

    @Given("the account balance is ${int}")
    public void theAccountBalanceIs$(int amount) {
    }

    @When("the account holder checks the balance")
    public void theAccountHolderChecksTheBalance() {
    }

    @Then("it should show ${int}")
    public void itShouldShow$(int amount) {
    }
}
