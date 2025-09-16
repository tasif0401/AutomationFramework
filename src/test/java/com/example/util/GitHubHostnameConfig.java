package com.example.util;

import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.util.Properties;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Utility class to configure GitHub access for environments with restricted access
 * or custom hostname requirements.
 */
public class GitHubHostnameConfig {
    
    private static boolean isConfigured = false;
    
    /**
     * Configure GitHub access settings based on properties.
     * This configures WebDriverManager and system properties for GitHub access.
     * 
     * @param properties Configuration properties containing GitHub settings
     */
    public static void configureGitHubAccess(Properties properties) {
        if (isConfigured) {
            return; // Already configured
        }
        
        String githubHostname = properties.getProperty("github.hostname", "github.com");
        String githubApiHostname = properties.getProperty("github.api.hostname", "api.github.com");
        String githubApiUrl = properties.getProperty("github.api.url", "https://api.github.com");
        String githubReleasesUrl = properties.getProperty("github.releases.url", "https://github.com");
        
        // Configure proxy if enabled
        boolean proxyEnabled = Boolean.parseBoolean(properties.getProperty("github.proxy.enabled", "false"));
        if (proxyEnabled) {
            String proxyHost = properties.getProperty("github.proxy.host");
            String proxyPort = properties.getProperty("github.proxy.port");
            
            if (proxyHost != null && !proxyHost.trim().isEmpty() && 
                proxyPort != null && !proxyPort.trim().isEmpty()) {
                
                System.setProperty("http.proxyHost", proxyHost);
                System.setProperty("http.proxyPort", proxyPort);
                System.setProperty("https.proxyHost", proxyHost);
                System.setProperty("https.proxyPort", proxyPort);
                
                System.out.println("[GitHubConfig] Configured proxy: " + proxyHost + ":" + proxyPort);
            }
        }
        
        // Configure WebDriverManager for GitHub access
        try {
            // Set GitHub API URL for WebDriverManager if different from default
            if (!githubApiUrl.equals("https://api.github.com")) {
                System.setProperty("wdm.gitHubTokenName", "");
                System.setProperty("wdm.gitHubTokenSecret", "");
                System.out.println("[GitHubConfig] Using custom GitHub API URL: " + githubApiUrl);
            }
            
            // Configure timeout settings for better reliability using system properties
            System.setProperty("wdm.timeout", "30");
            System.setProperty("wdm.avoidBrowserDetection", "true");
            
        } catch (Exception e) {
            System.err.println("[GitHubConfig] Warning: Could not configure WebDriverManager GitHub settings: " + e.getMessage());
        }
        
        // Set system properties for hostname resolution if custom hostnames are provided
        if (!githubHostname.equals("github.com")) {
            System.setProperty("github.custom.hostname", githubHostname);
            System.out.println("[GitHubConfig] Using custom GitHub hostname: " + githubHostname);
        }
        
        if (!githubApiHostname.equals("api.github.com")) {
            System.setProperty("github.api.custom.hostname", githubApiHostname);
            System.out.println("[GitHubConfig] Using custom GitHub API hostname: " + githubApiHostname);
        }
        
        System.out.println("[GitHubConfig] GitHub access configuration completed");
        isConfigured = true;
    }
    
    /**
     * Test GitHub connectivity by attempting to reach the configured GitHub hostname.
     * 
     * @param properties Configuration properties
     * @return true if GitHub is accessible, false otherwise
     */
    public static boolean testGitHubConnectivity(Properties properties) {
        String githubApiUrl = properties.getProperty("github.api.url", "https://api.github.com");
        
        try {
            java.net.URL url = new java.net.URL(githubApiUrl);
            java.net.HttpURLConnection connection = (java.net.HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(10000); // 10 seconds
            connection.setReadTimeout(10000);
            
            int responseCode = connection.getResponseCode();
            System.out.println("[GitHubConfig] GitHub connectivity test - Response code: " + responseCode);
            
            // GitHub API returns 403 for unauthenticated requests, which means it's accessible
            return responseCode == 200 || responseCode == 403;
            
        } catch (Exception e) {
            System.err.println("[GitHubConfig] GitHub connectivity test failed: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Reset configuration state (mainly for testing purposes).
     */
    public static void resetConfiguration() {
        isConfigured = false;
    }
}