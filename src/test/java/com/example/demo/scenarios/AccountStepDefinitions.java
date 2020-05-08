package com.example.demo.scenarios;

import com.example.demo.domain.Account;
import com.example.demo.repository.AccountRepository;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SuppressWarnings("SpringJavaAutowiredMembersInspection")
public class AccountStepDefinitions {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountRepository accountRepository;

    private ResultActions resultActions;

    @Before
    public void resetBalance() {
        ArrayList<Account> accounts = (ArrayList<Account>) accountRepository.findAll();
        for (Account a : accounts) {
            a.setBalance(0);
            accountRepository.save(a);
        }
    }

    @Given("successful authentication")
    public void successfulAuthentication() throws Exception {
        mockMvc.perform(post("/authenticate")
                .param("name", "Bob")
                .param("pin", "1111"));
    }

    @Given("the account balance is ${double}")
    public void theAccountBalanceIs$(double amount) throws Exception {
        mockMvc.perform(post("/deposit")
                .param("name", "Bob")
                .param("pin", "1111")
                .param("amount", Double.toString(amount)));
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

    @When("the account holder deposit ${double}")
    public void theAccountHolderDeposit$(double amount) throws Exception {
        resultActions = mockMvc.perform(post("/deposit")
                .param("name", "Bob")
                .param("pin", "1111")
                .param("amount", Double.toString(amount)));
    }

    @When("the account holder {string} withdraws ${double}")
    public void theAccountHolderNameWithdraws$WithdrawAmount(String name, double amount) throws Exception {
        resultActions = mockMvc.perform(post("/withdraw")
                .param("name", name)
                .param("pin", "1111")
                .param("amount", Double.toString(amount)));
    }
}
