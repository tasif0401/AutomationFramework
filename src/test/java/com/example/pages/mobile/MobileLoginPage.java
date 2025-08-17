
package com.example.pages.mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MobileLoginPage 
{
    private AppiumDriver<MobileElement> driver;
    private WebDriverWait wait;

    private By username = By.id("username");
    private By password = By.xpath("//android.widget.EditText[@text='Password']");
    private By loginBtn = By.xpath("//android.widget.TextView[@text='Login']");

    public MobileLoginPage(AppiumDriver<MobileElement> driver){ 
    	this.driver = driver;
        this.wait = new WebDriverWait(driver, 20);
    }

    public void enterUsername(String u)
    { 
    	driver.findElement(username).sendKeys(u); 
    	
    }
    public void enterPassword(String p)
    { 
        wait.until(ExpectedConditions.visibilityOfElementLocated(password));
    	driver.findElement(password).sendKeys(p); 
    }
    public void clickLogin()
    { 
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginBtn));
    	driver.findElement(loginBtn).click(); 
    }
}
