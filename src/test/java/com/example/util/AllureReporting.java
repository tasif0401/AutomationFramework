package com.example.util;

import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class AllureReporting {
    public static void logPass(String message) {
        Allure.step(message, Status.PASSED);
    }

    public static void logFail(String message) {
        Allure.step(message, Status.FAILED);
    }

    public static void logSkip(String message) {
        Allure.step(message, Status.SKIPPED);
    }

    public static void logInfo(String message) {
        Allure.step(message, Status.BROKEN);
    }

    public static void attachScreenshot(WebDriver driver, String name) {
        if (driver instanceof TakesScreenshot) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.getLifecycle().addAttachment(name, "image/png", "png", screenshot);
        }
    }

    public static void attachText(String name, String content) {
        Allure.addAttachment(name, content);
    }

    public static void setTestCaseName(String featureFileName) {
        Allure.getLifecycle().updateTestCase(tc -> tc.setName(featureFileName));
    }
}
