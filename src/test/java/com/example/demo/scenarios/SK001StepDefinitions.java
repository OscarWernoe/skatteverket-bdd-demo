package com.example.demo.scenarios;

import com.example.demo.controller.IUController;
import com.example.demo.domain.IU;
import com.example.demo.domain.SK001IU;
import com.example.demo.domain.kontroller.FormalieController;
import com.example.demo.repository.IURepository;
import com.example.demo.service.IUService;
import io.cucumber.core.gherkin.vintage.internal.gherkin.deps.com.google.gson.Gson;
import io.cucumber.java.Before;
import io.cucumber.java.ParameterType;
import io.cucumber.java.sv.Givet;
import io.cucumber.java.sv.När;
import io.cucumber.java.sv.Så;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static com.example.demo.domain.Status.INKOMMEN;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SK001StepDefinitions {

    private SK001IU iu;
    private IUService iuService;
    private IUController iuController;

    @Autowired
    private IURepository iuRepository;

    @Autowired
    private MockMvc mockMvc;

    FormalieController passingFormalieController = new FormalieController() {
        @Override
        public Object formatkontroll(SK001IU iu) {
            return null;
        }

        @Override
        public Object kontrolleraSamband(SK001IU iu) {
            return null;
        }

        @Override
        public Object kontrolleraObligatoriskaFalt(SK001IU iu) {
            return null;
        }

        @Override
        public Map<String, String> logg() {
            HashMap<String, String> logg = new HashMap<>();
//            logg.put("idnr", iu.getInkomsttagare());
            logg.put("idnr", "199910101234");
            logg.put("kontroller", "k1, k2, k3");
            logg.put("datum", "2020-01-01");
            return logg;
        }
    };

    @Before
    public void nullifyIu() {
        this.iu = null;
    }

    @Givet("en IU med gilltig information")
    public void enIUMedGilltigInformation() {
        iu = new SK001IU();
        // set fields
    }

    @När("IU läses in")
    public void iuLäsesIn() throws Exception {
        // läs in IU
        Gson gson = new Gson();
        String jsonIu = gson.toJson(iu);

        mockMvc.perform(post("/SK001")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonIu))
                .andExpect(status().isOk());
    }

    @Så("utförs formaliekontroll")
    public void utförsFormaliekontroll() {
        passingFormalieController.formatkontroll(iu);
        passingFormalieController.kontrolleraObligatoriskaFalt(iu);
        passingFormalieController.kontrolleraSamband(iu);
    }


    @Så("sparas IU som inkommen")
    public void sparasIUSomInkommen() {
        IU iuSaved = iuRepository.findById(1L).get();
        assertEquals(INKOMMEN, iuSaved.getStatus());
    }

    @Givet("en IU med ogilltig information")
    public void enIUMedOgilltigInformation() {
        iu = new SK001IU();
        // set fields
    }

    @Så("loggas IU med orgnr, misslyckade kontroller, dagens datum")
    public void loggasIUMedOrgnrMisslyckadeKontrollerDagensDatum() {
        HashMap<String, String> resultat = (HashMap<String, String>) passingFormalieController.logg();
        assertNotNull(resultat.get("idnr"));
        assertNotNull(resultat.get("kontroller"));
        assertNotNull(resultat.get("datum"));
    }

    @ParameterType("IuLine|IuPhrase")
    public SK001IU IUObj(String id) {
        SK001IU s = new SK001IU();
        s.setAktuellFomIu(id);
        return s;
    }

    @Givet("en {IUObj} med gilltig information")
    public void enIUObj(SK001IU s) {
        System.out.println(s.getAktuellFomIu());
    }
}
