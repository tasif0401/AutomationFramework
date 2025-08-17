package com.mib.android.steps;

import java.util.Properties;

import com.example.base.BaseTest;
import com.example.base.DriverFactory;
import com.mib.android.pages.AccountSummaryPage;
import com.mib.android.pages.TransfersPage;

import io.cucumber.java.en.When;


public class AddNewBeneficiaryWithinADCBSteps extends BaseTest {
	
    private AccountSummaryPage accountSummaryPage;
    private TransfersPage transfersPage;	
    
    @When("user navigates to Add Beneficiary page within ADCB")
    public void user_navigates_to_Add_Beneficiary_page_within_ADCB() throws Exception 
    {
    	// Initialize the AccountSummaryPage and TransfersPage
    	accountSummaryPage = new AccountSummaryPage(mobileDriver);
    	transfersPage = new TransfersPage(mobileDriver);
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
    	
    }
    
   
/*
    @When("user enters beneficiary details with name {string}, account {string}, bank {string}")
    public void user_enters_beneficiary_details_with_name_account_bank(String string, String string2, String string3) {
       
    }

    @Then("beneficiary should be added successfully")
    public void beneficiary_should_be_added_successfully() {
       
    }
*/

}