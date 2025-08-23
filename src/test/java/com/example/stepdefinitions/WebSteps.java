package com.example.stepdefinitions;

import com.example.base.BaseTest;
import com.example.base.DriverFactory;
import com.example.pages.web.LoginPage;
import com.example.pages.web.DashboardPage;
import com.example.util.AllureReporting;
import io.cucumber.java.en.*;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.Allure;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class WebSteps extends BaseTest {
    private LoginPage login;
    private DashboardPage dash;
    private List<String> stepResults = new ArrayList<>();

    @Description("Opens the browser and navigates to the web application using the provided config file.")
    @Step("Open the browser for web with config: {config}")
    @Given("I open the browser for web {string}")
    public void i_open_the_browser_for_web(String config ) throws Exception {
        Properties prop = DriverFactory.loadConfig(config);
        BaseTest.initWebDriver(prop);
        login = new LoginPage(webDriver);
        login.open(prop.getProperty("baseUrl"));
        AllureReporting.logPass("Browser opened and navigated to baseUrl");
        AllureReporting.attachScreenshot(webDriver, "Navigation Screenshot");
        AllureReporting.setTestCaseName(config);
        stepResults.add("Opened browser and navigated to baseUrl");
    }

    @Description("Logs in to the web application with the given username and password.")
    @Step("Login with username: {username} and password: {password}")
    @When("I login with username {string} and password {string}")
    public void I_login_with_username_and_password(String username, String password){
        login.enterUsername(username);
        login.enterPassword(password);
        login.clickLogin();
        AllureReporting.logPass("Login performed with username: " + username);
        AllureReporting.attachScreenshot(webDriver, "Login Screenshot");
        stepResults.add("Login performed with username: " + username);
    }

}