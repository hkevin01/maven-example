package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Map;
import java.util.TreeMap;

/**
 * Demonstrates comprehensive Maven properties usage including:
 * - Built-in Maven properties
 * - Custom defined properties
 * - Environment variables
 * - System properties
 * - Resource filtering with properties
 * - Property inheritance in profiles
 */
public class PropertiesDemo {

    private static final Logger logger = LoggerFactory.getLogger(PropertiesDemo.class);

    public static void main(String[] args) {
        PropertiesDemo demo = new PropertiesDemo();

        logger.info("=== Maven Properties Demonstration ===");

        demo.displayApplicationInfo();
        demo.showSystemProperties();
        demo.showEnvironmentVariables();
        demo.loadFilteredProperties();
        demo.loadGeneratedProperties();
        demo.demonstratePropertyTypes();
        demo.showBuildInformation();
    }

    /**
     * Display basic application information from filtered properties.
     */
    public void displayApplicationInfo() {
        logger.info("\n1. Application Information:");
        logger.info("   Name: {}", getSystemPropertyOrDefault("app.name", "Unknown"));
        logger.info("   Environment: {}", getSystemPropertyOrDefault("environment", "Unknown"));
        logger.info("   Log Level: {}", getSystemPropertyOrDefault("log.level", "Unknown"));
        logger.info("   Build Timestamp: {}", getSystemPropertyOrDefault("build.timestamp", "Unknown"));
    }

    /**
     * Show relevant system properties including Maven-provided ones.
     */
    public void showSystemProperties() {
        logger.info("\n2. Relevant System Properties:");

        Map<String, String> relevantProps = new TreeMap<>();
        Properties sysProps = System.getProperties();

        // Filter and display Maven and application-related properties
        for (String name : sysProps.stringPropertyNames()) {
            if (name.startsWith("app.") ||
                name.startsWith("maven.") ||
                name.startsWith("java.") ||
                name.startsWith("user.") ||
                name.equals("environment") ||
                name.equals("log.level") ||
                name.equals("build.timestamp")) {
                relevantProps.put(name, sysProps.getProperty(name));
            }
        }

        relevantProps.forEach((key, value) ->
            logger.info("   {}: {}", key, value));
    }

    /**
     * Show environment variables that might affect the application.
     */
    public void showEnvironmentVariables() {
        logger.info("\n3. Environment Variables:");

        Map<String, String> env = System.getenv();
        Map<String, String> relevantEnv = new TreeMap<>();

        // Show Maven and development-related environment variables
        String[] relevantVars = {"MAVEN_HOME", "JAVA_HOME", "PATH", "M2_HOME",
                                "MAVEN_OPTS", "JAVA_OPTS", "USER", "HOME"};

        for (String var : relevantVars) {
            if (env.containsKey(var)) {
                String value = env.get(var);
                // Truncate PATH for readability
                if ("PATH".equals(var) && value.length() > 100) {
                    value = value.substring(0, 100) + "...";
                }
                relevantEnv.put(var, value);
            }
        }

        relevantEnv.forEach((key, value) ->
            logger.info("   {}: {}", key, value));
    }

    /**
     * Load and display properties from filtered resource files.
     */
    public void loadFilteredProperties() {
        logger.info("\n4. Filtered Properties from Resources:");

        try {
            Properties appProps = loadProperties("application.properties");
            Properties configProps = loadProperties("config/database.properties");

            logger.info("   Application Properties:");
            displayPropertiesSubset(appProps, "app.");

            logger.info("   Database Configuration:");
            displayPropertiesSubset(configProps, "database.");

        } catch (Exception e) {
            logger.error("Error loading filtered properties: {}", e.getMessage());
        }
    }

    /**
     * Load and display generated properties file.
     */
    public void loadGeneratedProperties() {
        logger.info("\n5. Generated Properties (from properties-maven-plugin):");

        try {
            Properties generatedProps = loadProperties("generated.properties");

            // Display a subset of interesting generated properties
            String[] interestingProps = {"project.groupId", "project.artifactId",
                                       "project.version", "maven.compiler.source",
                                       "app.name", "environment"};

            for (String prop : interestingProps) {
                String value = generatedProps.getProperty(prop);
                if (value != null) {
                    logger.info("   {}: {}", prop, value);
                }
            }

        } catch (Exception e) {
            logger.warn("Generated properties not available: {}", e.getMessage());
        }
    }

