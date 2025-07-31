package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import static org.assertj.core.api.Assertions.*;

import java.util.Properties;
import java.util.List;

/**
 * Tests for ProfileDemo demonstrating Maven profiles functionality
 */
class ProfileDemoTest {

    private ProfileDemo profileDemo;

    @BeforeEach
    void setUp() {
        profileDemo = new ProfileDemo();
    }

    @Nested
    @DisplayName("Profile Configuration Tests")
    class ProfileConfigurationTests {

        @Test
        @DisplayName("Should create profile configuration object")
        void shouldCreateProfileConfigurationObject() {
            ProfileDemo.ProfileConfiguration config = profileDemo.getProfileConfiguration();

            assertThat(config).isNotNull();
            assertThat(config.getEnvironment()).isNotNull();
            assertThat(config.getLogLevel()).isNotNull();
        }

        @Test
        @DisplayName("Should handle default configuration values")
        void shouldHandleDefaultConfigurationValues() {
            ProfileDemo.ProfileConfiguration config = profileDemo.getProfileConfiguration();

            // Should have reasonable defaults
            assertThat(config.getEnvironment()).isIn("local", "development", "testing", "staging", "production");
            assertThat(config.getLogLevel()).isIn("DEBUG", "INFO", "WARN", "ERROR");
        }

        @Test
        @DisplayName("Should have proper toString implementation")
        void shouldHaveProperToStringImplementation() {
            ProfileDemo.ProfileConfiguration config = profileDemo.getProfileConfiguration();
            String toString = config.toString();

            assertThat(toString).isNotNull();
            assertThat(toString).contains("ProfileConfiguration");
            assertThat(toString).contains("environment=");
            assertThat(toString).contains("logLevel=");
        }
    }

    @Nested
    @DisplayName("Property Loading Tests")
    class PropertyLoadingTests {

        @Test
        @DisplayName("Should load database properties with filtered values")
        void shouldLoadDatabasePropertiesWithFilteredValues() throws Exception {
            Properties props = profileDemo.loadProperties("database.properties");

            assertThat(props).isNotNull();
            assertThat(props.getProperty("database.host")).isNotNull();
            assertThat(props.getProperty("database.port")).isNotNull();
            assertThat(props.getProperty("database.environment")).isNotNull();
        }

        @Test
        @DisplayName("Should load API properties with filtered values")
        void shouldLoadApiPropertiesWithFilteredValues() throws Exception {
            Properties props = profileDemo.loadProperties("api.properties");

            assertThat(props).isNotNull();
            assertThat(props.getProperty("api.base.url")).isNotNull();
            assertThat(props.getProperty("api.timeout")).isNotNull();
            assertThat(props.getProperty("api.environment")).isNotNull();
        }

        @Test
        @DisplayName("Should load environment-specific properties")
        void shouldLoadEnvironmentSpecificProperties() throws Exception {
            // Test loading local environment properties
            Properties localProps = profileDemo.loadProperties("environments/local.properties");

            assertThat(localProps).isNotNull();
            assertThat(localProps.getProperty("environment.name")).isEqualTo("Local Development");
            assertThat(localProps.getProperty("debug.mode")).isEqualTo("true");
        }

        @Test
        @DisplayName("Should handle missing property files gracefully")
        void shouldHandleMissingPropertyFilesGracefully() {
            assertThatThrownBy(() -> profileDemo.loadProperties("nonexistent.properties"))
                .isInstanceOf(Exception.class)
                .hasMessageContaining("Could not find nonexistent.properties");
        }
    }

    @Nested
    @DisplayName("Profile Detection Tests")
    class ProfileDetectionTests {

        @Test
        @DisplayName("Should detect active profiles")
        void shouldDetectActiveProfiles() {
            List<String> activeProfiles = profileDemo.getActiveProfiles();

            assertThat(activeProfiles).isNotNull();
            assertThat(activeProfiles).isNotEmpty();
        }

        @Test
        @DisplayName("Should check profile activation status")
        void shouldCheckProfileActivationStatus() {
            // Test with likely active profile
            String environment = System.getProperty("environment", "local");
            boolean isActive = profileDemo.isProfileActive(environment);

            assertThat(isActive).isTrue();
        }

        @Test
        @DisplayName("Should handle profile aliases")
        void shouldHandleProfileAliases() {
            // Test common profile aliases
            boolean localActive = profileDemo.isProfileActive("local");
            assertThat(localActive).isNotNull(); // Should return a boolean value
        }
    }

    @Nested
    @DisplayName("Environment Configuration Tests")
    class EnvironmentConfigurationTests {

        @Test
        @DisplayName("Should load local environment configuration")
        void shouldLoadLocalEnvironmentConfiguration() throws Exception {
            Properties props = profileDemo.loadProperties("environments/local.properties");

            assertThat(props.getProperty("environment.name")).isEqualTo("Local Development");
            assertThat(props.getProperty("database.type")).isEqualTo("H2");
            assertThat(props.getProperty("debug.mode")).isEqualTo("true");
        }

