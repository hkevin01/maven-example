package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

/**
 * Demonstrates different Maven packaging strategies and their use cases.
 * This class can be packaged as:
 * - Standard JAR (with dependencies managed separately)
 * - Fat/Uber JAR (all dependencies included)
 * - Executable JAR (Spring Boot style)
 * - WAR file (for web deployment)
 * - Custom assembly
 */
public class PackagingDemo {
    
    private static final Logger logger = LoggerFactory.getLogger(PackagingDemo.class);
    
    public static void main(String[] args) {
        PackagingDemo demo = new PackagingDemo();
        
        System.out.println("=== Maven Packaging Demonstration ===\n");
        
        demo.displayApplicationInfo();
        demo.demonstratePackagingTypes();
        demo.checkDependencies();
        demo.analyzeRuntimeEnvironment();
        demo.showManifestInformation();
    }
    
    /**
     * Display basic application information
     */
    public void displayApplicationInfo() {
        System.out.println("1. Application Information:");
        System.out.println("===========================");
        System.out.println("Application: Maven Packaging Examples");
        System.out.println("Version: 1.0.0");
        System.out.println("Java Version: " + System.getProperty("java.version"));
        System.out.println("Maven Packaging Type: " + getPackagingType());
        System.out.println();
    }
    
    /**
     * Demonstrates different packaging approaches and their characteristics
     */
    public void demonstratePackagingTypes() {
        System.out.println("2. Packaging Types Demonstration:");
        System.out.println("=================================");
        
        String[] packagingTypes = {
            "Standard JAR - Dependencies managed externally",
            "Fat/Uber JAR - All dependencies included in single file",
            "Executable JAR - Self-contained with embedded server",
            "WAR File - Web application archive for servlet containers",
            "Custom Assembly - Tailored packaging with specific structure"
        };
        
        for (int i = 0; i < packagingTypes.length; i++) {
            System.out.println((i + 1) + ". " + packagingTypes[i]);
        }
        
        System.out.println("\nCurrent Runtime Configuration:");
        System.out.println("- Classpath entries: " + getClasspathEntryCount());
        System.out.println("- JAR file location: " + getJarLocation());
        System.out.println("- Execution mode: " + getExecutionMode());
        System.out.println();
    }
    
    /**
     * Checks if required dependencies are available
     */
    public void checkDependencies() {
        System.out.println("3. Dependency Verification:");
        System.out.println("===========================");
        
        // Check SLF4J
        checkDependency("SLF4J Logging", "org.slf4j.Logger");
        
        // Check Logback
        checkDependency("Logback Classic", "ch.qos.logback.classic.Logger");
        
        // Check Jackson
        checkDependency("Jackson JSON", "com.fasterxml.jackson.databind.ObjectMapper");
        
        // Check Servlet API (if available)
        checkDependency("Servlet API", "javax.servlet.http.HttpServlet");
        
        System.out.println();
    }
    
    /**
     * Analyzes the runtime environment
     */
    public void analyzeRuntimeEnvironment() {
        System.out.println("4. Runtime Environment Analysis:");
        System.out.println("================================");
        
        System.out.println("- Working Directory: " + System.getProperty("user.dir"));
        System.out.println("- Java Home: " + System.getProperty("java.home"));
        System.out.println("- OS: " + System.getProperty("os.name") + " " + System.getProperty("os.version"));
        System.out.println("- Architecture: " + System.getProperty("os.arch"));
        System.out.println("- Available Processors: " + Runtime.getRuntime().availableProcessors());
        System.out.println("- Max Memory: " + (Runtime.getRuntime().maxMemory() / (1024 * 1024)) + " MB");
        System.out.println("- Free Memory: " + (Runtime.getRuntime().freeMemory() / (1024 * 1024)) + " MB");
        
        System.out.println();
    }
    
    /**
     * Shows manifest information if available
     */
    public void showManifestInformation() {
        System.out.println("5. JAR Manifest Information:");
        System.out.println("============================");
        
        try {
            InputStream manifestStream = getClass().getClassLoader().getResourceAsStream("META-INF/MANIFEST.MF");
            if (manifestStream != null) {
                Manifest manifest = new Manifest(manifestStream);
                Attributes attributes = manifest.getMainAttributes();
                
                System.out.println("- Main-Class: " + attributes.getValue("Main-Class"));
                System.out.println("- Implementation-Title: " + attributes.getValue("Implementation-Title"));
                System.out.println("- Implementation-Version: " + attributes.getValue("Implementation-Version"));
                System.out.println("- Implementation-Vendor: " + attributes.getValue("Implementation-Vendor"));
                System.out.println("- Built-By: " + attributes.getValue("Built-By"));
                System.out.println("- Build-Timestamp: " + attributes.getValue("Build-Timestamp"));
                System.out.println("- Class-Path: " + attributes.getValue("Class-Path"));
                
                manifestStream.close();
            } else {
                System.out.println("No manifest file found (running from IDE or test environment)");
            }
        } catch (IOException e) {
            logger.warn("Could not read manifest information", e);
            System.out.println("Could not read manifest: " + e.getMessage());
        }
        
        System.out.println();
    }
    
