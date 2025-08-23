package com.mib.android.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.util.TestUtils;
import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.appium.java_client.TouchAction;
import java.time.Duration;

public class TransfersPage {
	private AppiumDriver<MobileElement> driver;
    private WebDriverWait wait;
	
    
    
    // Define the elements using @FindBy annotations
    @FindBy(xpath = "//android.widget.TextView[@text=\"Existing Beneficiary\"]")
    private WebElement existingBeneficiaryTab;
    	
    @FindBy(xpath = "//android.widget.TextView[@text=\"New Beneficiary\"]")
    private WebElement newBeneficiaryTab;
    
    @FindBy(xpath = "//android.widget.TextView[@text=\"Within ADCB\"]")
    private WebElement withinAdcbTab;
    
    @FindBy(xpath = "//android.widget.TextView[@text=\"Yes\"]")
    private WebElement alretYesButton;

    @FindBy(xpath = "//android.widget.TextView[@text=\"Proceed\"]")
    private WebElement proceedbutton;

    
    @FindBy(xpath = "//android.widget.EditText")
    private WebElement accountNumberField;
    
    @FindBy(xpath=" //android.widget.TextView[@text=\"Fetch Account Title..\"]")
    private WebElement fetchAccountTitleButton;
    
    @FindBy(xpath = "//android.widget.TextView[@text=\"Continue\"]")
    private WebElement continueButton;
    
    @FindBy(xpath = "//android.widget.EditText[@text=\"Nickname\"]")
    private WebElement nicknameField;
    
    @FindBy(xpath = "//android.widget.EditText[@text=\"Address Line 1\"]")
    private WebElement addressLine1Field;
    
    @FindBy(xpath = "//android.widget.TextView[@text=\"Review\"]")	
	private WebElement reviewtext;    
    
    @FindBy(xpath = "(//android.widget.TextView[@text=\"Show more\"])[1]")
	private WebElement showMoreButton;
	
	@FindBy(xpath = "(//android.widget.TextView[@text=\"Account Number\"]/..//android.widget.TextView)[2]")
	private WebElement accountNumberText;
	
	@FindBy(xpath = "//android.widget.TextView[@text=\"Confirm\"]")
	private WebElement confirmButton;
	
  
    @FindBy(xpath = "//android.widget.TextView[@text=\"Success!\"]")	
    private WebElement successText;
    
    @FindBy(xpath = "//*[@text=\"Add Another\"]")	
    private WebElement addAnotherButton;
    
    @FindBy(xpath = "//*[@text=\"Existing Beneficiary\"]")	
    private WebElement existingBeneficiaryButton;
    
