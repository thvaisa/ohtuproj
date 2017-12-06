package com.lukuvinkkikirjasto;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(format = "pretty", features = "src/test/java/resources/ohtu")
public class RunCukesTest{

}