package com.example.stepdefinitions;

import com.example.base.BaseTest;
import com.example.base.DriverFactory;
import com.example.pages.mobile.MobileLoginPage;
import com.example.util.ExcelUtils;
import com.example.util.TestUtils;
import com.mib.android.pages.AccountSummaryPage;
import com.mib.android.pages.TransfersPage;

import io.cucumber.java.en.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import io.qameta.allure.Step;
import io.qameta.allure.Description;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MobileSteps extends BaseTest 
{
     private ExcelUtils excelUtils;
	 private MobileLoginPage login ;
	 private AccountSummaryPage accountSummaryPage;
	 private TransfersPage transfersPage;	

    @Description("Opens the mobile app using the provided configuration file.")
    @Step("Open the app on mobile with config: {configFile}")
    @Given("I open the app on mobile with {string}")
    public void I_open_the_app_on_mobile_with(String configFile) throws Exception {
        Properties prop = DriverFactory.loadConfig(configFile);
        BaseTest.initMobileDriver(prop);
        login = new MobileLoginPage(mobileDriver);
        excelUtils = new ExcelUtils();
        accountSummaryPage = new AccountSummaryPage(mobileDriver);
        transfersPage = new TransfersPage(mobileDriver);
        attachScreenshot("Open App");
    }

    @Description("Performs mobile login using the given password.")
    @Step("Perform mobile login with password: {p}")
    @When("I perform mobile login with {string}")
    public void I_perform_mobile_login_with(String p){
        login.ClickEnglishlanguagebtn();
        login.clickLogin();
        attachScreenshot("Clicked Login");
        login.enterPassword(ExcelUtils.readExcelColumnNameAsString("Password"));
        attachScreenshot("Entered Password");
        login.clickLogin();
        attachScreenshot("Clicked Login Again");
    }

    @Description("Verifies that the Account Summary dashboard is displayed on mobile.")
    @Step("Verify mobile Account Summary dashboard is displayed")
    @Then("I should see mobile Account Summary dashboard")
    public void i_should_see_mobile_account_summary_dashboard() {
        String gettext = accountSummaryPage.getAccountSummaryTitle();
        attachScreenshot("Account Summary Dashboard");
        try {
            Assert.assertEquals(gettext, "ACCOUNT SUMMARY", "Account Summary title does not match");
        } catch (AssertionError e) {
            throw e;
        }
    }
   
    @Description("Navigates to the Add Beneficiary page within ADCB for the specified account number.")
    @Step("Navigate to Add Beneficiary page within ADCB for account: ")
    @When("user navigates to Add Beneficiary page within ADCB")
    public void user_navigates_to_add_beneficiary_page_within_adcb() throws InterruptedException {
    	String gettext = accountSummaryPage.getAccountSummaryTitle();
        attachScreenshot("Account Summary Title");
        if (!gettext.equals("ACCOUNT SUMMARY")) {
            throw new RuntimeException("Account Summary title does not match");
        } else {
            System.out.println("Account Summary title matches: " + gettext);
        }
        accountSummaryPage.clickTransfersTab();
        attachScreenshot("Clicked Transfers Tab");
        transfersPage.clickNewBeneficiaryTab();
        attachScreenshot("Clicked New Beneficiary Tab");
        transfersPage.clickWithinAdcbTab();
        attachScreenshot("Clicked Within ADCB Tab");
        transfersPage.clickAlertYesButton();
        attachScreenshot("Clicked Alert Yes Button");
        transfersPage.enterAccountNumber(ExcelUtils.readExcelColumnNameAsString("AccountNumber"));
        attachScreenshot("Entered Account Number");
        transfersPage.clickFetchAccountTitleButton();
        attachScreenshot("Clicked Fetch Account Title Button");
        transfersPage.clickContinueButton();
        attachScreenshot("Clicked Continue Button");
        String BeneficiaryName = TestUtils.randomAlphaString(4);
        ExcelUtils.writeRuntimeValueToDataStorage("MobileSteps", "BeneficiaryName", BeneficiaryName);
        transfersPage.enterNickname(BeneficiaryName);
        attachScreenshot("Entered Nickname");
        transfersPage.enterAddressLine1("AUH");
        attachScreenshot("Entered Address Line 1");
        transfersPage.clickContinueButton();
        attachScreenshot("Clicked Continue Button Again");
        String test = transfersPage.getReviewText();
        attachScreenshot("Review Text");
        if (!test.equals("Review")) {
            throw new RuntimeException("Review text does not match");
        } else {
            System.out.println("Review text matches: " + test);
        }
        transfersPage.clickShowMoreButton();
        attachScreenshot("Clicked Show More Button");
        String accountnumbertxt = transfersPage.getAccountNumberText();
        attachScreenshot("Account Number Text"+accountnumbertxt);
        System.out.println("Account Number Text: " + accountnumbertxt);

        transfersPage.clickConfirmButton();
        attachScreenshot("Clicked Confirm Button");
        String successText = transfersPage.getSuccessText();
        attachScreenshot("Success Text");
        if(!successText.equals("Success!")) {
            throw new RuntimeException("Success text does not match");
        } else {
            System.out.println("Succes text matches: " + test);
        }
        String accountnumbertxt1 = transfersPage.getAccountNumberText();
        attachScreenshot("Account Number Text After Success");
        
    }
    
	@Description("User navigates to Existing Beneficiary page and searches for the added beneficiary name using runtime value from DataStorage.")
    @Step("Navigate to Existing Beneficiary page and search for beneficiary: {beneficiaryName}")
	@When("User naviagtes to Existing Beneficiary page")
	public void User_naviagtes_to_Existing_Beneficiary_page() throws InterruptedException {
		
		String beneficiaryName = ExcelUtils.readRuntimeValueFromDataStorage("MobileSteps", "BeneficiaryName");
		transfersPage.clickAddanotherButton();
		attachScreenshot("Clicked Add Another Button");
		transfersPage.clickExistingBeneficiaryTab();
		attachScreenshot("Clicked Existing Beneficiary Tab");
		transfersPage.clickExistingBeneficiarySearchIcon();
		attachScreenshot("Clicked Existing Beneficiary Search Icon");
		transfersPage.enterSearchBeneficiaryField(beneficiaryName);		
		attachScreenshot("Entered Search Beneficiary Field");
	}

    @Description("Validate that the added beneficiary name is displayed using runtime value from DataStorage.")
    @Step("Validate added beneficiary name: {beneficiaryName}")
    @And("user should validate the Added Beneficiary Name")
	public void user_should_validate_the_Added_Beneficiary_Name() throws InterruptedException {
		String beneficiaryName = ExcelUtils.readRuntimeValueFromDataStorage("MobileSteps", "BeneficiaryName");
		if(transfersPage.isPendingBeneficiariesTabDisplayed())
		{
			System.out.println("Pending Beneficiaries Tab is displayed");
			transfersPage.clickPendingBeneficiaries();
			attachScreenshot("Clicked Pending Beneficiaries Tab");
			Assert.assertTrue(transfersPage.isSearchBeneficiaryFieldDisplayed(beneficiaryName), "Beneficiary name is not displayed in Pending Beneficiaries");
			attachScreenshot("Searched for Beneficiary in Pending Beneficiaries");
		} else {
			System.out.println("Active Beneficiaries Tab is displayed");
			//transfersPage.clickActiveBeneficiaries();
			attachScreenshot("Clicked Active Beneficiaries Tab");
			Assert.assertTrue(transfersPage.isSearchBeneficiaryFieldDisplayed(beneficiaryName), "Beneficiary name is not displayed in Active Beneficiaries");
			attachScreenshot("Searched for Beneficiary in Active Beneficiaries");
		}
		
		attachScreenshot("Validated Added Beneficiary Name");
	}
    
    @Description("User selects the existing beneficiary name from the search results using runtime value from DataStorage.")
    @When("user Searches for Existing Beneficiary with Name {string}")
    public void user_searches_for_existing_beneficiary_with_name(String beneficiaryName) throws InterruptedException {
       
        Object result = ExcelUtils.readExcelColumnName("BeneficiaryName");
        String beneficiaryName1 = "";
        if (result instanceof java.util.List && !((java.util.List<?>) result).isEmpty()) {
            beneficiaryName1 = String.valueOf(((java.util.List<?>) result).get(0));
        }
        System.out.println("Searching for Existing Beneficiary with Name: " + beneficiaryName1);
        transfersPage.clickExistingBeneficiarySearchIcon();
        attachScreenshot("Clicked Existing Beneficiary Search Icon");
        transfersPage.enterSearchBeneficiaryField(beneficiaryName1);
        attachScreenshot("Entered Search Beneficiary Field");
       if (transfersPage.isActiveBeneficiariesTabDisplayed()) {
 			System.out.println("Active Beneficiaries Tab is displayed");
 			attachScreenshot("Clicked Active Beneficiaries Tab");
 			Assert.assertTrue(transfersPage.isSearchBeneficiaryFieldDisplayed(beneficiaryName1), "Beneficiary name is not displayed in Active Beneficiaries");
 			attachScreenshot("Searched for Beneficiary in Active Beneficiaries");
 		}
    }
	
    
    @Description("User selects the existing beneficiary name from the search results.")
    @And("user selects Existing Beneficiary with Name")
    public void user_selects_existing_beneficiary_with_name() throws InterruptedException {
        Object result = ExcelUtils.readExcelColumnName("BeneficiaryName");
        String beneficiaryName1 = "";
        if (result instanceof java.util.List && !((java.util.List<?>) result).isEmpty()) {
            beneficiaryName1 = String.valueOf(((java.util.List<?>) result).get(0));
        }
       transfersPage.SelectSearchedBeneficiaryName(beneficiaryName1);
       attachScreenshot("Selected Searched Beneficiary Name");
    }
	
    @Description("User navigates to the Transfer Funds page.")
	@And("user Select the source Account with Positive balance")
	public void user_Select_the_source_Account_with_Positive_balance() throws Exception {
    	Properties prop = DriverFactory.loadConfig("SoruceAccount.properties");
    	String sourceAccount = prop.getProperty("sourceaccount");

    	if(transfersPage.isSelectSourceAccountTextDisplayed()) {
    		Assert.assertTrue(transfersPage.isSelectSourceAccountTextDisplayed(), "Source Account Page is not displayed");
        	transfersPage.clickSelectSourceAccountText(sourceAccount);
    		attachScreenshot("Selected Source Account with Positive Balance");
    	}else {
    			Assert.fail("Source Account Page is not displayed");
    			attachScreenshot("Source Account Page Not Displayed");
    	}    	
    	
	}
    
	
	@Then("user Should enter the Transfer Deatils")
	public void user_should_enter_the_transfer_deatils() throws InterruptedException {
	   if(transfersPage.isTransferDetailsTextDisplayed()) {
		   transfersPage.enterTransferAmount(ExcelUtils.readExcelColumnNameAsString("Amount"));
		   attachScreenshot("Entered Transfer Amount");
		   Thread.sleep(2000); // Wait for 2 seconds to ensure the amount is entered
		   String AvailableBalance = transfersPage.getAvailableBalanceText();
		   attachScreenshot("Available Balance Text: " + AvailableBalance);
		   ExcelUtils.writeRuntimeValueToDataStorage("MobileSteps", "AvailableBalance", AvailableBalance);
		   transfersPage.SelectpurposeOfTransfer(ExcelUtils.readExcelColumnNameAsString("PurposeofTransfer"));
		   attachScreenshot("Selected Purpose of Transfer");
		   Thread.sleep(2000); // Wait for 2 seconds to ensure the purpose is selected
		 /*String ToTransferAccountNumber = transfersPage.ToTransferAccountNumberText();
		   attachScreenshot("To Transfer Account Number Text: " + ToTransferAccountNumber);
		   ExcelUtils.writeRuntimeValueToDataStorage("MobileSteps", "ToTransferAccountNumber", ToTransferAccountNumber);
		   String FromTransferAccountNumber = transfersPage.FromTransferAccountNumberText();
		   attachScreenshot("From Transfer Account Number Text: " + FromTransferAccountNumber);
		   ExcelUtils.writeRuntimeValueToDataStorage("MobileSteps", "FromTransferAccountNumber", FromTransferAccountNumber);
		  */
		   transfersPage.clickContinueButton();
		   attachScreenshot("Clicked Continue Button on Transfer Details Page");
	   } else {
		   Assert.fail("Transfer Details Page is not displayed");
		   attachScreenshot("Transfer Details Page Not Displayed");
	   }
	}

	@Description("User navigates to the Fund Transfer Review page.")
	@Then("user should navigate to Fund Transfer Review page")
	public void user_should_navigate_to_fund_transfer_review_page() {
	    if(transfersPage.getReviewText().equals("Review")) {
	       attachScreenshot("Review Page is displayed");
	    } else {
	        Assert.fail("Review Page is not displayed");
	        attachScreenshot("Review Page Not Displayed");
	    }
	}
	
	@Description("User validates the Fund Transfer Review page details.")
	@Then("user should validate the Fund Transfer Review page")
	public void user_should_validate_the_fund_transfer_review_page() 
	{
		 	transfersPage.clickShowMoreButton();
	        attachScreenshot("Clicked Show More Button on Review Page");
	        String TransferAmountTextReviewPage = transfersPage.getTransferAmountText();
	        attachScreenshot("Transfer Amount Text on Review Page: " + TransferAmountTextReviewPage);
	        ExcelUtils.writeRuntimeValueToDataStorage("MobileSteps", "TransferAmountTextReviewPage",TransferAmountTextReviewPage);
	        String FromAccountNumberReviewPage = transfersPage.getFromAccountNumberText();
	        attachScreenshot("From Account Number Text on Review Page: " + FromAccountNumberReviewPage);
	        ExcelUtils.writeRuntimeValueToDataStorage("MobileSteps", "FromAccountNumberReviewPage",FromAccountNumberReviewPage);
	        String BalBeforeReviewPage = transfersPage.getBalBeforeText();
	        attachScreenshot("Balance Before Review Page: " + BalBeforeReviewPage);
	        ExcelUtils.writeRuntimeValueToDataStorage("MobileSteps", "BalBeforeReviewPage",BalBeforeReviewPage);
	        String BalAfterReviewPage = transfersPage.getBalAfterText();
	        attachScreenshot("Balance After Review Page: " + BalAfterReviewPage);
	        ExcelUtils.writeRuntimeValueToDataStorage("MobileSteps", "BalAfterReviewPage",BalAfterReviewPage);
	        if(ExcelUtils.readRuntimeValueFromDataStorage("MobileSteps", "AvailableBalance").equals(BalAfterReviewPage)) {
	            System.out.println("Available Balance matches with Balance After Review Page");
	            attachScreenshot("Available Balance Matches with Balance After Review Page");
	            transfersPage.clickconfirmTransferButton();	
	        } else {
	            Assert.fail("Available Balance does not match with Balance After Review Page");
	            attachScreenshot("Available Balance Mismatch");
	        }
	        
	}
	@Description("User navigates to the Fund Transfer Success page.")
	@Then("user should navigate to Fund Transfer Success page")
	public void user_should_navigate_to_fund_transfer_success_page() {
	   if(transfersPage.isUseOtpVerificationButtonDisplayed())
	   {
		   transfersPage.clickUseOtpVerificationButton();
	       attachScreenshot("Clicked Use OTP Verification Button");
	       transfersPage.enterSmsOtp();
	       attachScreenshot("Entered SMS OTP");
	       if(transfersPage.getSuccessText().equals("Success!")) {
	           System.out.println("Fund Transfer Success Page is displayed");
	           attachScreenshot("Fund Transfer Success Page");
	       } else {
	           Assert.fail("Fund Transfer Success Page is not displayed");
	           attachScreenshot("Fund Transfer Success Page Not Displayed");
	       }
	       
	   } else {
	       Assert.fail("Use OTP Verification Button is not displayed");
	       attachScreenshot("Use OTP Verification Button Not Displayed");
	   }
	}
	@Description("User validates the Fund Transfer Success page details.")
	@Then("user should validate the Fund Transfer Success page")
	public void user_should_validate_the_fund_transfer_success_page() {
		transfersPage.clickShowMoreButton();
		attachScreenshot("Clicked Show More Button on Success Page");
		String TransferAmountTextSuccessPage = transfersPage.getTransferAmountText();
		attachScreenshot("Transfer Amount Text on Success Page: " + TransferAmountTextSuccessPage);
		String BalBeforeTextSuccessPage = transfersPage.getBalBeforeText();
		attachScreenshot("Balance Before Text on Success Page: " + BalBeforeTextSuccessPage);
		String BalAfterTextSuccessPage = transfersPage.getBalAfterText();
		attachScreenshot("Balance After Text on Success Page: " + BalAfterTextSuccessPage);
		ExcelUtils.writeRuntimeValueToDataStorage("MobileSteps", "BalAfterTextSuccessPage",BalAfterTextSuccessPage);
		if(TransferAmountTextSuccessPage.equals(ExcelUtils.readRuntimeValueFromDataStorage("MobileSteps", "TransferAmountTextReviewPage")) &&
		   BalBeforeTextSuccessPage.equals(ExcelUtils.readRuntimeValueFromDataStorage("MobileSteps", "BalBeforeReviewPage")) &&
		   BalAfterTextSuccessPage.equals(ExcelUtils.readRuntimeValueFromDataStorage("MobileSteps", "BalAfterReviewPage"))) {
		   System.out.println("Transfer Amount matches with Review Page");
			attachScreenshot("Transfer Amount Matches with Review Page");
			transfersPage.clickAccountSummaryButton();
		} else {
			Assert.fail("Transfer Amount does not match with Review Page");
			attachScreenshot("Transfer Amount Mismatch");
		}	
		}
	
	@Description("User navigates to the Account Summary Dashboard.")
	@When("user navigates to Account Summary Dashboard")
	public void user_navigates_to_account_summary_dashboard() throws Exception {
		Properties prop = DriverFactory.loadConfig("SoruceAccount.properties");
    	String sourceAccount = prop.getProperty("sourceaccount");
    	String gettext = accountSummaryPage.getAccountSummaryTitle();
        attachScreenshot("Account Summary Title");
        if (!gettext.equals("ACCOUNT SUMMARY")) {
            throw new RuntimeException("Account Summary title does not match");
        } else {
            System.out.println("Account Summary title matches: " + gettext);
        }
		TestUtils.scrollToElementPresent(mobileDriver, sourceAccount);
		TestUtils.clickElementByText(mobileDriver, sourceAccount);
		attachScreenshot("Clicked on Source Account to validate balance");
		
	}

	@Then("user should validate the Amount is Deducted or not in source account")
	public void user_should_validate_the_amount_is_deducted_or_not_in_source_account() throws Exception {
		Properties prop = DriverFactory.loadConfig("SoruceAccount.properties");
    	String sourceAccount = prop.getProperty("sourceaccount");
    	Thread.sleep(8000);
		if(TestUtils.isElementPresent(mobileDriver, sourceAccount)) {
			String NetavailableBalance = transfersPage.getNetAvailableBalanceForWithdrawal();
			System.out.println("Net Available Balance after Transfer: " + NetavailableBalance);
			attachScreenshot("Net Available Balance after Transfer: " + NetavailableBalance);
			if(ExcelUtils.readRuntimeValueFromDataStorage("MobileSteps", "BalAfterTextSuccessPage").equals(NetavailableBalance)) {
				System.out.println("Amount is Deducted from Source Account");
				attachScreenshot("Amount is Deducted from Source Account");
		}	else {
				Assert.fail("Amount is not Deducted from Source Account");
				attachScreenshot("Amount is not Deducted from Source Account");
			}
		} else {
			Assert.fail("Source Account is not displayed");
			attachScreenshot("Source Account is not displayed");
		}
	}
	
	
	@Before
    public void setUp() {
        if (transfersPage == null && mobileDriver != null && accountSummaryPage == null) {
        				accountSummaryPage = new AccountSummaryPage(mobileDriver);
            transfersPage = new TransfersPage(mobileDriver);
        }
    }
	@AfterSuite
	public void sendEmailReport() {
		BaseTest.sendAllureReportEmail();
	}
	public void attachScreenshot(String stepName) {
        if (mobileDriver != null) {
            byte[] screenshot = ((TakesScreenshot) mobileDriver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(stepName + " Screenshot", new java.io.ByteArrayInputStream(screenshot));
        }
    }
	
}
