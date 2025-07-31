package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Map;
import java.util.TreeMap;
import java.util.Arrays;
import java.util.List;

/**
 * Demonstrates comprehensive Maven profiles usage including:
 * - Environment-specific profiles (local, dev, test, staging, prod)
 * - Feature flag profiles (debug, metrics, security)
 * - Build configuration profiles (fast, full)
 * - Activation-based profiles (JDK, OS, property, file-based)
 * - Profile inheritance and property override
 * - Runtime profile detection and configuration
 */
public class ProfileDemo {

    private static final Logger logger = LoggerFactory.getLogger(ProfileDemo.class);

    public static void main(String[] args) {
        ProfileDemo demo = new ProfileDemo();

        logger.info("=== Maven Profiles Demonstration ===");

        demo.displayActiveConfiguration();
        demo.showEnvironmentSettings();
        demo.showFeatureFlags();
        demo.showBuildConfiguration();
        demo.showDatabaseConfiguration();
        demo.showApiConfiguration();
        demo.demonstrateProfileEffects();
        demo.showRuntimeInformation();
    }

    /**
     * Display the currently active configuration based on profiles.
     */
    public void displayActiveConfiguration() {
        logger.info("\n1. Active Configuration Summary:");

        ProfileConfiguration config = getProfileConfiguration();

        logger.info("   Environment: {}", config.getEnvironment());
        logger.info("   Log Level: {}", config.getLogLevel());
        logger.info("   Debug Enabled: {}", config.isDebugEnabled());
        logger.info("   Metrics Enabled: {}", config.isMetricsEnabled());
        logger.info("   Security Strict: {}", config.isSecurityStrict());
        logger.info("   Cache Enabled: {}", config.isCacheEnabled());
    }

    /**
     * Show environment-specific settings.
     */
    public void showEnvironmentSettings() {
        logger.info("\n2. Environment Settings:");

        String environment = getSystemPropertyOrDefault("environment", "unknown");

        try {
            Properties envProps = loadProperties("environments/" + environment + ".properties");

            logger.info("   Configuration for '{}' environment:", environment);
            envProps.forEach((key, value) ->
                logger.info("     {}: {}", key, value));

        } catch (Exception e) {
            logger.warn("   Environment-specific configuration not found for: {}", environment);
            logger.info("   Using default configuration");
        }
    }

    /**
     * Display feature flag status.
     */
    public void showFeatureFlags() {
        logger.info("\n3. Feature Flags:");

        ProfileConfiguration config = getProfileConfiguration();

        logger.info("   Debug Features: {}",
            config.isDebugEnabled() ? "ENABLED" : "DISABLED");
        logger.info("   Performance Metrics: {}",
            config.isMetricsEnabled() ? "ENABLED" : "DISABLED");
        logger.info("   Caching: {}",
            config.isCacheEnabled() ? "ENABLED" : "DISABLED");
        logger.info("   Strict Security: {}",
            config.isSecurityStrict() ? "ENABLED" : "DISABLED");

        // Show additional feature flags if available
        showAdditionalFeatures();
    }

    /**
     * Show build configuration details.
     */
    public void showBuildConfiguration() {
        logger.info("\n4. Build Configuration:");

        logger.info("   Compile Debug: {}",
            getBooleanProperty("compile.debug", true));
        logger.info("   Compile Optimize: {}",
            getBooleanProperty("compile.optimize", false));
        logger.info("   Skip Tests: {}",
            getBooleanProperty("skip.tests", false));
        logger.info("   Skip Integration Tests: {}",
            getBooleanProperty("skip.integration.tests", true));

        // Show build type if in CI
        String buildType = System.getProperty("build.type");
        if (buildType != null) {
            logger.info("   Build Type: {}", buildType);
        }
    }

    /**
     * Show database configuration.
     */
    public void showDatabaseConfiguration() {
        logger.info("\n5. Database Configuration:");

        try {
            Properties dbProps = loadProperties("database.properties");

            logger.info("   Database Configuration:");
            dbProps.forEach((key, value) -> {
                // Mask sensitive information
                String displayValue = key.toString().toLowerCase().contains("password")
                    ? "***MASKED***" : value.toString();
                logger.info("     {}: {}", key, displayValue);
            });

        } catch (Exception e) {
            logger.info("   Using system properties for database configuration:");
            logger.info("     Host: {}", getSystemPropertyOrDefault("database.host", "localhost"));
            logger.info("     Port: {}", getSystemPropertyOrDefault("database.port", "5432"));
            logger.info("     Name: {}", getSystemPropertyOrDefault("database.name", "maven_demo"));
        }
    }

    /**
     * Show API configuration.
     */
    public void showApiConfiguration() {
        logger.info("\n6. API Configuration:");

        try {
            Properties apiProps = loadProperties("api.properties");

            apiProps.forEach((key, value) ->
                logger.info("     {}: {}", key, value));

        } catch (Exception e) {
            logger.info("   Base URL: {}", getSystemPropertyOrDefault("api.base.url", "http://localhost:8080"));
            logger.info("   Timeout: {}ms", getSystemPropertyOrDefault("api.timeout", "30000"));
        }
    }

