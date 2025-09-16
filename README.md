
# AutomationFramework_Full

Upgraded Web + Mobile (Android/iOS) BDD automation framework with:
- Local execution (ChromeDriver + Appium) and LambdaTest remote execution (placeholders)
- Expanded page objects for web and mobile
- 5 sample tests (web and mobile) and Excel test data via Apache POI
- GitHub Actions matrix CI workflow and Jenkinsfile
- ExtentReports / Cucumber reports
- **GitHub hostname configuration for restricted environments**

Security note: Do NOT commit your LT credentials in the repo. Use GitHub Secrets or Jenkins Credentials.

## GitHub Access Configuration

This framework includes configurable GitHub hostname settings for environments with restricted internet access or enterprise GitHub setups:

### Configuration Properties
Add these properties to your config files (`src/test/resources/config/*.properties`):

```properties
# GitHub access configuration (for environments with restricted access)
github.hostname=github.com
github.api.hostname=api.github.com
github.releases.hostname=github.com

# Proxy settings for GitHub access (optional)
github.proxy.enabled=false
github.proxy.host=your.proxy.host
github.proxy.port=8080

# Custom GitHub endpoints (optional, for enterprise GitHub)
github.api.url=https://api.github.com
github.releases.url=https://github.com
```

### Use Cases
- **Corporate networks**: Configure proxy settings for GitHub access
- **Enterprise GitHub**: Set custom hostnames for GitHub Enterprise Server
- **Restricted environments**: Configure alternate GitHub endpoints
- **WebDriverManager**: Ensures proper driver downloads from GitHub releases

### Features
- Automatic WebDriverManager configuration for GitHub access
- Proxy support for corporate environments
- Connectivity testing before driver downloads
- Enterprise GitHub Server support
- Fallback mechanisms for restricted access

How to use:
1. Configure execution mode in src/test/resources/config/*.properties (execution=local or execution=lambdatest).
2. **Configure GitHub access settings if needed** (see GitHub Access Configuration above).
3. For local web, ensure Chrome is installed. WebDriverManager auto-downloads ChromeDriver.
4. For local mobile, start Appium: `appium` and connect device/emulator.
5. To run locally: `mvn clean test`
6. To run on LambdaTest, set execution=lambdatest and update lt.* placeholders or configure secrets in CI.

CI:
- GitHub Actions uses matrix and expects LT_USERNAME & LT_ACCESS_KEY as repository secrets.
- Jenkinsfile expects credentials configured in Jenkins Credentials with IDs `LT_USERNAME` and `LT_ACCESS_KEY`.

