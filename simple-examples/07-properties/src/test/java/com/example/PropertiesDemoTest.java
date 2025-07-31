package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import static org.assertj.core.api.Assertions.*;

import java.util.Properties;

/**
 * Tests for PropertiesDemo demonstrating Maven properties functionality
 */
class PropertiesDemoTest {

    private PropertiesDemo propertiesDemo;

    @BeforeEach
    void setUp() {
        propertiesDemo = new PropertiesDemo();
    }

    @Nested
    @DisplayName("Property Loading Tests")
    class PropertyLoadingTests {

        @Test
        @DisplayName("Should load application properties with filtered values")
        void shouldLoadApplicationPropertiesWithFilteredValues() throws Exception {
            Properties props = propertiesDemo.loadProperties("application.properties");

            assertThat(props).isNotNull();
            assertThat(props.getProperty("app.name")).isEqualTo("Maven Properties Demo");
            assertThat(props.getProperty("app.version")).isEqualTo("1.0.0");
            assertThat(props.getProperty("project.groupId")).isEqualTo("com.example");
            assertThat(props.getProperty("project.artifactId")).isEqualTo("maven-properties");
        }

        @Test
        @DisplayName("Should load database configuration with filtered values")
        void shouldLoadDatabaseConfigurationWithFilteredValues() throws Exception {
            Properties props = propertiesDemo.loadProperties("config/database.properties");

            assertThat(props).isNotNull();
            assertThat(props.getProperty("database.host")).isNotNull();
            assertThat(props.getProperty("database.port")).isNotNull();
            assertThat(props.getProperty("database.url")).contains("jdbc:postgresql");
            assertThat(props.getProperty("database.environment")).isNotNull();
        }

        @Test
        @DisplayName("Should load build information properties")
        void shouldLoadBuildInformationProperties() throws Exception {
            Properties props = propertiesDemo.loadProperties("build-info.properties");

            assertThat(props).isNotNull();
            assertThat(props.getProperty("build.system")).isEqualTo("Maven");
            assertThat(props.getProperty("project.group")).isEqualTo("com.example");
            assertThat(props.getProperty("compiler.source")).isEqualTo("11");
            assertThat(props.getProperty("compiler.target")).isEqualTo("11");
        }

        @Test
        @DisplayName("Should handle missing properties file")
        void shouldHandleMissingPropertiesFile() {
            assertThatThrownBy(() -> propertiesDemo.loadProperties("nonexistent.properties"))
                .isInstanceOf(Exception.class)
                .hasMessageContaining("Could not find nonexistent.properties");
        }
    }

    @Nested
    @DisplayName("System Properties Tests")
    class SystemPropertiesTests {

        @Test
        @DisplayName("Should access system properties passed from Maven")
        void shouldAccessSystemPropertiesPassedFromMaven() {
            PropertiesDemo.PropertyInfo info = propertiesDemo.getPropertyInfo();

            assertThat(info).isNotNull();
            assertThat(info.getAppName()).isNotNull();
            assertThat(info.getEnvironment()).isNotNull();
            assertThat(info.getLogLevel()).isNotNull();
        }

        @Test
        @DisplayName("Should handle missing system properties gracefully")
        void shouldHandleMissingSystemPropertiesGracefully() {
            // Clear a system property temporarily
            String originalValue = System.getProperty("app.name");
            System.clearProperty("app.name");

            try {
                PropertiesDemo.PropertyInfo info = propertiesDemo.getPropertyInfo();
                assertThat(info.getAppName()).isEqualTo("Unknown");
            } finally {
                // Restore original value
                if (originalValue != null) {
                    System.setProperty("app.name", originalValue);
                }
            }
        }
    }

    @Nested
    @DisplayName("Property Info Tests")
    class PropertyInfoTests {

        @Test
        @DisplayName("Should create property info object")
        void shouldCreatePropertyInfoObject() {
            PropertiesDemo.PropertyInfo info = propertiesDemo.getPropertyInfo();

            assertThat(info).isNotNull();
            assertThat(info.getAppName()).isNotNull();
            assertThat(info.getEnvironment()).isNotNull();
            assertThat(info.getLogLevel()).isNotNull();
            assertThat(info.getBuildTimestamp()).isNotNull();
        }

        @Test
        @DisplayName("Should have proper toString implementation")
        void shouldHaveProperToStringImplementation() {
            PropertiesDemo.PropertyInfo info = propertiesDemo.getPropertyInfo();
            String toString = info.toString();

            assertThat(toString).isNotNull();
            assertThat(toString).contains("PropertyInfo");
            assertThat(toString).contains("appName=");
            assertThat(toString).contains("environment=");
            assertThat(toString).contains("logLevel=");
            assertThat(toString).contains("buildTimestamp=");
        }
    }

    @Nested
    @DisplayName("Alternative Delimiter Tests")
    class AlternativeDelimiterTests {

        @Test
        @DisplayName("Should support alternative delimiters in properties")
        void shouldSupportAlternativeDelimitersInProperties() throws Exception {
            Properties props = propertiesDemo.loadProperties("application.properties");

            // Test that alternative delimiters (@*@) are also processed
            assertThat(props.getProperty("alternative.app.name"))
                .isEqualTo("Maven Properties Demo");
            assertThat(props.getProperty("alternative.version"))
                .isEqualTo("1.0.0");
        }
    }

    @Nested
    @DisplayName("Feature Flag Tests")
    class FeatureFlagTests {

        @Test
        @DisplayName("Should load feature flag properties")
        void shouldLoadFeatureFlagProperties() throws Exception {
            Properties props = propertiesDemo.loadProperties("application.properties");

            assertThat(props.getProperty("feature.debug.enabled")).isNotNull();
            assertThat(props.getProperty("feature.metrics.enabled")).isNotNull();
            assertThat(props.getProperty("feature.experimental.enabled")).isNotNull();
        }
    }

    @Test
    @DisplayName("Should run main method without errors")
    void shouldRunMainMethodWithoutErrors() {
        // This test verifies that the main method executes without throwing exceptions
        assertThatCode(() -> PropertiesDemo.main(new String[]{}))
            .doesNotThrowAnyException();
    }
}
