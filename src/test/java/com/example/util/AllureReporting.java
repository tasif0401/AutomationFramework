package com.example.util;

import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;

import java.awt.Desktop;
import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;

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

    public static void createHtmlReportFromExisting(String reportSourceDir, String resultsDir) {
        java.nio.file.Path source = java.nio.file.Paths.get(reportSourceDir, "index.html");
        java.nio.file.Path target = java.nio.file.Paths.get(resultsDir, "index.html");
        try {
            java.nio.file.Files.copy(source, target, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
            System.out.println("HTML report copied to: " + target.toString());
        } catch (Exception e) {
            System.err.println("Failed to copy HTML report: " + e.getMessage());
        }
    }
    
    @AfterSuite(alwaysRun = true)
    public void openAllureReport() {
        try {
            String reportPath = "allure-report/Asif.html";
            File report = new File(reportPath);

            if (report.exists()) {
                Desktop.getDesktop().browse(report.toURI());
                System.out.println("✅ Allure report opened automatically: " + reportPath);
            } else {
                System.out.println("⚠️ Allure report not found. Did tests generate results?");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}