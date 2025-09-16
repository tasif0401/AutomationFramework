package com.example.util;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Standalone utility to validate GitHub hostname configuration.
 * Run this to test your GitHub access settings before running tests.
 */
public class GitHubConfigValidator {
    
    public static void main(String[] args) {
        String configFile = "src/test/resources/config/web.properties";
        
        if (args.length > 0) {
            configFile = args[0];
        }
        
        System.out.println("=== GitHub Configuration Validator ===");
        System.out.println("Testing configuration from: " + configFile);
        System.out.println();
        
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream(configFile));
            
            // Display current configuration
            System.out.println("Current GitHub Configuration:");
            System.out.println("- GitHub Hostname: " + prop.getProperty("github.hostname", "github.com"));
            System.out.println("- GitHub API Hostname: " + prop.getProperty("github.api.hostname", "api.github.com"));
            System.out.println("- GitHub API URL: " + prop.getProperty("github.api.url", "https://api.github.com"));
            System.out.println("- Proxy Enabled: " + prop.getProperty("github.proxy.enabled", "false"));
            
            if (Boolean.parseBoolean(prop.getProperty("github.proxy.enabled", "false"))) {
                System.out.println("- Proxy Host: " + prop.getProperty("github.proxy.host", ""));
                System.out.println("- Proxy Port: " + prop.getProperty("github.proxy.port", ""));
            }
            System.out.println();
            
            // Configure GitHub access
            System.out.println("Configuring GitHub access...");
            GitHubHostnameConfig.configureGitHubAccess(prop);
            
            // Test connectivity
            System.out.println("Testing GitHub connectivity...");
            boolean isConnected = GitHubHostnameConfig.testGitHubConnectivity(prop);
            
            if (isConnected) {
                System.out.println("✅ SUCCESS: GitHub is accessible with current configuration");
            } else {
                System.out.println("❌ WARNING: GitHub may not be accessible with current configuration");
                System.out.println("   This might be due to network restrictions or incorrect settings");
            }
            
            System.out.println();
            System.out.println("=== Validation Complete ===");
            
        } catch (Exception e) {
            System.err.println("❌ ERROR: Failed to validate configuration");
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}