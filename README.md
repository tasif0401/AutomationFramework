
# AutomationFramework_Full

Upgraded Web + Mobile (Android/iOS) BDD automation framework with:
- Local execution (ChromeDriver + Appium) and LambdaTest remote execution (placeholders)
- Expanded page objects for web and mobile
- 5 sample tests (web and mobile) and Excel test data via Apache POI
- GitHub Actions matrix CI workflow and Jenkinsfile
- ExtentReports / Cucumber reports

Security note: Do NOT commit your LT credentials in the repo. Use GitHub Secrets or Jenkins Credentials.

How to use:
1. Configure execution mode in src/test/resources/config/*.properties (execution=local or execution=lambdatest).
2. For local web, ensure Chrome is installed. WebDriverManager auto-downloads ChromeDriver.
3. For local mobile, start Appium: `appium` and connect device/emulator.
4. To run locally: `mvn clean test`
5. To run on LambdaTest, set execution=lambdatest and update lt.* placeholders or configure secrets in CI.

CI:
- GitHub Actions uses matrix and expects LT_USERNAME & LT_ACCESS_KEY as repository secrets.
- Jenkinsfile expects credentials configured in Jenkins Credentials with IDs `LT_USERNAME` and `LT_ACCESS_KEY`.

