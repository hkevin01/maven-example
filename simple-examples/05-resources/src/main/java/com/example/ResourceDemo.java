package com.example;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.Enumeration;

/**
 * Demonstrates Maven resource handling capabilities including:
 * - Resource filtering and property substitution
 * - Loading properties files from classpath
 * - Accessing filtered resources at runtime
 * - Working with different resource types
 */
public class ResourceDemo {
    
    private static final Logger logger = LoggerFactory.getLogger(ResourceDemo.class);
    
    public static void main(String[] args) {
        ResourceDemo demo = new ResourceDemo();
        
        System.out.println("=== Maven Resource Handling Demo ===\n");
        
        demo.demonstrateFilteredProperties();
        demo.demonstrateConfigurationFiles();
        demo.demonstrateResourceAccess();
        demo.demonstrateEnvironmentSpecificConfig();
    }
    
    /**
     * Demonstrates how Maven filters properties files during build
     */
    public void demonstrateFilteredProperties() {
        System.out.println("1. Filtered Properties Demonstration:");
        System.out.println("=====================================");
        
        try {
            Properties appProps = loadProperties("application.properties");
            
            System.out.println("Application Information (filtered by Maven):");
            System.out.println("- Name: " + appProps.getProperty("app.name"));
            System.out.println("- Version: " + appProps.getProperty("app.version"));
            System.out.println("- Description: " + appProps.getProperty("app.description"));
            System.out.println("- Author: " + appProps.getProperty("app.author"));
            System.out.println("- Build Timestamp: " + appProps.getProperty("build.timestamp"));
            
        } catch (Exception e) {
            logger.error("Error loading filtered properties", e);
        }
        
        System.out.println();
    }
    
    /**
     * Demonstrates loading configuration files from resources
     */
    public void demonstrateConfigurationFiles() {
        System.out.println("2. Configuration Files Demonstration:");
        System.out.println("====================================");
        
        try {
            Configuration config = new Configurations().properties("database.properties");
            
            System.out.println("Database Configuration:");
            System.out.println("- URL: " + config.getString("database.url"));
            System.out.println("- Username: " + config.getString("database.username"));
            System.out.println("- Pool Size: " + config.getInt("database.pool.size", 10));
            System.out.println("- Timeout: " + config.getInt("api.timeout"));
            
        } catch (Exception e) {
            logger.error("Error loading configuration files", e);
        }
        
        System.out.println();
    }
    
    /**
     * Demonstrates different ways to access resources
     */
    public void demonstrateResourceAccess() {
        System.out.println("3. Resource Access Methods:");
        System.out.println("===========================");
        
        // Method 1: Using ClassLoader
        InputStream stream1 = getClass().getClassLoader().getResourceAsStream("data/sample.txt");
        System.out.println("Via ClassLoader: " + (stream1 != null ? "✓ Found" : "✗ Not found"));
        
        // Method 2: Using Class.getResource()
        URL resource = getClass().getResource("/config/logback.xml");
        System.out.println("Via Class.getResource(): " + (resource != null ? "✓ Found at " + resource : "✗ Not found"));
        
        // Method 3: Using ResourceBundle-like approach
        try {
            Properties messages = loadProperties("messages.properties");
            System.out.println("Messages file loaded with " + messages.size() + " entries");
            System.out.println("- Welcome message: " + messages.getProperty("welcome.message"));
            System.out.println("- Error message: " + messages.getProperty("error.general"));
        } catch (Exception e) {
            logger.warn("Could not load messages.properties", e);
        }
        
        System.out.println();
    }
    
    /**
     * Demonstrates environment-specific configuration
     */
    public void demonstrateEnvironmentSpecificConfig() {
        System.out.println("4. Environment-Specific Configuration:");
        System.out.println("=====================================");
        
        try {
            Properties envProps = loadProperties("environment.properties");
            
            System.out.println("Current Environment Settings:");
            System.out.println("- Log Level: " + envProps.getProperty("log.level"));
            System.out.println("- Environment: " + envProps.getProperty("environment", "default"));
            System.out.println("- Debug Mode: " + envProps.getProperty("debug.enabled", "false"));
            System.out.println("- Feature Flags: " + envProps.getProperty("features.experimental", "disabled"));
            
            // Demonstrate profile-specific values
            String profile = System.getProperty("maven.profile", "default");
            System.out.println("- Active Profile: " + profile);
            
        } catch (Exception e) {
            logger.error("Error loading environment configuration", e);
        }
        
        System.out.println();
    }
    
    /**
     * Utility method to load properties from classpath
     */
    public Properties loadProperties(String filename) throws Exception {
        Properties props = new Properties();
        try (InputStream stream = getClass().getClassLoader().getResourceAsStream(filename)) {
            if (stream == null) {
                throw new RuntimeException("Could not find " + filename + " in classpath");
            }
            props.load(stream);
        }
        return props;
    }
    
    /**
     * Lists all available resources (useful for debugging)
     */
    public void listAvailableResources() {
        System.out.println("Available Resources:");
        System.out.println("===================");
        
        try {
            Enumeration<URL> resources = getClass().getClassLoader().getResources("");
            while (resources.hasMoreElements()) {
                URL resource = resources.nextElement();
                System.out.println("- " + resource);
            }
        } catch (Exception e) {
            logger.error("Error listing resources", e);
        }
    }
    
    /**
     * Gets application information from filtered properties
     */
    public ApplicationInfo getApplicationInfo() {
        try {
            Properties props = loadProperties("application.properties");
            return new ApplicationInfo(
                props.getProperty("app.name"),
                props.getProperty("app.version"),
                props.getProperty("app.description"),
                props.getProperty("app.author")
            );
        } catch (Exception e) {
            logger.error("Error loading application info", e);
            return new ApplicationInfo("Unknown", "Unknown", "Unknown", "Unknown");
        }
    }
    
    /**
     * Simple data class for application information
     */
    public static class ApplicationInfo {
        private final String name;
        private final String version;
        private final String description;
        private final String author;
        
        public ApplicationInfo(String name, String version, String description, String author) {
            this.name = name;
            this.version = version;
            this.description = description;
            this.author = author;
        }
        
        // Getters
        public String getName() { return name; }
        public String getVersion() { return version; }
        public String getDescription() { return description; }
        public String getAuthor() { return author; }
        
        @Override
        public String toString() {
            return String.format("ApplicationInfo{name='%s', version='%s', description='%s', author='%s'}", 
                               name, version, description, author);
        }
    }
}
