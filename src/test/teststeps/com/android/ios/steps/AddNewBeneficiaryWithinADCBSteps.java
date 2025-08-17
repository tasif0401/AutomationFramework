package com.android.ios.steps;

import com.example.base.BaseTest;
import com.example.pages.mobile.MobileLoginPage;
import com.mib.android.page.AccountSummaryPage;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class AddNewBeneficiaryWithinADCBSteps extends BaseTest {
    private AccountSummaryPage accountSummaryPage;

    public void initAccountSummaryPage(AppiumDriver<MobileElement> driver) {
        accountSummaryPage = new AccountSummaryPage(driver);
    }

    // Add your step definitions here
}
