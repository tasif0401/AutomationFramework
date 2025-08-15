
package com.example.pages.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage {
    private WebDriver driver;
    private By welcome = By.id("welcome");
    public DashboardPage(WebDriver driver){ this.driver = driver; }
    public boolean isWelcomeVisible(){ return driver.findElements(welcome).size() > 0; }
}
