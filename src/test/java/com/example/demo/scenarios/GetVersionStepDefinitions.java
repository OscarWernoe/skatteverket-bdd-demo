package com.example.demo.scenarios;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SuppressWarnings("SpringJavaAutowiredMembersInspection")
public class GetVersionStepDefinitions {

    @Autowired
    private MockMvc mockMvc;

    private ResultActions resultActions;

    @When("the client calls {string}")
    public void theClientCallsVersion(String page) throws Exception {
        resultActions = mockMvc.perform(get(page));
    }

    @Then("the client receives status code of {int}")
    public void theClientReceivesStatusCodeOf(int statusCode) throws Exception {
        resultActions.andExpect(status().is(statusCode));
    }

    @And("the client receives server version {string}")
    public void theClientReceivesServerVersion(String version) throws Exception {
        resultActions.andExpect(content().string(version));
    }

}
