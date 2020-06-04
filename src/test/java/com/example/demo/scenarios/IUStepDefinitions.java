package com.example.demo.scenarios;

import com.example.demo.domain.IU;
import com.example.demo.domain.Status;
import com.example.demo.repository.IURepository;
import io.cucumber.core.gherkin.vintage.internal.gherkin.deps.com.google.gson.Gson;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class IUStepDefinitions {

    @SuppressWarnings("SpringJavaAutowiredMembersInspection")
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IURepository iuRepository;

    private ResultActions resultActions;
    private IU iu;
    private final Gson gson = new Gson();


    @Given("IU information filled by the user")
    public void iuInformationFilledByTheUser() throws Exception {
        iu = new IU(); //receive as argument from feature file?
        iu.setId(1L);
    }

    @When("the user submits the information")
    public void theUserSubmitsTheInformation() throws Exception {
        resultActions = mockMvc.perform(post("/iu").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(iu)));
    }

    @Then("the IU is validated")
    public void theIUIsValidated() {

    }

    @Given("that the validation has no errors")
    public void thatTheValidationHasNoErrors() throws Exception {
        resultActions.andExpect(status().isOk());
    }

    @Then("the IU is stored as Inkommen")
    public void theIUIsStoredAsInkommen() {
        Optional<IU> storedIU = iuRepository.findById(iu.getId());
        assertEquals(storedIU.get().getStatus(), Status.INKOMMEN);
    }

    @Given("that the validation has errors")
    public void thatTheValidationHasErrors() {
    }

    @Then("the IU is logged with personnr, orgnr, failed controls, today's date")
    public void theIUIsLoggedWithPersonnrOrgnrFailedControlsTodaySDate() {

    }

    @Given("IU information is valid")
    public void iuInformationIsValid() {
        // make IU with valid data
    }

    @Given("IU information is invalid")
    public void iuInformationIsInvalid() {
        // make IU with invalid data
    }
}
