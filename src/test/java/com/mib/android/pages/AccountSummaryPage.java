package com.mib.android.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class AccountSummaryPage {

	
	private AppiumDriver<MobileElement> driver;
    private WebDriverWait wait;
    
    	// Define the elements using @FindBy annotations
    
    @FindBy(xpath = "//android.widget.TextView[@text='ACCOUNT SUMMARY']")
    private WebElement accountSummaryTitle;
    	
    @FindBy(xpath = "//android.widget.TextView[@text='TRANSFERS']")
    private WebElement transfersTab;
    
    // Constructor to initialize the driver and wait
    
    public AccountSummaryPage(AppiumDriver<MobileElement> driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 20);
        PageFactory.initElements(driver, this); // Initialize @FindBy elements
	}
    
    // Add methods to interact with elements
    
    public String getAccountSummaryTitle() {
        wait.until(ExpectedConditions.visibilityOf(accountSummaryTitle));
        return accountSummaryTitle.getText();
    }
    
    public void clickTransfersTab() {
		wait.until(ExpectedConditions.elementToBeClickable(transfersTab));
		transfersTab.click();
		
	}
}