    @FindBy(xpath = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.ImageView")	
    private WebElement ExistingBeneficiarySearchIcon;
    
    @FindBy(xpath = "//*[@class=\"android.widget.EditText\"]")	
    private WebElement searchBeneficiaryField;
    
    @FindBy(xpath = "(//*[@class=\"android.widget.EditText\"]/..//android.widget.ImageView)[1]")	
    private WebElement searchBeneficiaryIcon;
    
    @FindBy(xpath = "//*[@text=\"Pending activation / rejected\"]")	
    private WebElement pendingActivationRejectedTab;
      
    @FindBy(xpath = "//*[@text=\"Active Beneficiaries\"]")	
    private WebElement activeBeneficiariesTab;
    
    // Page Select Source Account
	@FindBy(xpath = "//android.widget.TextView[@text='Select source account']")
    private WebElement selectSourceAccountText;
    
	@FindBy(xpath = "//android.widget.TextView[@text=\"Transfer details\"]")
	private WebElement transferDetailsText;
	
	@FindBy(xpath = "//android.widget.EditText[@text=\"Transfer Amount\"]")
	private WebElement transferAmountField;
	
	@FindBy(xpath = "(//android.widget.TextView[@text='Available Balance']/..//android.widget.TextView)[1]")
	private WebElement availableBalanceText;
	
	@FindBy(xpath = "//android.widget.TextView[@text=\"Purpose of transfer\"]")
	private WebElement purposeOfTransferText;
	
	
	@FindBy(xpath = "//android.widget.EditText[@text=\"Additional Information\"]")
	private WebElement additionalInformationField;
	
	
	@FindBy(xpath = "((//android.view.ViewGroup)[45]//android.widget.TextView)[2]")
	private WebElement ToTransferAccountNumberText;
	
	@FindBy(xpath = "((//android.view.ViewGroup)[49]//android.widget.TextView)[2]")
	private WebElement FromTransferAccountNumberText;

	@FindBy(xpath = "(//android.widget.TextView[@text=\"Transfer Amount\"]/..//android.widget.TextView)[2]")
	private WebElement getTransferAmountText;
			
	
	@FindBy(xpath = "(//android.widget.TextView[@text=\"From\"]/..//android.widget.TextView)[2]")
	private WebElement getfromAccountNumberText;
	
	@FindBy(xpath = "(//android.widget.TextView[@text=\"Balance Before\"]/..//android.widget.TextView)[2]")
	private WebElement getBalBeforeText;
			
	@FindBy(xpath = "(//android.widget.TextView[@text=\"Balance After\"]/..//android.widget.TextView)[2]")
	private WebElement getBalAfterText;
					
	@FindBy(xpath = "//android.widget.TextView[@text=\"Confirm\"]")
	private WebElement confirmTransferButton;
									
	@FindBy(xpath = "//android.widget.TextView[@text=\"Use OTP verification\"]")
	private WebElement useOtpVerificationButton;						
							
	@FindBy(xpath = "//android.widget.TextView[@text=\"SMS validation\"]")
	private WebElement smsValidationText;						
							
	@FindBy(xpath = "(//android.widget.EditText)[1]")
	private WebElement entersmsOtpField;								
	
	@FindBy(xpath = "//android.widget.TextView[@text=\"Account Summary\"]")
	private WebElement accountSummaryButton;
	
	@FindBy(xpath = "(//android.widget.TextView[@text=\"Net available balance for withdrawal\"]/..//android.widget.TextView)[1]")
	private WebElement netAvailableBalanceForWithdrawal;
	
	 // Constructor to initialize the driver and wait
    public TransfersPage(AppiumDriver<MobileElement> driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 20);
        PageFactory.initElements(driver, this); // Initialize @FindBy elements
	}
    
    // Add methods to interact with elements
    
    public boolean getExistingBeneficiaryTabText() {
		return existingBeneficiaryTab.isDisplayed();
	}
	
	public void clickExistingBeneficiaryTab() {
		wait.until(ExpectedConditions.elementToBeClickable(existingBeneficiaryTab));

		existingBeneficiaryTab.click();
	}
	
	public void clickNewBeneficiaryTab() {
		wait.until(ExpectedConditions.elementToBeClickable(newBeneficiaryTab));

		newBeneficiaryTab.click();
	}
	
	public void clickWithinAdcbTab() {
		wait.until(ExpectedConditions.elementToBeClickable(withinAdcbTab));

		withinAdcbTab.click();
	}
	
	public void clickAlertYesButton() {
		wait.until(ExpectedConditions.elementToBeClickable(alretYesButton));
		alretYesButton.click();
		proceedbutton.click();
	}	
	