    /**
     * Demonstrate different types of Maven properties.
     */
    public void demonstratePropertyTypes() {
        logger.info("\n6. Maven Property Types Demonstration:");

        logger.info("   Built-in Properties:");
        logger.info("     - Maven version: {}", getMavenVersion());
        logger.info("     - Java version: {}", System.getProperty("java.version"));
        logger.info("     - Operating System: {}", System.getProperty("os.name"));

        logger.info("   Project Properties (from POM):");
        logger.info("     - App Name: {}", getSystemPropertyOrDefault("app.name", "Not available"));
        logger.info("     - Environment: {}", getSystemPropertyOrDefault("environment", "Not available"));

        logger.info("   Settings Properties:");
        logger.info("     - User Home: {}", System.getProperty("user.home"));
        logger.info("     - Maven Local Repository: {}", getMavenLocalRepository());
    }

    /**
     * Show build and timestamp information.
     */
    public void showBuildInformation() {
        logger.info("\n7. Build Information:");

        try {
            Properties buildProps = loadProperties("build-info.properties");

            logger.info("   Build Details:");
            buildProps.forEach((key, value) ->
                logger.info("     {}: {}", key, value));

        } catch (Exception e) {
            logger.info("   Build timestamp: {}",
                getSystemPropertyOrDefault("build.timestamp", "Not available"));
        }
    }

    /**
     * Utility method to load properties from classpath.
     */
    public Properties loadProperties(String filename) throws IOException {
        Properties props = new Properties();

        try (InputStream is = getClass().getClassLoader().getResourceAsStream(filename)) {
            if (is == null) {
                throw new IOException("Could not find " + filename + " in classpath");
            }
            props.load(is);
        }

        return props;
    }

    /**
     * Display properties with a specific prefix.
     */
    private void displayPropertiesSubset(Properties props, String prefix) {
        props.entrySet().stream()
            .filter(entry -> entry.getKey().toString().startsWith(prefix))
            .sorted(Map.Entry.comparingByKey())
            .forEach(entry ->
                logger.info("     {}: {}", entry.getKey(), entry.getValue()));
    }

    /**
     * Get system property with default value.
     */
    private String getSystemPropertyOrDefault(String key, String defaultValue) {
        return System.getProperty(key, defaultValue);
    }

    /**
     * Get Maven version if available.
     */
    private String getMavenVersion() {
        try {
            // Try to get Maven version from system properties or environment
            String version = System.getProperty("maven.version");
            if (version == null) {
                version = "Unknown (check with 'mvn --version')";
            }
            return version;
        } catch (Exception e) {
            return "Unknown";
        }
    }

    /**
     * Get Maven local repository location.
     */
    private String getMavenLocalRepository() {
        String userHome = System.getProperty("user.home");
        return userHome + "/.m2/repository";
    }

    /**
     * Get property information for testing.
     */
    public PropertyInfo getPropertyInfo() {
        return new PropertyInfo(
            getSystemPropertyOrDefault("app.name", "Unknown"),
            getSystemPropertyOrDefault("environment", "Unknown"),
            getSystemPropertyOrDefault("log.level", "Unknown"),
            getSystemPropertyOrDefault("build.timestamp", "Unknown")
        );
    }

    /**
     * Data class for property information.
     */
    public static class PropertyInfo {
        private final String appName;
        private final String environment;
        private final String logLevel;
        private final String buildTimestamp;

        public PropertyInfo(String appName, String environment, String logLevel, String buildTimestamp) {
            this.appName = appName;
            this.environment = environment;
            this.logLevel = logLevel;
            this.buildTimestamp = buildTimestamp;
        }

        public String getAppName() { return appName; }
        public String getEnvironment() { return environment; }
        public String getLogLevel() { return logLevel; }
        public String getBuildTimestamp() { return buildTimestamp; }

        @Override
        public String toString() {
            return String.format("PropertyInfo{appName='%s', environment='%s', logLevel='%s', buildTimestamp='%s'}",
                appName, environment, logLevel, buildTimestamp);
        }
    }
}
