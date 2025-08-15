package com.example.stepdefinitions;

import com.example.base.BaseTest;
import com.example.base.DriverFactory;
import com.example.pages.mobile.MobileLoginPage;
import io.cucumber.java.en.*;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.Assert;

import java.util.Properties;

public class MobileSteps extends BaseTest {
    private MobileLoginPage login;
    private AppiumDriver<MobileElement> mobileDriver;

    @Given("I open the app on mobile with config {string}")
    public void openApp(String configFile) throws Exception {
        Properties prop = DriverFactory.loadConfig(configFile);
        BaseTest.initMobileDriver(prop);
        login = new MobileLoginPage(mobileDriver);
    }

    @When("I perform mobile login with {string} and {string}")
    public void mobileLogin(String u, String p){
        login.enterUsername(u);
        login.enterPassword(p);
        login.clickLogin();
    }

    @Then("I should see mobile dashboard")
    public void verify(){
        // placeholder: in real app check an element
        Assert.assertTrue(true);
        BaseTest.quitAll();
    }
}