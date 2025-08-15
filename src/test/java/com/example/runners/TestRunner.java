
package com.example.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
  features = "src/test/resources/features/webLogin.feature",
  glue = {"com.example.stepdefinitions"},
  plugin = {"pretty","html:target/cucumber-report.html"}
)
public class TestRunner extends AbstractTestNGCucumberTests { }
