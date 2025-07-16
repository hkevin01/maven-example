package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import static org.assertj.core.api.Assertions.*;

import java.util.Properties;

/**
 * Tests for PackagingDemo demonstrating different packaging strategies
 */
class PackagingDemoTest {
    
    private PackagingDemo packagingDemo;
    
    @BeforeEach
    void setUp() {
        packagingDemo = new PackagingDemo();
    }
    
    @Nested
    @DisplayName("Packaging Type Detection Tests")
    class PackagingTypeDetectionTests {
        
        @Test
        @DisplayName("Should detect packaging type")
        void shouldDetectPackagingType() {
            String packagingType = packagingDemo.getPackagingType();
            
            assertThat(packagingType).isNotNull();
            assertThat(packagingType).isIn(
                "Standard JAR", 
                "Fat/Uber JAR", 
                "Executable JAR", 
                "WAR File", 
                "Assembly JAR", 
                "Development/IDE"
            );
        }
        
        @Test
        @DisplayName("Should get JAR location")
        void shouldGetJarLocation() {
            String location = packagingDemo.getJarLocation();
            
            assertThat(location).isNotNull();
            assertThat(location).isNotEmpty();
        }
        
        @Test
        @DisplayName("Should determine execution mode")
        void shouldDetermineExecutionMode() {
            String executionMode = packagingDemo.getExecutionMode();
            
            assertThat(executionMode).isNotNull();
            assertThat(executionMode).isIn(
                "JAR Execution",
                "Development/Maven", 
                "IDE/Test Environment"
            );
        }
    }
    
    @Nested
    @DisplayName("Classpath Analysis Tests")
    class ClasspathAnalysisTests {
        
        @Test
        @DisplayName("Should count classpath entries")
        void shouldCountClasspathEntries() {
            int count = packagingDemo.getClasspathEntryCount();
            
            assertThat(count).isGreaterThan(0);
        }
        
        @Test
        @DisplayName("Should handle classpath analysis")
        void shouldHandleClasspathAnalysis() {
            // This test verifies that classpath analysis doesn't throw exceptions
            assertThatCode(() -> {
                packagingDemo.getClasspathEntryCount();
                packagingDemo.getExecutionMode();
                packagingDemo.getJarLocation();
            }).doesNotThrowAnyException();
        }
    }
    
    @Nested
    @DisplayName("JSON Processing Tests")
    class JsonProcessingTests {
        
        @Test
        @DisplayName("Should process valid JSON")
        void shouldProcessValidJson() {
            String inputJson = "{\"name\":\"test\",\"value\":123}";
            String result = packagingDemo.processJson(inputJson);
            
            assertThat(result).isNotNull();
            assertThat(result).contains("name");
            assertThat(result).contains("test");
            assertThat(result).contains("value");
            assertThat(result).contains("123");
        }
        
        @Test
        @DisplayName("Should handle invalid JSON gracefully")
        void shouldHandleInvalidJsonGracefully() {
            String invalidJson = "{invalid json}";
            String result = packagingDemo.processJson(invalidJson);
            
            assertThat(result).isNotNull();
            assertThat(result).contains("Error:");
        }
        
        @Test
        @DisplayName("Should format JSON with pretty printing")
        void shouldFormatJsonWithPrettyPrinting() {
            String compactJson = "{\"nested\":{\"key\":\"value\"},\"array\":[1,2,3]}";
            String result = packagingDemo.processJson(compactJson);
            
            assertThat(result).isNotNull();
            assertThat(result).contains("\n"); // Should contain line breaks for pretty printing
        }
    }
    
    @Nested
    @DisplayName("Application Properties Tests")
    class ApplicationPropertiesTests {
        
        @Test
        @DisplayName("Should load application properties")
        void shouldLoadApplicationProperties() {
            Properties props = packagingDemo.loadApplicationProperties();
            
            assertThat(props).isNotNull();
        }
        
        @Test
        @DisplayName("Should handle missing properties file gracefully")
        void shouldHandleMissingPropertiesFileGracefully() {
            // This test verifies that missing properties file doesn't cause failures
            assertThatCode(() -> packagingDemo.loadApplicationProperties())
                .doesNotThrowAnyException();
        }
    }
    
    @Nested
    @DisplayName("Web Context Tests")
    class WebContextTests {
        
        @Test
        @DisplayName("Should determine web context")
        void shouldDetermineWebContext() {
            boolean isWebContext = packagingDemo.isWebContext();
            
            // In test environment, this should be false since servlet API is provided scope
            assertThat(isWebContext).isFalse();
        }
        
        @Test
        @DisplayName("Should handle web context detection gracefully")
        void shouldHandleWebContextDetectionGracefully() {
            assertThatCode(() -> packagingDemo.isWebContext())
                .doesNotThrowAnyException();
        }
    }
    
    @Nested
    @DisplayName("Packaging Info Tests")
    class PackagingInfoTests {
        
        @Test
        @DisplayName("Should create packaging info object")
        void shouldCreatePackagingInfoObject() {
            PackagingDemo.PackagingInfo info = packagingDemo.getPackagingInfo();
            
            assertThat(info).isNotNull();
            assertThat(info.getType()).isNotNull();
            assertThat(info.getLocation()).isNotNull();
            assertThat(info.getExecutionMode()).isNotNull();
            assertThat(info.getClasspathEntries()).isGreaterThan(0);
        }
        
        @Test
        @DisplayName("Should have proper toString implementation")
        void shouldHaveProperToStringImplementation() {
            PackagingDemo.PackagingInfo info = packagingDemo.getPackagingInfo();
            String toString = info.toString();
            
            assertThat(toString).isNotNull();
            assertThat(toString).contains("PackagingInfo");
            assertThat(toString).contains("type=");
            assertThat(toString).contains("location=");
            assertThat(toString).contains("executionMode=");
        }
        
        @Test
        @DisplayName("Should provide access to all properties")
        void shouldProvideAccessToAllProperties() {
            PackagingDemo.PackagingInfo info = packagingDemo.getPackagingInfo();
            
            // Test all getters work
            assertThatCode(() -> {
                info.getType();
                info.getLocation();
                info.getExecutionMode();
                info.getClasspathEntries();
                info.isWebContext();
            }).doesNotThrowAnyException();
        }
    }
    
    @Test
    @DisplayName("Should run main method without errors")
    void shouldRunMainMethodWithoutErrors() {
        // This test verifies that the main method executes without throwing exceptions
        assertThatCode(() -> PackagingDemo.main(new String[]{}))
            .doesNotThrowAnyException();
    }
    
    @Test
    @DisplayName("Should handle different runtime environments")
    void shouldHandleDifferentRuntimeEnvironments() {
        // Test that the demo works in different environments
        assertThatCode(() -> {
            packagingDemo.displayApplicationInfo();
            packagingDemo.demonstratePackagingTypes();
            packagingDemo.checkDependencies();
            packagingDemo.analyzeRuntimeEnvironment();
            packagingDemo.showManifestInformation();
        }).doesNotThrowAnyException();
    }
}
