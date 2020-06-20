package com.example.demo;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        plugin = {"pretty", "html:target/Destination"},
        tags = {"@POJO"}
//       tags = {"@beteende,@formatkontroll,@sambandskontroll"}
)
public class RunCucumberTest {
}
