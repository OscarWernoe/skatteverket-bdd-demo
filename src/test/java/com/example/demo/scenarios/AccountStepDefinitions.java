package com.example.demo.scenarios;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SuppressWarnings("SpringJavaAutowiredMembersInspection")
public class AccountStepDefinitions {

    @Autowired
    private MockMvc mockMvc;

    private ResultActions resultActions;

    @Given("successful authentication")
    public void successfulAuthentication() throws Exception {
        mockMvc.perform(post("/authenticate")
                .param("name", "Bob")
                .param("pin", "1111"));
    }

    @Given("the account balance is ${int}")
    public void theAccountBalanceIs$(int amount) {
        // todo deposit money
        throw new PendingException();
    }

    @When("the account holder checks the balance")
    public void theAccountHolderChecksTheBalance() throws Exception {
        resultActions = mockMvc.perform(get("/checkBalance")
                .param("name", "Bob")
                .param("pin", "1111"));
    }

    @Then("it should show ${double}")
    public void itShouldShow$(double amount) throws Exception {
        resultActions.andExpect(content().string(Double.toString(amount)));
    }


}
