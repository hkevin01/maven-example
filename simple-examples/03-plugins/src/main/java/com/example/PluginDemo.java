package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Demonstrates various Maven plugin configurations and their effects.
 * This class shows how different plugins interact with the build process.
 */
public class PluginDemo {
    
    private static final Logger logger = LoggerFactory.getLogger(PluginDemo.class);
    
    private final Properties buildProperties;
    
    public PluginDemo() {
        this.buildProperties = loadBuildProperties();
    }
    
    /**
     * Demonstrates Maven resource filtering by loading filtered properties.
     */
    private Properties loadBuildProperties() {
        Properties props = new Properties();
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("build.properties")) {
            if (is != null) {
                props.load(is);
                logger.info("Loaded build properties from resources");
            } else {
                logger.warn("build.properties not found in resources");
            }
        } catch (IOException e) {
            logger.error("Failed to load build properties", e);
        }
        return props;
    }
    
    /**
     * Shows information about the build, demonstrating Maven JAR plugin manifest entries.
     */
    public void showBuildInfo() {
        logger.info("=== Build Information ===");
        
        // Information from JAR manifest (added by maven-jar-plugin)
        Package pkg = getClass().getPackage();
        if (pkg != null) {
            String implementationTitle = pkg.getImplementationTitle();
            String implementationVersion = pkg.getImplementationVersion();
            String implementationVendor = pkg.getImplementationVendor();
            
            logger.info("Implementation Title: {}", implementationTitle);
            logger.info("Implementation Version: {}", implementationVersion);
            logger.info("Implementation Vendor: {}", implementationVendor);
        }
        
        // Information from filtered resources
        if (!buildProperties.isEmpty()) {
            logger.info("=== Filtered Build Properties ===");
            buildProperties.forEach((key, value) -> 
                logger.info("{}: {}", key, value));
        }
    }
    
    /**
     * Demonstrates code that will be analyzed by various quality plugins.
     */
    public String processArguments(String[] args) {
        if (args == null || args.length == 0) {
            return "No arguments provided";
        }
        
        StringBuilder result = new StringBuilder();
        result.append("Processing arguments: ");
        
        for (int i = 0; i < args.length; i++) {
            if (i > 0) {
                result.append(", ");
            }
            result.append(args[i]);
        }
        
        return result.toString();
    }
    
    /**
     * Calculates factorial - demonstrates method that will be tested for coverage.
     */
    public long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial is not defined for negative numbers");
        }
        if (n == 0 || n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }
    
    /**
     * Demonstrates string utility method that will be used in tests.
     */
    public boolean isPalindrome(String str) {
        if (str == null) {
            return false;
        }
        
        String cleaned = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        String reversed = new StringBuilder(cleaned).reverse().toString();
        return cleaned.equals(reversed);
    }
    
    /**
     * Shows system properties - demonstrates exec plugin functionality.
     */
    public void showSystemInfo() {
        logger.info("=== System Information ===");
        logger.info("Java Version: {}", System.getProperty("java.version"));
        logger.info("Java Vendor: {}", System.getProperty("java.vendor"));
        logger.info("OS Name: {}", System.getProperty("os.name"));
        logger.info("OS Version: {}", System.getProperty("os.version"));
        logger.info("User Name: {}", System.getProperty("user.name"));
        logger.info("Working Directory: {}", System.getProperty("user.dir"));
    }
    
    public static void main(String[] args) {
        logger.info("Starting Plugin Configuration Demo");
        
        PluginDemo demo = new PluginDemo();
        
        // Show build information (JAR plugin manifest)
        demo.showBuildInfo();
        
        // Process command line arguments (exec plugin)
        String argumentsResult = demo.processArguments(args);
        logger.info(argumentsResult);
        
        // Show system information
        demo.showSystemInfo();
        
        // Demonstrate some calculations
        logger.info("Factorial of 5: {}", demo.factorial(5));
        logger.info("Is 'A man a plan a canal Panama' a palindrome? {}", 
                   demo.isPalindrome("A man a plan a canal Panama"));
        
        logger.info("Plugin Configuration Demo completed");
    }
}