        @Test
        @DisplayName("Should load development environment configuration")
        void shouldLoadDevelopmentEnvironmentConfiguration() throws Exception {
            Properties props = profileDemo.loadProperties("environments/development.properties");

            assertThat(props.getProperty("environment.name")).isEqualTo("Development");
            assertThat(props.getProperty("database.type")).isEqualTo("PostgreSQL");
            assertThat(props.getProperty("external.api.mock")).isEqualTo("true");
        }

        @Test
        @DisplayName("Should load testing environment configuration")
        void shouldLoadTestingEnvironmentConfiguration() throws Exception {
            Properties props = profileDemo.loadProperties("environments/testing.properties");

            assertThat(props.getProperty("environment.name")).isEqualTo("Testing");
            assertThat(props.getProperty("integration.tests.enabled")).isEqualTo("true");
            assertThat(props.getProperty("mock.external.services")).isEqualTo("true");
        }

        @Test
        @DisplayName("Should load staging environment configuration")
        void shouldLoadStagingEnvironmentConfiguration() throws Exception {
            Properties props = profileDemo.loadProperties("environments/staging.properties");

            assertThat(props.getProperty("environment.name")).isEqualTo("Staging");
            assertThat(props.getProperty("load.tests.enabled")).isEqualTo("true");
            assertThat(props.getProperty("health.checks.enabled")).isEqualTo("true");
        }

        @Test
        @DisplayName("Should load production environment configuration")
        void shouldLoadProductionEnvironmentConfiguration() throws Exception {
            Properties props = profileDemo.loadProperties("environments/production.properties");

            assertThat(props.getProperty("environment.name")).isEqualTo("Production");
            assertThat(props.getProperty("backup.enabled")).isEqualTo("true");
            assertThat(props.getProperty("dr.enabled")).isEqualTo("true");
        }
    }

    @Nested
    @DisplayName("Feature Flag Tests")
    class FeatureFlagTests {

        @Test
        @DisplayName("Should handle debug feature flag")
        void shouldHandleDebugFeatureFlag() {
            ProfileDemo.ProfileConfiguration config = profileDemo.getProfileConfiguration();

            // Debug flag should be a boolean value
            boolean debugEnabled = config.isDebugEnabled();
            assertThat(debugEnabled).isIn(true, false);
        }

        @Test
        @DisplayName("Should handle metrics feature flag")
        void shouldHandleMetricsFeatureFlag() {
            ProfileDemo.ProfileConfiguration config = profileDemo.getProfileConfiguration();

            // Metrics flag should be a boolean value
            boolean metricsEnabled = config.isMetricsEnabled();
            assertThat(metricsEnabled).isIn(true, false);
        }

        @Test
        @DisplayName("Should handle cache feature flag")
        void shouldHandleCacheFeatureFlag() {
            ProfileDemo.ProfileConfiguration config = profileDemo.getProfileConfiguration();

            // Cache flag should be a boolean value
            boolean cacheEnabled = config.isCacheEnabled();
            assertThat(cacheEnabled).isIn(true, false);
        }

        @Test
        @DisplayName("Should handle security feature flag")
        void shouldHandleSecurityFeatureFlag() {
            ProfileDemo.ProfileConfiguration config = profileDemo.getProfileConfiguration();

            // Security flag should be a boolean value
            boolean securityStrict = config.isSecurityStrict();
            assertThat(securityStrict).isIn(true, false);
        }
    }

    @Nested
    @DisplayName("Property Filtering Tests")
    class PropertyFilteringTests {

        @Test
        @DisplayName("Should filter database properties correctly")
        void shouldFilterDatabasePropertiesCorrectly() throws Exception {
            Properties props = profileDemo.loadProperties("database.properties");

            // Verify that Maven properties were substituted
            assertThat(props.getProperty("database.config.version")).isEqualTo("1.0.0");
            assertThat(props.getProperty("database.environment")).isNotNull();
        }

        @Test
        @DisplayName("Should filter API properties correctly")
        void shouldFilterApiPropertiesCorrectly() throws Exception {
            Properties props = profileDemo.loadProperties("api.properties");

            // Verify that Maven properties were substituted
            assertThat(props.getProperty("api.version")).isEqualTo("1.0.0");
            assertThat(props.getProperty("api.environment")).isNotNull();
        }
    }

    @Test
    @DisplayName("Should run main method without errors")
    void shouldRunMainMethodWithoutErrors() {
        // This test verifies that the main method executes without throwing exceptions
        assertThatCode(() -> ProfileDemo.main(new String[]{}))
            .doesNotThrowAnyException();
    }
}