    /**
     * Helper method to check if a dependency is available
     */
    private void checkDependency(String name, String className) {
        try {
            Class.forName(className);
            System.out.println("✓ " + name + " - Available");
        } catch (ClassNotFoundException e) {
            System.out.println("✗ " + name + " - Not available");
        }
    }
    
    /**
     * Gets the current packaging type
     */
    public String getPackagingType() {
        String jarFile = getJarLocation();
        if (jarFile.contains("-fat.jar")) {
            return "Fat/Uber JAR";
        } else if (jarFile.contains("-executable.jar")) {
            return "Executable JAR";
        } else if (jarFile.endsWith(".war")) {
            return "WAR File";
        } else if (jarFile.contains("-jar-with-dependencies.jar")) {
            return "Assembly JAR";
        } else if (jarFile.endsWith(".jar")) {
            return "Standard JAR";
        } else {
            return "Development/IDE";
        }
    }
    
    /**
     * Gets the location of the current JAR file
     */
    public String getJarLocation() {
        try {
            return PackagingDemo.class.getProtectionDomain()
                .getCodeSource().getLocation().toString();
        } catch (Exception e) {
            return "Unknown";
        }
    }
    
    /**
     * Gets the execution mode
     */
    public String getExecutionMode() {
        String location = getJarLocation();
        if (location.startsWith("file:") && location.endsWith(".jar")) {
            return "JAR Execution";
        } else if (location.contains("target/classes")) {
            return "Development/Maven";
        } else {
            return "IDE/Test Environment";
        }
    }
    
    /**
     * Counts classpath entries
     */
    public int getClasspathEntryCount() {
        String classpath = System.getProperty("java.class.path");
        if (classpath == null) return 0;
        return classpath.split(System.getProperty("path.separator")).length;
    }
    
    /**
     * Demonstrates JSON processing capability
     */
    public String processJson(String jsonString) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Object jsonObject = mapper.readValue(jsonString, Object.class);
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
        } catch (Exception e) {
            logger.error("Error processing JSON", e);
            return "Error: " + e.getMessage();
        }
    }
    
    /**
     * Loads application properties
     */
    public Properties loadApplicationProperties() {
        Properties props = new Properties();
        try (InputStream stream = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            if (stream != null) {
                props.load(stream);
            }
        } catch (IOException e) {
            logger.warn("Could not load application properties", e);
        }
        return props;
    }
    
    /**
     * Utility method for web context (when packaged as WAR)
     */
    public boolean isWebContext() {
        try {
            Class.forName("javax.servlet.http.HttpServlet");
            return getJarLocation().endsWith(".war") || 
                   System.getProperty("catalina.home") != null;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
    
    /**
     * Gets detailed packaging information
     */
    public PackagingInfo getPackagingInfo() {
        return new PackagingInfo(
            getPackagingType(),
            getJarLocation(),
            getExecutionMode(),
            getClasspathEntryCount(),
            isWebContext()
        );
    }
    
    /**
     * Data class for packaging information
     */
    public static class PackagingInfo {
        private final String type;
        private final String location;
        private final String executionMode;
        private final int classpathEntries;
        private final boolean webContext;
        
        public PackagingInfo(String type, String location, String executionMode, 
                           int classpathEntries, boolean webContext) {
            this.type = type;
            this.location = location;
            this.executionMode = executionMode;
            this.classpathEntries = classpathEntries;
            this.webContext = webContext;
        }
        
        // Getters
        public String getType() { return type; }
        public String getLocation() { return location; }
        public String getExecutionMode() { return executionMode; }
        public int getClasspathEntries() { return classpathEntries; }
        public boolean isWebContext() { return webContext; }
        
        @Override
        public String toString() {
            return String.format("PackagingInfo{type='%s', location='%s', executionMode='%s', " +
                               "classpathEntries=%d, webContext=%s}", 
                               type, location, executionMode, classpathEntries, webContext);
        }
    }
}
