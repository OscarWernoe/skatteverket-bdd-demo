package com.example.demo.scenarios;

import io.cucumber.java.PendingException;
import io.cucumber.java.sv.Givet;
import io.cucumber.java.sv.När;

public class ExempelStepDefinitions {
    @Givet("det finns {int} gurkor")
    public void detFinnsGurkor(int arg0) {
        throw new PendingException();
    }

    @När("jag äter {int}")
    public void jagÄter(int arg0) {
        System.out.println("eating1");
    }

//    @När("jag äter {int}")
//    public void ajagÄter(int arg0) {
//        System.out.println("eating2");
//    }
}
