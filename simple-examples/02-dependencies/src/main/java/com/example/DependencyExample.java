package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Demonstrates Maven dependency management with various external libraries.
 * This class shows how to use different types of dependencies and their scopes.
 */
public class DependencyExample {
    
    private static final Logger logger = LoggerFactory.getLogger(DependencyExample.class);
    private final ObjectMapper objectMapper;
    private final HttpClient httpClient;
    
    public DependencyExample() {
        // Initialize Jackson ObjectMapper with JSR310 module for Java 8 time support
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        
        // Initialize HTTP client
        this.httpClient = HttpClients.createDefault();
    }
    
    /**
     * Demonstrates JSON serialization and deserialization using Jackson.
     */
    public void demonstrateJsonProcessing() {
        logger.info("Demonstrating JSON processing with Jackson");
        
        try {
            // Create a sample object
            Map<String, Object> data = new HashMap<>();
            data.put("name", "Maven Dependency Example");
            data.put("version", "1.0.0");
            data.put("timestamp", LocalDateTime.now());
            data.put("active", true);
            
            // Serialize to JSON
            String json = objectMapper.writeValueAsString(data);
            logger.info("Serialized JSON: {}", json);
            
            // Deserialize back to Map
            @SuppressWarnings("unchecked")
            Map<String, Object> deserializedData = objectMapper.readValue(json, Map.class);
            logger.info("Deserialized data: {}", deserializedData);
            
        } catch (Exception e) {
            logger.error("Error during JSON processing", e);
        }
    }
    
    /**
     * Demonstrates Apache Commons Lang utilities.
     */
    public void demonstrateCommonsLang() {
        logger.info("Demonstrating Apache Commons Lang utilities");
        
        String input = "  Maven Dependency Management  ";
        
        // String utilities
        String trimmed = StringUtils.trim(input);
        String capitalized = StringUtils.capitalize(trimmed.toLowerCase());
        boolean isEmpty = StringUtils.isEmpty(input);
        boolean isBlank = StringUtils.isBlank("   ");
        
        logger.info("Original: '{}'", input);
        logger.info("Trimmed: '{}'", trimmed);
        logger.info("Capitalized: '{}'", capitalized);
        logger.info("Is empty: {}", isEmpty);
        logger.info("Is blank (whitespace): {}", isBlank);
        
        // Array utilities
        String[] words = StringUtils.split(trimmed);
        String joined = StringUtils.join(words, " | ");
        logger.info("Words: {}", String.join(", ", words));
        logger.info("Joined: {}", joined);
    }
    
    /**
     * Demonstrates HTTP client usage (note: this requires internet connection).
     */
    public void demonstrateHttpClient() {
        logger.info("Demonstrating HTTP client");
        
        try {
            HttpGet request = new HttpGet("https://httpbin.org/json");
            HttpResponse response = httpClient.execute(request);
            
            int statusCode = response.getStatusLine().getStatusCode();
            String responseBody = EntityUtils.toString(response.getEntity());
            
            logger.info("HTTP Status: {}", statusCode);
            logger.info("Response: {}", responseBody);
            
        } catch (IOException e) {
            logger.warn("HTTP request failed (this is expected if no internet connection): {}", e.getMessage());
        }
    }
    
    /**
     * Utility method to validate input using Commons Lang.
     */
    public boolean isValidInput(String input) {
        return StringUtils.isNotBlank(input) && input.trim().length() >= 3;
    }
    
    /**
     * Converts an object to JSON string.
     */
    public String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            logger.error("Failed to serialize object to JSON", e);
            return "{}";
        }
    }
    
    public static void main(String[] args) {
        logger.info("Starting Maven Dependency Example");
        
        DependencyExample example = new DependencyExample();
        
        // Demonstrate different libraries
        example.demonstrateJsonProcessing();
        example.demonstrateCommonsLang();
        example.demonstrateHttpClient();
        
        // Test utility methods
        logger.info("Input validation test: {}", example.isValidInput("Maven"));
        logger.info("Input validation test (invalid): {}", example.isValidInput("  "));
        
        logger.info("Maven Dependency Example completed");
    }
}
