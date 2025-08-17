package com.example.testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
		features = {
			    "src/test/resources/features/webLogin.feature",			   
			  },
    glue = {
        "com.example.stepdefinitions",
   
    },
	monochrome = true,
    plugin = {
        "pretty",
        "html:target/cucumber-report.html",
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
    }
)
public class CucumberTestNGRunner extends AbstractTestNGCucumberTests {
  
}