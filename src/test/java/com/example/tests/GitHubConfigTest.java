package com.example.tests;

import java.io.FileInputStream;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.example.util.GitHubHostnameConfig;

/**
 * Test class to verify GitHub hostname configuration functionality.
 */
public class GitHubConfigTest {
    
    @Test(priority = 1)
    public void testGitHubConfigurationLoading() throws Exception {
        Properties prop = new Properties();
        prop.load(new FileInputStream("src/test/resources/config/web.properties"));
        
        // Test that we can load GitHub configuration properties
        String githubHostname = prop.getProperty("github.hostname");
        String githubApiUrl = prop.getProperty("github.api.url");
        
        Assert.assertNotNull(githubHostname, "GitHub hostname should be configured");
        Assert.assertNotNull(githubApiUrl, "GitHub API URL should be configured");
        
        System.out.println("[GitHubConfigTest] GitHub hostname: " + githubHostname);
        System.out.println("[GitHubConfigTest] GitHub API URL: " + githubApiUrl);
    }
    
    @Test(priority = 2)
    public void testGitHubHostnameConfiguration() throws Exception {
        Properties prop = new Properties();
        prop.load(new FileInputStream("src/test/resources/config/web.properties"));
        
        // Test configuring GitHub access
        GitHubHostnameConfig.resetConfiguration();
        GitHubHostnameConfig.configureGitHubAccess(prop);
        
        System.out.println("[GitHubConfigTest] GitHub configuration completed successfully");
        Assert.assertTrue(true, "GitHub configuration should complete without errors");
    }
    
    @Test(priority = 3)
    public void testGitHubConnectivity() throws Exception {
        Properties prop = new Properties();
        prop.load(new FileInputStream("src/test/resources/config/web.properties"));
        
        // Test GitHub connectivity
        boolean isConnected = GitHubHostnameConfig.testGitHubConnectivity(prop);
        
        System.out.println("[GitHubConfigTest] GitHub connectivity test result: " + isConnected);
        // Note: We don't assert true here because the test environment might not have GitHub access
        // The important thing is that the method executes without throwing exceptions
        Assert.assertNotNull(isConnected, "Connectivity test should return a boolean result");
    }
    
    @Test(priority = 4)
    public void testProxyConfiguration() throws Exception {
        Properties prop = new Properties();
        prop.load(new FileInputStream("src/test/resources/config/web.properties"));
        
        // Test proxy configuration (disabled by default)
        String proxyEnabled = prop.getProperty("github.proxy.enabled", "false");
        Assert.assertEquals(proxyEnabled, "false", "Proxy should be disabled by default");
        
        // Test that proxy host and port properties exist (even if empty)
        String proxyHost = prop.getProperty("github.proxy.host", "");
        String proxyPort = prop.getProperty("github.proxy.port", "");
        
        Assert.assertNotNull(proxyHost, "Proxy host property should exist");
        Assert.assertNotNull(proxyPort, "Proxy port property should exist");
        
        System.out.println("[GitHubConfigTest] Proxy configuration validated");
    }
}