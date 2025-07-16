package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import static org.assertj.core.api.Assertions.*;

import java.util.Properties;
import java.io.InputStream;

/**
 * Tests for ResourceDemo demonstrating Maven resource handling
 */
class ResourceDemoTest {
    
    private ResourceDemo resourceDemo;
    
    @BeforeEach
    void setUp() {
        resourceDemo = new ResourceDemo();
    }
    
    @Nested
    @DisplayName("Filtered Properties Tests")
    class FilteredPropertiesTests {
        
        @Test
        @DisplayName("Should load application properties with filtered values")
        void shouldLoadApplicationPropertiesWithFilteredValues() throws Exception {
            Properties props = resourceDemo.loadProperties("application.properties");
            
            assertThat(props).isNotNull();
            assertThat(props.getProperty("app.name")).isEqualTo("Resource Handling Example");
            assertThat(props.getProperty("app.version")).isEqualTo("1.0.0");
            assertThat(props.getProperty("app.description")).contains("Maven resource processing");
            assertThat(props.getProperty("app.author")).isEqualTo("Maven Learning Tool");
        }
        
        @Test
        @DisplayName("Should have build timestamp in properties")
        void shouldHaveBuildTimestampInProperties() throws Exception {
            Properties props = resourceDemo.loadProperties("application.properties");
            
            // Build timestamp should be present (filtered from Maven)
            assertThat(props.getProperty("build.timestamp")).isNotNull();
        }
        
        @Test
        @DisplayName("Should load project information from Maven properties")
        void shouldLoadProjectInformationFromMavenProperties() throws Exception {
            Properties props = resourceDemo.loadProperties("application.properties");
            
            assertThat(props.getProperty("project.groupId")).isEqualTo("com.example");
            assertThat(props.getProperty("project.artifactId")).isEqualTo("resource-handling");
        }
    }
    
    @Nested
    @DisplayName("Configuration Tests")
    class ConfigurationTests {
        
        @Test
        @DisplayName("Should load database configuration with filtered values")
        void shouldLoadDatabaseConfigurationWithFilteredValues() throws Exception {
            Properties props = resourceDemo.loadProperties("database.properties");
            
            assertThat(props).isNotNull();
            assertThat(props.getProperty("database.url")).contains("jdbc:h2:mem:testdb");
            assertThat(props.getProperty("database.username")).isEqualTo("sa");
            assertThat(props.getProperty("database.pool.size")).isEqualTo("10");
        }
        
        @Test
        @DisplayName("Should have API timeout from Maven properties")
        void shouldHaveApiTimeoutFromMavenProperties() throws Exception {
            Properties props = resourceDemo.loadProperties("database.properties");
            
            // API timeout should be filtered from Maven properties
            assertThat(props.getProperty("api.timeout")).isNotNull();
            assertThat(Integer.parseInt(props.getProperty("api.timeout"))).isGreaterThan(0);
        }
    }
    
    @Nested
    @DisplayName("Resource Access Tests")
    class ResourceAccessTests {
        
        @Test
        @DisplayName("Should access text file from classpath")
        void shouldAccessTextFileFromClasspath() {
            InputStream stream = getClass().getClassLoader().getResourceAsStream("data/sample.txt");
            
            assertThat(stream).isNotNull();
        }
        
        @Test
        @DisplayName("Should load messages properties")
        void shouldLoadMessagesProperties() throws Exception {
            Properties props = resourceDemo.loadProperties("messages.properties");
            
            assertThat(props).isNotNull();
            assertThat(props.getProperty("welcome.message")).contains("Welcome");
            assertThat(props.getProperty("error.general")).isNotNull();
            assertThat(props.size()).isGreaterThan(5);
        }
        
        @Test
        @DisplayName("Should find logback configuration in config folder")
        void shouldFindLogbackConfigurationInConfigFolder() {
            java.net.URL resource = getClass().getResource("/config/logback.xml");
            
            assertThat(resource).isNotNull();
        }
    }
    
    @Nested
    @DisplayName("Application Info Tests")
    class ApplicationInfoTests {
        
        @Test
        @DisplayName("Should create application info from filtered properties")
        void shouldCreateApplicationInfoFromFilteredProperties() {
            ResourceDemo.ApplicationInfo appInfo = resourceDemo.getApplicationInfo();
            
            assertThat(appInfo).isNotNull();
            assertThat(appInfo.getName()).isEqualTo("Resource Handling Example");
            assertThat(appInfo.getVersion()).isEqualTo("1.0.0");
            assertThat(appInfo.getDescription()).contains("Maven resource processing");
            assertThat(appInfo.getAuthor()).isEqualTo("Maven Learning Tool");
        }
        
        @Test
        @DisplayName("Should handle toString correctly")
        void shouldHandleToStringCorrectly() {
            ResourceDemo.ApplicationInfo appInfo = resourceDemo.getApplicationInfo();
            String toString = appInfo.toString();
            
            assertThat(toString).contains("ApplicationInfo");
            assertThat(toString).contains("Resource Handling Example");
            assertThat(toString).contains("1.0.0");
        }
    }
    
    @Nested
    @DisplayName("Environment Configuration Tests")
    class EnvironmentConfigurationTests {
        
        @Test
        @DisplayName("Should load environment properties with log level")
        void shouldLoadEnvironmentPropertiesWithLogLevel() throws Exception {
            Properties props = resourceDemo.loadProperties("environment.properties");
            
            assertThat(props).isNotNull();
            assertThat(props.getProperty("log.level")).isNotNull();
            assertThat(props.getProperty("debug.enabled")).isEqualTo("false");
        }
        
        @Test
        @DisplayName("Should have monitoring settings")
        void shouldHaveMonitoringSettings() throws Exception {
            Properties props = resourceDemo.loadProperties("environment.properties");
            
            assertThat(props.getProperty("metrics.enabled")).isEqualTo("true");
            assertThat(props.getProperty("health.check.interval")).isEqualTo("60");
            assertThat(props.getProperty("performance.tracking")).isEqualTo("enabled");
        }
    }
    
    @Test
    @DisplayName("Should handle missing properties gracefully")
    void shouldHandleMissingPropertiesGracefully() {
        assertThatThrownBy(() -> resourceDemo.loadProperties("nonexistent.properties"))
            .isInstanceOf(RuntimeException.class)
            .hasMessageContaining("Could not find nonexistent.properties");
    }
    
    @Test
    @DisplayName("Should run main method without errors")
    void shouldRunMainMethodWithoutErrors() {
        // This test verifies that the main method executes without throwing exceptions
        assertThatCode(() -> ResourceDemo.main(new String[]{}))
            .doesNotThrowAnyException();
    }
}
