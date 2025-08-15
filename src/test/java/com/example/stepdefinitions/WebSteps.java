
package com.example.stepdefinitions;

import com.example.base.BaseTest;
import com.example.base.DriverFactory;
import com.example.pages.web.LoginPage;
import com.example.pages.web.DashboardPage;
import io.cucumber.java.en.*;
import org.testng.Assert;

import java.util.Properties;

public class WebSteps extends BaseTest {
    private LoginPage login;
    private DashboardPage dash;

    @Given("I open the browser for web {string}")
    public void I_open_the_browser_for_web(String config ) throws Exception {
        Properties prop = DriverFactory.loadConfig(config);
        BaseTest.initWebDriver(prop);
        login = new LoginPage(webDriver);
        login.open(prop.getProperty("baseUrl"));
    }

    @When("I login with username {string} and password {string}")
    public void I_login_with_username_and_password(String username, String password){
        login.enterUsername(username);
        login.enterPassword(password);
       // login.clickLogin();
       // dash = new DashboardPage(webDriver);
    }


}
