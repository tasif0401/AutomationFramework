package com.mib.android.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

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

    
    @FindBy(xpath = "//android.widget.EditText[@text=\"Account Number\"]")
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
    
    @FindBy(xpath = "//android.widget.TextView[@text=\"Show more\"]")
	private WebElement showMoreButton;
	
	@FindBy(xpath = "(//android.widget.TextView[@text=\"Account Number\"]/..//android.widget.TextView)[2]")
	private WebElement accountNumberText;
	
	// Example of a dynamic element, adjust as needed
     		
    		
    
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
	

	public void enterAccountNumber(String accountnumber) {
		wait.until(ExpectedConditions.visibilityOf(accountNumberField));
		enterTextAndDone(accountNumberField, accountnumber);
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

    /**
     * Compares all fields from Review page with Success screen fields.
     * Returns true if all review fields are present in success screen.
     */
    public boolean compareFieldsWithSuccessScreen(List<String> reviewFields, List<String> successFields) {
        for (String field : reviewFields) {
            if (!successFields.contains(field)) {
                return false;
            }
        }
        return true;
    }
}