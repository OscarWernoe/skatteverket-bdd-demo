package com.example.demo.scenarios;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SuppressWarnings("SpringJavaAutowiredMembersInspection")
public class AuthenticationStepDefinitions {

    @Autowired
    private MockMvc mockMvc;

    private ResultActions resultActions;

    @When("holder enters name {string} and pin {string}")
    public void holderEntersNameAndPin(String name, String pin) throws Exception {
        resultActions = mockMvc.perform(post("/authenticate")
                .param("name", name)
                .param("pin", pin));
    }

    @Then("holder sees message {string}")
    public void holderSeesMessage(String message) throws Exception {
        resultActions.andExpect(content().string(message));
    }
}