	public void enterAccountNumber(String accountnumber) throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(accountNumberField));
		accountNumberField.click();
		driver.getKeyboard().pressKey(accountnumber);
		driver.executeScript("mobile: performEditorAction", ImmutableMap.of("action", "Done")); 
		TestUtils.hideKeyboardByDone(driver);
	
		}

	public void clickFetchAccountTitleButton() {
		wait.until(ExpectedConditions.visibilityOf(fetchAccountTitleButton));
		wait.until(ExpectedConditions.elementToBeClickable(fetchAccountTitleButton));
		fetchAccountTitleButton.click();
	}

	public void clickContinueButton() {
		wait.until(ExpectedConditions.visibilityOf(continueButton));
		wait.until(ExpectedConditions.elementToBeClickable(continueButton));
		continueButton.click();
	}

	public void enterNickname(String nickname) {
		wait.until(ExpectedConditions.visibilityOf(nicknameField));
		nicknameField.sendKeys(nickname);
	}

	public void enterAddressLine1(String address) {
		wait.until(ExpectedConditions.visibilityOf(addressLine1Field));
		addressLine1Field.sendKeys(address);
	}
	
	public String getReviewText() {
		wait.until(ExpectedConditions.visibilityOf(reviewtext));
		return reviewtext.getText();
	}
	
	public void clickShowMoreButton() {
		wait.until(ExpectedConditions.elementToBeClickable(showMoreButton));
		showMoreButton.click();
	}
	
	public String getAccountNumberText() {
		wait.until(ExpectedConditions.visibilityOf(accountNumberText));
		return accountNumberText.getText();
	}	
	
	public void clickConfirmButton() {
		wait.until(ExpectedConditions.elementToBeClickable(confirmButton));
		confirmButton.click();
	}
	
	public String getSuccessText() {
		wait.until(ExpectedConditions.visibilityOf(successText));
		return successText.getText();
	}
	
	public void clickAddanotherButton() {
		wait.until(ExpectedConditions.elementToBeClickable(addAnotherButton));
		addAnotherButton.click();
	}
	
		public void clickExistingBeneficiaryButton() {
		wait.until(ExpectedConditions.elementToBeClickable(existingBeneficiaryButton));
		existingBeneficiaryButton.click();
	}
	
	public void clickExistingBeneficiarySearchIcon() {
		wait.until(ExpectedConditions.elementToBeClickable(ExistingBeneficiarySearchIcon));
		ExistingBeneficiarySearchIcon.click();
	}
	
	public void enterSearchBeneficiaryField(String beneficiaryName) throws InterruptedException {
		
		wait.until(ExpectedConditions.visibilityOf(searchBeneficiaryField));
		searchBeneficiaryField.clear();
		Thread.sleep(2000);
		driver.getKeyboard().pressKey(beneficiaryName);
		Thread.sleep(2000);
		searchBeneficiaryIcon.click();
		
		
	}
	
	public void clickPendingBeneficiaries()	
	{
		wait.until(ExpectedConditions.elementToBeClickable(pendingActivationRejectedTab));
		pendingActivationRejectedTab.click();
	}
	
	public boolean isPendingBeneficiariesTabDisplayed() {
		try {
			return pendingActivationRejectedTab.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean isActiveBeneficiariesTabDisplayed() {
		try {
			return activeBeneficiariesTab.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
	
	
	public void clickActiveBeneficiaries()	
	{
			wait.until(ExpectedConditions.elementToBeClickable(activeBeneficiariesTab));
		activeBeneficiariesTab.click();
	}
	
	
	/**
	 * Searches for a beneficiary by name in both Pending and Active tabs.
	 * Returns true if the beneficiary is found in either tab.
	 */
	
	public boolean isSearchBeneficiaryFieldDisplayed(String beneficiaryName) throws InterruptedException {
		
            MobileElement DisplayBeneficiaryName = driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='"+beneficiaryName+"']"));
			return DisplayBeneficiaryName.isDisplayed();
		}
	
/*	public boolean isSearchBeneficiaryFieldDisplayed(String beneficiaryName) throws InterruptedException {
	    boolean found = false;
	    // Use findElements to avoid NoSuchElementException
	    if (driver.findElement(MobileBy.xpath("//*[@text='Pending activation / rejected']")).isDisplayed()) {
	        MobileElement pendingTab = driver.findElement(MobileBy.xpath("//*[@text='Pending activation / rejected']"));
	        Thread.sleep(2000);
	        //pendingTab.click();
	        Thread.sleep(2000);
	        try {
	            MobileElement DisplayBeneficiaryName = driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='"+beneficiaryName+"']"));
	            wait.until(ExpectedConditions.visibilityOf(DisplayBeneficiaryName));
	            if (DisplayBeneficiaryName.isDisplayed()) {
	                found = true;
	            }
	        } catch (Exception e) {
	            // Ignore if not found
	        }
	    }
	    if (driver.findElement(MobileBy.xpath("//*[@text='Active Beneficiaries']")).isDisplayed()) {
	        MobileElement activeTab = driver.findElement(MobileBy.xpath("//*[@text='Active Beneficiaries']"));
	        Thread.sleep(2000);
	        // activeTab.click(); // Uncomment if needed
	        Thread.sleep(2000);
	        try {
	            MobileElement DisplayBeneficiaryName = driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='"+beneficiaryName+"']"));
	            wait.until(ExpectedConditions.visibilityOf(DisplayBeneficiaryName));
	            if (DisplayBeneficiaryName.isDisplayed()) {
	                found = true;
	            }
	        } catch (Exception e) {
	            // Ignore if not found
	        }
	    }
	    return found;
	}
*/	
	public void SelectSearchedBeneficiaryName(String beneficiaryName) throws InterruptedException {
			MobileElement DisplayBeneficiaryName = driver.findElement(MobileBy.xpath(("//android.widget.TextView[@text='"+beneficiaryName+"']")));
			wait.until(ExpectedConditions.visibilityOf(DisplayBeneficiaryName));
			DisplayBeneficiaryName.click();
	}
	
	public boolean isSelectSourceAccountTextDisplayed() {
		try {
			wait.until(ExpectedConditions.visibilityOf(selectSourceAccountText));
			return selectSourceAccountText.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
	
	public void clickSelectSourceAccountText(String sourceAccount) {
       MobileElement sourceaccount = driver.findElement(By.xpath("//android.widget.TextView[@text='"+sourceAccount+"']"));
       sourceaccount.click();
	}
	
	public boolean isTransferDetailsTextDisplayed() {
		try {
			wait.until(ExpectedConditions.visibilityOf(transferDetailsText));
			return transferDetailsText.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
	
	public void enterTransferAmount(String amount) {
		wait.until(ExpectedConditions.visibilityOf(transferAmountField));
		transferAmountField.click();
		driver.getKeyboard().pressKey(amount);
		driver.executeScript("mobile: performEditorAction", ImmutableMap.of("action", "Done"));
		
		TestUtils.hideKeyboardByDone(driver);
	}
	
	public String getAvailableBalanceText() {
		wait.until(ExpectedConditions.visibilityOf(availableBalanceText));
		return availableBalanceText.getText();
	}

	
	
	public void SelectpurposeOfTransfer(String purpose) throws InterruptedException {
		purposeOfTransferText.click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='"+purpose+"']")));
		// Use MobileBy to find the element by text
		MobileElement purposeOfTransferOption = driver.findElement(By.xpath("//android.widget.TextView[@text='"+purpose+"']"));
		wait.until(ExpectedConditions.visibilityOf(purposeOfTransferOption));
		purposeOfTransferOption.click();
		Thread.sleep(2000);
		TestUtils.hideKeyboardByDone(driver);
	}
	
	public String ToTransferAccountNumberText() {
		TestUtils.swipeDown(driver);
		wait.until(ExpectedConditions.visibilityOf(ToTransferAccountNumberText));
		return ToTransferAccountNumberText.getText();
	}
	
	public String FromTransferAccountNumberText() {
		wait.until(ExpectedConditions.visibilityOf(FromTransferAccountNumberText));
		return FromTransferAccountNumberText.getText();
	}
	
	
	public void clickconfirmTransferButton() {
		wait.until(ExpectedConditions.elementToBeClickable(confirmTransferButton));
		confirmTransferButton.click();
	}
	
	
	public String getTransferAmountText() {
		wait.until(ExpectedConditions.visibilityOf(getTransferAmountText));
		return getTransferAmountText.getText();
	}
	
	public String getFromAccountNumberText() {
		wait.until(ExpectedConditions.visibilityOf(getfromAccountNumberText));
		return getfromAccountNumberText.getText();
	}
	
	
	public String getBalBeforeText() {
		wait.until(ExpectedConditions.visibilityOf(getBalBeforeText));
		return getBalBeforeText.getText();
	}
	
	public String getBalAfterText() {
		wait.until(ExpectedConditions.visibilityOf(getBalAfterText));
		return getBalAfterText.getText();
	}
	
	/**
	 * Clicks the Use OTP verification button.
	 */
	public boolean isUseOtpVerificationButtonDisplayed() {
		try {
			wait.until(ExpectedConditions.visibilityOf(useOtpVerificationButton));
			return useOtpVerificationButton.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
	
	public void clickUseOtpVerificationButton() {
		wait.until(ExpectedConditions.elementToBeClickable(useOtpVerificationButton));
		useOtpVerificationButton.click();
	}
	
	public boolean isSmsValidationTextDisplayed() {
		try {
			wait.until(ExpectedConditions.visibilityOf(smsValidationText));
			return smsValidationText.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
	 
	public void enterSmsOtp() {
		wait.until(ExpectedConditions.visibilityOf(entersmsOtpField));
		entersmsOtpField.click();
		driver.getKeyboard().pressKey("111111");
		driver.executeScript("mobile: performEditorAction", ImmutableMap.of("action", "Done")); 
	}
	
	public void clickAccountSummaryButton() {
		wait.until(ExpectedConditions.elementToBeClickable(accountSummaryButton));
		accountSummaryButton.click();
	}
	
	
	public String getNetAvailableBalanceForWithdrawal() {
		wait.until(ExpectedConditions.visibilityOf(netAvailableBalanceForWithdrawal));
		String netAvailableBalanceForWithdrawalAED = netAvailableBalanceForWithdrawal.getText();
		return TestUtils.replaceSymbolWithAed(netAvailableBalanceForWithdrawalAED);
	}
	
	/**
	 * Retrieves all elements with positive AED balances.
	 * Returns a list of MobileElements that match the criteria.
	 */


	/**
	 * Waits for the page to load by checking if the existing beneficiary tab is visible.
	 */
	public void waitForPageToLoad() {
		wait.until(ExpectedConditions.visibilityOf(existingBeneficiaryTab));
	}
	
	
	
	
	
	
	/**
     * Closes the mobile keyboard (works for both Android and iOS).
     */
    public void closeKeyboard() {
        try {
            driver.hideKeyboard();
        } catch (Exception e) {
            // Keyboard may already be hidden or not present
        }
    }

    /**
     * Presses the 'Done' key on the mobile keyboard (Android only).
     */
    public void pressDoneOnKeyboard() {
        if (driver instanceof AndroidDriver) {
            ((AndroidDriver<MobileElement>) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
        } else {
            // For iOS, you may need to locate and tap the 'Done' button manually
            // Example: Find the 'Done' button and click it
            // driver.findElementByAccessibilityId("Done").click();
        }
    }
    
    /**
     * Generic method to enter text into any field and perform keyboard 'Done' operation.
     */
    public void enterTextAndDone(WebElement field, String text) {
        wait.until(ExpectedConditions.visibilityOf(field));
       // field.click();
        field.sendKeys(text);
        pressDoneOnKeyboard();
    }
    
    /**
     * Checks if all key elements of the Transfers page are visible.
     * Returns true if all are displayed, false otherwise.
     */
  /*  public boolean isPageLoaded() {
        try {
            wait.until(ExpectedConditions.visibilityOf(existingBeneficiaryTab));
            wait.until(ExpectedConditions.visibilityOf(newBeneficiaryTab));
            wait.until(ExpectedConditions.visibilityOf(withinAdcbTab));
            wait.until(ExpectedConditions.visibilityOf(alretYesButton));
            wait.until(ExpectedConditions.visibilityOf(proceedbutton));
            wait.until(ExpectedConditions.visibilityOf(accountNumberField));
            // Add more elements as needed
            return existingBeneficiaryTab.isDisplayed() &&
                   newBeneficiaryTab.isDisplayed() &&
                   withinAdcbTab.isDisplayed() &&
                   alretYesButton.isDisplayed() &&
                   proceedbutton.isDisplayed() &&
                   accountNumberField.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    */
    
    /**
     * Captures all visible text from the Review page.
     */
    public List<String> getAllVisibleTextsOnReviewPage() {
        List<String> texts = new ArrayList<>();
        try {
            // Example: Add all review-related elements here
            texts.add(existingBeneficiaryTab.getText());
            texts.add(newBeneficiaryTab.getText());
            texts.add(withinAdcbTab.getText());
            texts.add(alretYesButton.getText());
            texts.add(proceedbutton.getText());
            texts.add(accountNumberField.getText());
            // Add more elements as needed for your Review page
        } catch (Exception e) {
            // Handle if any element is not present
        }
        return texts;
    }

   
    public boolean compareFieldsWithSuccessScreen(List<String> reviewFields, List<String> successFields) {
        for (String field : reviewFields) {
            if (!successFields.contains(field)) {
                return false;
            }
        }
        return true;
    }
    
   
}