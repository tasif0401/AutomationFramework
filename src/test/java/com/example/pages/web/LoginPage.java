package com.example.pages.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By username = By.xpath("//*[@name='username']");
    private By password = By.xpath("//*[@type='password']");
    private By loginBtn = By.id("loginBtn");

    public LoginPage(WebDriver driver){ 
        this.driver = driver; 
        this.wait = new WebDriverWait(driver, 20);
    }

    public void open(String url){ driver.get(url); }

    public void enterUsername(String u)
    { 
        wait.until(ExpectedConditions.visibilityOfElementLocated(username));
        driver.findElement(username).clear(); 
        driver.findElement(username).sendKeys(u); 
    }

    public void enterPassword(String p){ 
        wait.until(ExpectedConditions.visibilityOfElementLocated(password));
        driver.findElement(password).clear(); 
        driver.findElement(password).sendKeys(p); 
    }

    public void clickLogin(){ driver.findElement(loginBtn).click(); }

    public String getTitle(){ return driver.getTitle(); }
}