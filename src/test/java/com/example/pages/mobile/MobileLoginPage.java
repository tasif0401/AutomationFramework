
package com.example.pages.mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class MobileLoginPage {
    private AppiumDriver<MobileElement> driver;
    private By username = By.id("username");
    private By password = By.id("password");
    private By loginBtn = By.id("loginBtn");

    public MobileLoginPage(AppiumDriver<MobileElement> driver){ this.driver = driver; }

    public void enterUsername(String u){ driver.findElement(username).sendKeys(u); }
    public void enterPassword(String p){ driver.findElement(password).sendKeys(p); }
    public void clickLogin(){ driver.findElement(loginBtn).click(); }
}
