package com.example.util;

import io.qameta.allure.Step;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class TestUtils {
	
	
    /**
     * Advanced sendKeys supporting both Selenium WebElement and Appium MobileElement, with Allure logging.
     */
    @Step("Send keys '{text}' to element: {element}")
    public static void sendKeys(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    @Step("Send keys '{text}' to mobile element: {element}")
    public static void sendKeys(MobileElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Clear text from an element (WebElement).
     */
    @Step("Clear text from element: {element}")
    public static void clearText(WebElement element) {
        element.clear();
    }

    /**
     * Clear text from a mobile element (MobileElement).
     */
    @Step("Clear text from mobile element: {element}")
    public static void clearText(MobileElement element) {
        element.clear();
    }

    /**
     * Get text from an element (WebElement).
     */
    @Step("Get text from element: {element}")
    public static String getText(WebElement element) {
        return element.getText();
    }

    /**
     * Get text from a mobile element (MobileElement).
     */
    @Step("Get text from mobile element: {element}")
    public static String getText(MobileElement element) {
        return element.getText();
    }

    @Step("Swipe screen vertically")
    public void swipeScreen(AppiumDriver<MobileElement> driver) {
        int height = driver.manage().window().getSize().height;
        int width = driver.manage().window().getSize().width;
        int startX = width / 2;
        int startY = (int) (height * 0.8);
        int endY = (int) (height * 0.2);
        new TouchAction<>(driver)
            .press(PointOption.point(startX, startY))
            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
            .moveTo(PointOption.point(startX, endY))
            .release()
            .perform();
    }
    
    /**
     * Performs a vertical swipe from top to bottom of the screen (scrolls down).
     */
    @Step("Swipe down on screen")
    public static void swipeDown(AppiumDriver<MobileElement> driver) {
        int height = driver.manage().window().getSize().height;
        int width = driver.manage().window().getSize().width;
        int startX = width / 2;
        int startY = (int) (height * 0.2);
        int endY = (int) (height * 0.8);
        new TouchAction<>(driver)
            .press(PointOption.point(startX, startY))
            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
            .moveTo(PointOption.point(startX, endY))
            .release()
            .perform();
    }
    
    // Scroll to element (WebElement)
    @Step("Scroll to element: {element}")
    public static void scrollToElement(WebElement element) {
        // Example using JavaScriptExecutor
        org.openqa.selenium.JavascriptExecutor js = (JavascriptExecutor) ((RemoteWebElement)element).getWrappedDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

     @Step("Scroll to element: {element}")
    public static void scrollToElement(AppiumDriver<MobileElement> element) {
        // Example using JavaScriptExecutor
        org.openqa.selenium.JavascriptExecutor js = (JavascriptExecutor) ((WrapsDriver) element).getWrappedDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // Is displayed (WebElement)
    @Step("Check if element is displayed: {element}")
    public static boolean isDisplayed(WebElement element) {
        return element.isDisplayed();
    }

    // Handle alert (accept)
    @Step("Accept alert")
    public static void handleAlert(WebDriver driver) {
        driver.switchTo().alert().accept();
    }

    // Handle pop-up (dismiss)
    @Step("Dismiss pop-up")
    public static void handlePopUp(WebDriver driver) {
        driver.switchTo().alert().dismiss();
    }

    // Hide keyboard by cancel (MobileElement)
    @Step("Hide keyboard by cancel")
    public static void hideKeyboardByCancel(AppiumDriver<MobileElement> driver) {
        driver.hideKeyboard(); // Remove argument, use default
    }

    // Send keys from keyboard (WebElement)
    @Step("Send keys from keyboard: {text} to element: {element}")
    public static void sendKeysFromKeyboard(WebElement element, String text) {
        element.sendKeys(text);
    }

    // Swipe by coordinates (MobileElement)
    @Step("Swipe by coordinates from ({startX},{startY}) to ({endX},{endY})")
    public static void swipeByCoordinates(AppiumDriver<MobileElement> driver, int startX, int startY, int endX, int endY) {
        new io.appium.java_client.TouchAction<>(driver)
            .press(new io.appium.java_client.touch.offset.PointOption().withCoordinates(startX, startY))
            .moveTo(new io.appium.java_client.touch.offset.PointOption().withCoordinates(endX, endY))
            .release()
            .perform();
    }

    // Swipe left on screen (MobileElement)
    @Step("Swipe left on screen")
    public static void swipeLeftOnScreen(AppiumDriver<MobileElement> driver, int y, int startX, int endX) {
        new io.appium.java_client.TouchAction<>(driver)
            .press(new io.appium.java_client.touch.offset.PointOption().withCoordinates(startX, y))
            .moveTo(new io.appium.java_client.touch.offset.PointOption().withCoordinates(endX, y))
            .release()
            .perform();
    }

    // Random alpha string
    @Step("Generate random alpha string of length: {length}")
    public static String randomAlphaString(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        java.util.Random rnd = new java.util.Random();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return sb.toString();
    }

    // Swipe screen (direction)
    @Step("Swipe screen in direction: {direction}")
    public static void swipeScreen(AppiumDriver<MobileElement> driver, String direction) {
        // Basic example for vertical/horizontal swipe
        org.openqa.selenium.Dimension size = driver.manage().window().getSize();
        int startX = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);
        if ("UP".equalsIgnoreCase(direction)) {
            new io.appium.java_client.TouchAction<>(driver)
                .press(new io.appium.java_client.touch.offset.PointOption().withCoordinates(startX, startY))
                .moveTo(new io.appium.java_client.touch.offset.PointOption().withCoordinates(startX, endY))
                .release().perform();
        } else if ("DOWN".equalsIgnoreCase(direction)) {
            new io.appium.java_client.TouchAction<>(driver)
                .press(new io.appium.java_client.touch.offset.PointOption().withCoordinates(startX, endY))
                .moveTo(new io.appium.java_client.touch.offset.PointOption().withCoordinates(startX, startY))
                .release().perform();
        }
        // Add LEFT/RIGHT as needed
    }

    // Switch to frame by name/id
    @Step("Switch to frame: {frameName}")
    public static void switchToFrame(WebDriver driver, String frameName) {
        driver.switchTo().frame(frameName);
    }

    // Switch to frame by index
    @Step("Switch to frame by index: {index}")
    public static void switchToFrameByIndex(WebDriver driver, int index) {
        driver.switchTo().frame(index);
    }

    // Page scroll down by pixels
    @Step("Page scroll down by pixels: {pixels}")
    public static void pageScrollDown(WebDriver driver, int pixels) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0," + pixels + ")");
    }

    // Hide keyboard (Appium)
    @Step("Hide keyboard")
    public static void hideKeyboard(AppiumDriver<MobileElement> driver) {
        driver.hideKeyboard();
    }

    // Get WebElement list by By
    @Step("Get WebElement list by locator: {by}")
    public static java.util.List<WebElement> getWebElementList(WebDriver driver, By by) {
        return driver.findElements(by);
    }

    // Get size of elements by By
    @Step("Get size of elements by locator: {by}")
    public static int getSize(WebDriver driver, By by) {
        return driver.findElements(by).size();
    }

    // Get text by attribute
    @Step("Get text by attribute: {attribute} for locator: {by}")
    public static String getTextByAttribute(WebDriver driver, By by, String attribute) {
        WebElement element = driver.findElement(by);
        return element.getAttribute(attribute);
    }

    // Select by visible text (WebElement)
    @Step("Select by visible text: {text} for element: {element}")
    public static void selectByVisibleText(WebElement element, String text) {
        new Select(element).selectByVisibleText(text);
    }

    // Hide keyboard by Done (Appium)
    @Step("Hide keyboard by Done")
    public static void hideKeyboardByDone(AppiumDriver<MobileElement> driver) {
        driver.hideKeyboard(); // Remove argument, use default
    }

    // Page scroll to element
    @Step("Page scroll to element: {element} with alignment: {align}")
    public static void pageScrollToElement(WebDriver driver, WebElement element, String align) {
       ( (JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: '" + align + "'});", element);
    }

    // Accept alert box
    @Step("Accept alert box")
    public static void acceptAlertBox(WebDriver driver) {
        driver.switchTo().alert().accept();
    }

    // Zip file
    @Step("Zip file: {filePath}")
    public static void zip(String filePath) throws java.io.IOException {
        // Simple zip logic (single file)
        java.io.File file = new java.io.File(filePath);
        java.util.zip.ZipOutputStream zos = new java.util.zip.ZipOutputStream(new java.io.FileOutputStream(filePath + ".zip"));
        java.io.FileInputStream fis = new java.io.FileInputStream(file);
        zos.putNextEntry(new java.util.zip.ZipEntry(file.getName()));
        byte[] buffer = new byte[1024];
        int len;
        while ((len = fis.read(buffer)) > 0) {
            zos.write(buffer, 0, len);
        }
        zos.closeEntry();
        fis.close();
        zos.close();
    }

    // Clear content (WebElement)
    @Step("Clear content of element: {element} with value: {value}")
    public static void clearContent(WebElement element, String value) {
        element.clear();
        element.sendKeys(value);
    }

    // Click (WebElement)
    @Step("Click element: {element} with description: {desc}")
    public static void click(WebElement element, String desc) {
        element.click();
    }

    // Click (By)
    @Step("Click element by locator: {by} with description: {desc}")
    public static void click(WebDriver driver,By by, String desc) {
        driver.findElement(by).click();
        
    }
   
    // Click (By)
    @Step("Click element by locator: {by} with description: {desc}")
    public static void click(AppiumDriver<MobileElement> driver,By by) {					 
        driver.findElement(by).click();
        
    }
    // iOS tap button (By)
    @Step("iOS tap button by locator: {by} with description: {desc}")
    public static void iOSTapButton(AppiumDriver<MobileElement> driver,By by, String desc) {
        driver.findElement(by).click();
    }

    // Customised dropdown selection
    @Step("Customised dropdown selection")
    public static void customisedDropdownSelection(WebElement dropdown, WebElement option, WebElement container, String value) {
        dropdown.click();
        option.click();
    }

    // Click checkbox
    @Step("Click checkbox: {element} with description: {desc}")
    public static void clickCheckbox(WebElement element, String desc) {
        if (!element.isSelected()) {
            element.click();
        }
    }

    // Wait until element loads (WebElement)
    @Step("Wait until element loads: {element} for {timeout} seconds with description: {desc}")
    public static void waitUntilElementLoads(WebDriver driver, WebElement element, int timeout, String desc) {
        new WebDriverWait(driver, timeout).until(
            ExpectedConditions.visibilityOf(element)
        );
    }

    // Wait until element loads (By)
    @Step("Wait until element loads by locator: {by} for {timeout} seconds with description: {desc}")
    public static void waitUntilElementLoads(WebDriver driver, By by, int timeout, String desc) {
        new WebDriverWait(driver, timeout).until(
            ExpectedConditions.visibilityOfElementLocated(by)
        );
    }

    // Wait until element clickable (WebElement)
    @Step("Wait until element clickable: {element} for {timeout} seconds with description: {desc}")
    public static void waitUntilElementClickable(WebDriver driver, WebElement element, int timeout, String desc) {
        new WebDriverWait(driver, timeout).until(
            ExpectedConditions.elementToBeClickable(element)
        );
    }

    // Wait until element clickable (By)
    @Step("Wait until element clickable by locator: {by} for {timeout} seconds with description: {desc}")
    public static void waitUntilElementClickable(WebDriver driver, By by, int timeout, String desc) {
        new WebDriverWait(driver, timeout).until(
            ExpectedConditions.elementToBeClickable(by)
        );
    }

    // Implicit wait (seconds)
    @Step("Implicit wait for {seconds} seconds")
    public static void implicitWait(WebDriver driver, int seconds) {
        driver.manage().timeouts().implicitlyWait(seconds, java.util.concurrent.TimeUnit.SECONDS);
    }

    // Implicit wait (milliseconds)
    @Step("Implicit wait for {milliseconds} milliseconds")
    public static void implicitWaitInMilliSeconds(WebDriver driver, int milliseconds) {
        driver.manage().timeouts().implicitlyWait(milliseconds, java.util.concurrent.TimeUnit.MILLISECONDS);
    }

    // Keyboard operations
    @Step("Keyboard operations: {keys}")
    public static void keyboardOperations(WebElement element, String keys) {
        element.sendKeys(keys);
    }

    // Mouse over
    @Step("Mouse over element: {element} with description: {desc}")
    public static void mouseOver(WebDriver driver, WebElement element, String desc) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    // Is object present (WebElement)
    @Step("Is object present: {element} with description: {desc}")
    public static boolean isObjectPresent(WebElement element, String desc) {
        return element != null && element.isDisplayed();
    }

    // Get text (By)
    @Step("Get text by locator: {by} with description: {desc}")
    public static String getText(WebDriver driver, By by, String desc) {
        return driver.findElement(by).getText();
    }

    // Get text (WebElement)
    @Step("Get text from element: {element} with description: {desc}")
    public static String getText(WebElement element, String desc) {
        return element.getText();
    }

    // Is object present (By)
    @Step("Is object present by locator: {by} with description: {desc}")
    public static boolean isObjectPresent(WebDriver driver, By by, String desc) {
        try {
            return driver.findElement(by).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Is object present (By, timeout)
    @Step("Is object present by locator: {by} with timeout: {timeout}")
    public static boolean isObjectPresent(WebDriver driver, By by, int timeout) {
        try {
            new WebDriverWait(driver, timeout).until(
                ExpectedConditions.visibilityOfElementLocated(by)
            );
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Store element value
    @Step("Store element value: {key} = {value}")
    public static void storeElementValue(java.util.Map<String, String> storage, String key, String value) {
        storage.put(key, value);
    }

    // Get data storage field value
    @Step("Get data storage field value: {key}")
    public static String getDataStorageFieldValue(java.util.Map<String, String> storage, String key) {
        return storage.get(key);
    }

    // Get element value
    @Step("Get element value: {key} from storage")
    public static String getElementValue(java.util.Map<String, String> storage, String key) {
        return storage.get(key);
    }

    // Store element value common
    @Step("Store element value common: {key} = {value}")
    public static void storeElementValueCommon(java.util.Map<String, String> storage, String key, String value) {
        storage.put(key, value);
    }

    // Get value from web table
    @Step("Get value from web table: {tableXpath}, row: {row}, col: {col}, header: {header}, value: {value}")
    public static String getValueFromWebTable(WebDriver driver, String tableXpath, String row, String col, String header, String value) {
        // Example: find cell by xpath
        String cellXpath = tableXpath + "/tbody/tr[" + row + "]/td[" + col + "]";
        WebElement cell = driver.findElement(By.xpath(cellXpath));
        return cell.getText();
    }

    /**
     * Get account numbers with amount greater than 10,000 AED.
     * @param accountNumberElements List of WebElements for account numbers
     * @param amountElements List of WebElements for amounts (same order as account numbers)
     * @return java.util.List<String> of valid account numbers
     */
    @Step("Get account numbers with amount > 10,000 AED")
    public static java.util.List<String> getValidAccountNumbers(java.util.List<WebElement> accountNumberElements, java.util.List<WebElement> amountElements) {
        java.util.List<String> validAccounts = new java.util.ArrayList<>();
        for (int i = 0; i < accountNumberElements.size() && i < amountElements.size(); i++) {
            String amountText = amountElements.get(i).getText().replaceAll("[^\\d.]", ""); // Remove non-numeric chars
            double amount = 0;
            try {
                amount = Double.parseDouble(amountText);
            } catch (NumberFormatException e) {
                continue; // skip if not a valid number
            }
            if (amount > 10000) {
                validAccounts.add(accountNumberElements.get(i).getText());
            }
        }
        return validAccounts;
    }

    // Take screenshot and save to file
    @Step("Take screenshot and save to: {filePath}")
    public static void takeScreenshot(WebDriver driver, String filePath) {
        try {
            org.openqa.selenium.TakesScreenshot ts = (org.openqa.selenium.TakesScreenshot) driver;
            java.io.File srcFile = ts.getScreenshotAs(org.openqa.selenium.OutputType.FILE);
            java.nio.file.Files.copy(srcFile.toPath(), java.nio.file.Paths.get(filePath), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            System.out.println("Screenshot failed: " + e.getMessage());
        }
    }

    // Take screenshot and return as BufferedImage (for comparison)
    @Step("Take screenshot and return as BufferedImage")
    public static java.awt.image.BufferedImage takeScreenshotAsImage(WebDriver driver) {
        try {
            org.openqa.selenium.TakesScreenshot ts = (org.openqa.selenium.TakesScreenshot) driver;
            java.io.File srcFile = ts.getScreenshotAs(org.openqa.selenium.OutputType.FILE);
            return javax.imageio.ImageIO.read(srcFile);
        } catch (Exception e) {
            System.out.println("Screenshot as image failed: " + e.getMessage());
            return null;
        }
    }

    // Compare two images (BufferedImage)
    @Step("Compare two screenshots")
    public static boolean compareImages(java.awt.image.BufferedImage img1, java.awt.image.BufferedImage img2) {
        if (img1 == null || img2 == null) return false;
        if (img1.getWidth() != img2.getWidth() || img1.getHeight() != img2.getHeight()) return false;
        for (int y = 0; y < img1.getHeight(); y++) {
            for (int x = 0; x < img1.getWidth(); x++) {
                if (img1.getRGB(x, y) != img2.getRGB(x, y)) {
                    return false;
                }
            }
        }
        return true;
    }

    // Store all visible element values on screen
    @Step("Store all visible element values")
    public static void storeAllElementValues(WebDriver driver, java.util.Map<String, String> storage, java.util.List<By> locators) {
        for (By by : locators) {
            try {
                WebElement element = driver.findElement(by);
                if (element.isDisplayed()) {
                    storage.put(by.toString(), element.getText());
                }
            } catch (Exception e) {
                // skip if not found
            }
        }
    }

    // Validate new screen values against stored values
    @Step("Validate new screen values against stored values")
    public static boolean validateScreenValues(WebDriver driver, java.util.Map<String, String> storage, java.util.List<By> locators) {
        boolean allMatch = true;
        for (By by : locators) {
            try {
                WebElement element = driver.findElement(by);
                String oldValue = storage.get(by.toString());
                String newValue = element.getText();
                if (oldValue != null && !oldValue.equals(newValue)) {
                    allMatch = false;
                    System.out.println("Value mismatch for " + by + ": " + oldValue + " != " + newValue);
                }
            } catch (Exception e) {
                allMatch = false;
                System.out.println("Element not found for validation: " + by);
            }
        }
        return allMatch;
    }
    /**
     * Try multiple locators (By objects) and return the first matching WebElement.
     * Useful for dynamic XPath handling when element locators change.
     */
    @Step("Find element using multiple locators")
    public static WebElement findElementWithFallback(WebDriver driver, java.util.List<By> locators) {
        for (By by : locators) {
            try {
                WebElement element = driver.findElement(by);
                if (element.isDisplayed()) {
                    return element;
                }
            } catch (Exception e) {
                // Try next locator
            }
        }
        throw new org.openqa.selenium.NoSuchElementException("Element not found with any provided locator");
    }

    /**
     * Try multiple locators (By objects) and return the first matching MobileElement (Appium).
     */
    @Step("Find mobile element using multiple locators")
    public static MobileElement findMobileElementWithFallback(AppiumDriver<MobileElement> driver, java.util.List<By> locators) {
        for (By by : locators) {
            try {
                MobileElement element = (MobileElement) driver.findElement(by);
                if (element.isDisplayed()) {
                    return element;
                }
            } catch (Exception e) {
                // Try next locator
            }
        }
        throw new org.openqa.selenium.NoSuchElementException("Mobile element not found with any provided locator");
    }
    
    @Step("Hide keyboard by Done or Cancel")
    public static void HideKeyboardByDone(AppiumDriver<MobileElement> driver) {
		try {
			driver.findElement(MobileBy.AccessibilityId("Done")).click();
		} catch (Exception e) {
			try {
				driver.findElement(MobileBy.AccessibilityId("Cancel")).click();
			} catch (Exception e1) {
				driver.hideKeyboard();
			}
		}
	}
    
    /**
     * Swipe up on the screen using AppiumDriver.
     */
    @Step("Swipe up on screen")
    public static void swipeUpOnScreen(AppiumDriver<MobileElement> driver) {
        try {
            Dimension size = driver.manage().window().getSize();
            System.out.println("Size of Device Screen : " + size);
            int startX = (int) (size.width * 0.50);
            int endX = startX;
            int startY = (int) (size.height * 0.70);
            int endY = (int) (size.height * 0.43);
            System.out.println("StartX = " + startX + " StartY = " + startY + " EndY = " + endY + " Endx = " + endX);
            Thread.sleep(1000);
            WaitOptions waitOptions = WaitOptions.waitOptions(Duration.ofMillis(600));
            new TouchAction<>(driver)
                .press(PointOption.point(startX, startY))
                .waitAction(waitOptions)
                .moveTo(PointOption.point(endX, endY))
                .release()
                .perform();
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Checks if an element with the given text is present on the screen, with implicit wait for element to load.
     */
    @Step("Check if element with text '{elementText}' is present (with implicit wait)")
    public static boolean isElementPresent(AppiumDriver<MobileElement> driver, String elementText) {
        try {
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS); // 5 seconds implicit wait
            MobileElement element = driver.findElement(
                MobileBy.AndroidUIAutomator(
                    "new UiSelector().textContains(\"" + elementText + "\")"
                )
            );
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        } finally {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // Reset to 0 or default
        }
    }

    /**
     * Scrolls up until the element with the given text is present on the screen.
     * Returns true if found, false otherwise.
     */
    @Step("Scroll to element with text '{elementText}'")
    public static boolean scrollToElementPresent(AppiumDriver<MobileElement> driver,String elementText) {
        int maxScrolls = 10; // Prevent infinite loop
        int scrollCount = 0;
        while (scrollCount < maxScrolls) {
            try { Thread.sleep(2000); } catch (InterruptedException e) {}
            if (isElementPresent(driver, elementText)) {
                return true;
            }
            TestUtils.swipeUpOnScreen(driver);
            scrollCount++;
        }
        return false;
    }

    /**
     * Clicks the first element on the screen that contains the given text.
     */
    @Step("Click element with text '{sourceAccount}'")
    public static void clickElementByText(AppiumDriver<MobileElement> mobileDriver, String sourceAccount) {
        try {
            MobileElement element = mobileDriver.findElement(
                MobileBy.AndroidUIAutomator(
                    "new UiSelector().textContains(\"" + sourceAccount + "\")"
                )
            );
            element.click();
        } catch (Exception e) {
            System.out.println("Element with text '" + sourceAccount + "' not found: " + e.getMessage());
        }
    }
    
    public static void ConvertCurrencySymbol(AppiumDriver<MobileElement> driver, String fromCurrency, String toCurrency) {
		try {
			MobileElement fromCurrencyElement = driver.findElement(MobileBy.AndroidUIAutomator(
					"new UiSelector().textContains(\"" + fromCurrency + "\")"));
			String fromSymbol = fromCurrencyElement.getText().replaceAll("[^\\p{Sc}]", "").trim();
			System.out.println("From Currency Symbol: " + fromSymbol);		
		}catch (Exception e) {
			System.out.println("From Currency Symbol not found: " + e.getMessage());
    }
    }
   
    /**
     * Replaces the currency symbol in a string like " 53,432.75" with the amount and "AED".
     * Example: " 53,432.75" -> "53,432.75 AED"
     */
    public static String replaceSymbolWithAed(String text) {
        // Remove the first character (currency symbol) and trim
        String amount = text.substring(1).trim();
        return amount + " AED";
    }
    
  
}