    /**
     * Demonstrate the effects of different profiles.
     */
    public void demonstrateProfileEffects() {
        logger.info("\n7. Profile Effects Demonstration:");

        String environment = getSystemPropertyOrDefault("environment", "local");

        switch (environment.toLowerCase()) {
            case "local":
                logger.info("   LOCAL environment effects:");
                logger.info("     - Debug logging enabled");
                logger.info("     - Uses H2 database");
                logger.info("     - Relaxed security");
                logger.info("     - Development features enabled");
                break;

            case "development":
                logger.info("   DEVELOPMENT environment effects:");
                logger.info("     - Debug logging enabled");
                logger.info("     - PostgreSQL database connection");
                logger.info("     - Metrics collection enabled");
                logger.info("     - Cache disabled for fresh data");
                break;

            case "testing":
                logger.info("   TESTING environment effects:");
                logger.info("     - Info level logging");
                logger.info("     - Integration tests enabled");
                logger.info("     - Strict security mode");
                logger.info("     - Shorter API timeouts");
                break;

            case "staging":
                logger.info("   STAGING environment effects:");
                logger.info("     - Warning level logging");
                logger.info("     - Production-like configuration");
                logger.info("     - Performance optimizations");
                logger.info("     - Security hardening enabled");
                break;

            case "production":
                logger.info("   PRODUCTION environment effects:");
                logger.info("     - Error level logging only");
                logger.info("     - Full optimization enabled");
                logger.info("     - Strict security policies");
                logger.info("     - Minimal timeouts");
                break;

            default:
                logger.info("   UNKNOWN environment: {}", environment);
                logger.info("     - Using default configuration");
        }
    }

    /**
     * Show runtime and system information.
     */
    public void showRuntimeInformation() {
        logger.info("\n8. Runtime Information:");

        logger.info("   Java Version: {}", System.getProperty("java.version"));
        logger.info("   Operating System: {}", System.getProperty("os.name"));

        // Show Java version specific features
        String modernJava = System.getProperty("feature.modern.java");
        if ("true".equals(modernJava)) {
            logger.info("   Modern Java Features: AVAILABLE (Java 11+)");
        }

        // Show OS-specific settings
        String scriptExt = System.getProperty("script.extension");
        if (scriptExt != null) {
            logger.info("   Script Extension: {}", scriptExt);
        }

        // Show Docker availability
        String dockerAvailable = System.getProperty("docker.available");
        if ("true".equals(dockerAvailable)) {
            logger.info("   Docker: AVAILABLE (Dockerfile detected)");
        }

        // Show CI build information
        if (System.getProperty("ci") != null) {
            logger.info("   CI Build: DETECTED");
        }
    }

    /**
     * Show additional feature flags that might be available.
     */
    private void showAdditionalFeatures() {
        Map<String, String> additionalFeatures = new TreeMap<>();

        // Collect additional feature properties
        System.getProperties().stringPropertyNames().stream()
            .filter(name -> name.startsWith("feature."))
            .forEach(name -> additionalFeatures.put(name, System.getProperty(name)));

        if (!additionalFeatures.isEmpty()) {
            logger.info("   Additional Features:");
            additionalFeatures.forEach((key, value) ->
                logger.info("     {}: {}", key, value));
        }
    }

    /**
     * Get current profile configuration.
     */
    public ProfileConfiguration getProfileConfiguration() {
        return new ProfileConfiguration(
            getSystemPropertyOrDefault("environment", "local"),
            getSystemPropertyOrDefault("log.level", "INFO"),
            getBooleanProperty("feature.debug.enabled", false),
            getBooleanProperty("feature.metrics.enabled", false),
            getBooleanProperty("feature.cache.enabled", true),
            getBooleanProperty("feature.security.strict", false)
        );
    }

    /**
     * Load properties from classpath.
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
     * Get system property with default value.
     */
    private String getSystemPropertyOrDefault(String key, String defaultValue) {
        return System.getProperty(key, defaultValue);
    }

    /**
     * Get boolean system property with default value.
     */
    private boolean getBooleanProperty(String key, boolean defaultValue) {
        String value = System.getProperty(key);
        return value != null ? Boolean.parseBoolean(value) : defaultValue;
    }

    /**
     * Get the list of likely active profiles based on system properties.
     */
    public List<String> getActiveProfiles() {
        // This is an approximation based on system properties
        // In a real application, you might use Maven's profile detection

        String environment = getSystemPropertyOrDefault("environment", "local");
        return Arrays.asList(environment);
    }

    /**
     * Check if a specific profile is likely active.
     */
    public boolean isProfileActive(String profileId) {
        // Simple check based on environment property
        String environment = getSystemPropertyOrDefault("environment", "local");

        // Check exact match or common aliases
        return profileId.equals(environment) ||
               (profileId.equals("local") && environment.equals("local")) ||
               (profileId.equals("dev") && environment.equals("development")) ||
               (profileId.equals("prod") && environment.equals("production"));
    }

    /**
     * Configuration data class.
     */
    public static class ProfileConfiguration {
        private final String environment;
        private final String logLevel;
        private final boolean debugEnabled;
        private final boolean metricsEnabled;
        private final boolean cacheEnabled;
        private final boolean securityStrict;

        public ProfileConfiguration(String environment, String logLevel,
                                   boolean debugEnabled, boolean metricsEnabled,
                                   boolean cacheEnabled, boolean securityStrict) {
            this.environment = environment;
            this.logLevel = logLevel;
            this.debugEnabled = debugEnabled;
            this.metricsEnabled = metricsEnabled;
            this.cacheEnabled = cacheEnabled;
            this.securityStrict = securityStrict;
        }

        // Getters
        public String getEnvironment() { return environment; }
        public String getLogLevel() { return logLevel; }
        public boolean isDebugEnabled() { return debugEnabled; }
        public boolean isMetricsEnabled() { return metricsEnabled; }
        public boolean isCacheEnabled() { return cacheEnabled; }
        public boolean isSecurityStrict() { return securityStrict; }

        @Override
        public String toString() {
            return String.format("ProfileConfiguration{environment='%s', logLevel='%s', " +
                               "debugEnabled=%s, metricsEnabled=%s, cacheEnabled=%s, securityStrict=%s}",
                environment, logLevel, debugEnabled, metricsEnabled, cacheEnabled, securityStrict);
        }
    }
}
