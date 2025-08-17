package com.example.stepdefinitions;

import com.example.base.BaseTest;
import com.example.base.DriverFactory;
import com.example.pages.mobile.MobileLoginPage;
import com.mib.android.pages.AccountSummaryPage;
import com.mib.android.pages.TransfersPage;

import io.cucumber.java.en.*;
import org.testng.Assert;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import io.qameta.allure.Step;
import io.qameta.allure.Description;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MobileSteps extends BaseTest 
{
    
	 private MobileLoginPage login ;
	 private AccountSummaryPage accountSummaryPage;
	 private TransfersPage transfersPage;	

    @Description("Opens the mobile app using the provided configuration file.")
    @Step("Open the app on mobile with config: {configFile}")
    @Given("I open the app on mobile with config {string}")
    public void I_open_the_app_on_mobile_with_config(String configFile) throws Exception {
        Properties prop = DriverFactory.loadConfig(configFile);
        BaseTest.initMobileDriver(prop);
        login = new MobileLoginPage(mobileDriver);
        accountSummaryPage = new AccountSummaryPage(mobileDriver);
    	transfersPage = new TransfersPage(mobileDriver);
		
    }

    @Description("Performs mobile login using the given password.")
    @Step("Perform mobile login with password: {p}")
    @When("I perform mobile login with pass {string}")
    public void I_perform_mobile_login_with_pass(String p){
        login.clickLogin();
        login.enterPassword(p);
        login.clickLogin();
    }

    @Description("Verifies that the Account Summary dashboard is displayed on mobile.")
    @Step("Verify mobile Account Summary dashboard is displayed")
    @Then("I should see mobile Account Summary dashboard")
    public void i_should_see_mobile_account_summary_dashboard() {
	String gettext = accountSummaryPage.getAccountSummaryTitle();
	Assert.assertEquals(gettext, "ACCOUNT SUMMARY", "Account Summary title does not match");
   }
   
    @Description("Navigates to the Add Beneficiary page within ADCB for the specified account number.")
    @Step("Navigate to Add Beneficiary page within ADCB for account: {accountnumber}")
    @When("user navigates to Add Beneficiary page within ADCB {string}")
    public void user_navigates_to_Add_Beneficiary_page_within_ADCB(String accountnumber) throws Exception 
    {
    	// Initialize the AccountSummaryPage and TransfersPage
    	String gettext = accountSummaryPage.getAccountSummaryTitle();
    	if (!gettext.equals("ACCOUNT SUMMARY")) {
    		throw new RuntimeException("Account Summary title does not match");
    	}else {
			System.out.println("Account Summary title matches: " + gettext);
		}
    	accountSummaryPage.clickTransfersTab();
    	transfersPage.clickNewBeneficiaryTab();
    	transfersPage.clickWithinAdcbTab();
    	transfersPage.clickAlertYesButton();
    	
    	transfersPage.enterAccountNumber(String.valueOf(accountnumber));
    	transfersPage.clickFetchAccountTitleButton();
    	transfersPage.clickContinueButton();
    	transfersPage.enterNickname("Asif");
    	transfersPage.enterAddressLine1("AUH");
    	transfersPage.clickContinueButton();
    	String test = transfersPage.getReviewText();
    	if (!test.equals("Review")) {
			throw new RuntimeException("Review text does not match");
    	}
    	else {
			System.out.println("Review text matches: " + test);
    	}
    	transfersPage.clickShowMoreButton();
    	String accountnumbertxt = transfersPage.getAccountNumberText();
    	if (!accountnumbertxt.equals(accountnumber)) {
    					throw new RuntimeException("Account number text does not match");
    	}else {
			System.out.println("Review text matches: " + test);

    	}
    }

  
}