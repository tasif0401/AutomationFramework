package com.example.base;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.AppiumDriver;
import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseTest {
	protected static WebDriver webDriver;
    protected static AppiumDriver<WebElement> mobileDriver;

    
    
    public static void initWebDriver(Properties prop) throws Exception {
        String execution = prop.getProperty("execution","local");
        if (execution.equalsIgnoreCase("local"))
        {
        	 if (webDriver != null) {
        		 webDriver.close();
             }
            WebDriverManager.chromedriver().setup();
            webDriver = new ChromeDriver();
            webDriver.manage().window().maximize();
            webDriver.get(prop.getProperty("baseUrl"));
        } else {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("browserName", prop.getProperty("browser","Chrome"));
            caps.setCapability("browserVersion", prop.getProperty("browserVersion","latest"));
            caps.setCapability("platformName", prop.getProperty("platformName","Windows 10"));
            caps.setCapability("build", prop.getProperty("build","BDD-Web"));
            caps.setCapability("name", prop.getProperty("name","Web Test"));
            String hub = prop.getProperty("lt.hub");
            webDriver = new RemoteWebDriver(new URL(hub), caps);
        }
    }
@SuppressWarnings("deprecation")
@BeforeClass()
    public static void initMobileDriver(Properties prop) throws Exception {
        String execution = prop.getProperty("execution","local");
        DesiredCapabilities caps = new DesiredCapabilities();
       
        if(execution.equalsIgnoreCase("local"))
        {
        	 String appPackage = prop.getProperty("appPackage");
             String adbPath = prop.getProperty("adbPath");
             // Always force-stop the app before driver initialization
             try {
                 String adbCmd = adbPath + " shell am force-stop " + appPackage;
                 Runtime.getRuntime().exec(adbCmd).waitFor();
                 Thread.sleep(1000); // Wait for app to fully terminate
             } catch (Exception e) {
                 e.printStackTrace();
             }
            caps.setCapability("platformName", prop.getProperty("platformName"));
            caps.setCapability("appium:deviceName", prop.getProperty("deviceName"));
            caps.setCapability("appium:udid", prop.getProperty("udid"));
            caps.setCapability("appium:noReset",true);
            caps.setCapability("appium:autoLunch",true);
            caps.setCapability("appium:newCommandTimeout","5000");
            caps.setCapability("appium:dontStopAppOnReset",true);
            caps.setCapability("appium:appPackage", prop.getProperty("appPackage"));
            caps.setCapability("appium:appActivity", prop.getProperty("appActivity"));
            caps.setCapability("appium:automationName", prop.getProperty("automationName"));
            System.out.println("[Appium] Capabilities: " + caps);
            System.out.println("[Appium] Server URL: " + prop.getProperty("appium.server","http://127.0.0.1:4723/wd/hub"));
            mobileDriver = new AppiumDriver<>(new URL(prop.getProperty("appium.server","http://127.0.0.1:4723/wd/hub")), caps);
            
        } else if(execution.equalsIgnoreCase("lambdatest")){
        	
		    Map< String, Object> ltOptions= new HashMap<>();
		    ltOptions.put("user", prop.getProperty("lt.username"));
		    ltOptions.put("accessKey", prop.getProperty("lt.accesskey"));
            caps.setCapability("LT:Options", ltOptions);
            caps.setCapability("appium:platformName", prop.getProperty("platformName"));
            caps.setCapability("appium:deviceName", prop.getProperty("deviceName"));
            caps.setCapability("appium:platformVersion", prop.getProperty("platformVersion"));
            caps.setCapability("appium:isRealMobile", Boolean.parseBoolean(prop.getProperty("isRealMobile","true")));
            caps.setCapability("appium:app", prop.getProperty("app"));
            caps.setCapability("appium:visual",true);
            caps.setCapability("appium:autoGrantPermissions",true);
            caps.setCapability("appium:build", prop.getProperty("build","BDD-Mobile MIB"));
            caps.setCapability("appium:name", prop.getProperty("name","Mobile Test- MIB"));
            String hub = prop.getProperty("lt.mobileHub");
            System.out.println("[Appium] Capabilities: " + caps);
            System.out.println("[Appium] LambdaTest Hub: " + hub);
            mobileDriver =  new AppiumDriver<>(new URL(hub), caps);
            
        }
    }
@AfterClass
public static void quitAll() {
    try { if (webDriver != null) webDriver.quit(); } catch (Exception e){}
    try { if (mobileDriver != null) mobileDriver.quit(); } catch (Exception e){}
}


}