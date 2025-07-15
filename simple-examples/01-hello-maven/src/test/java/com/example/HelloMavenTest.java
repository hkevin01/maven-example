package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for HelloMaven class.
 */
class HelloMavenTest {
    
    private final HelloMaven helloMaven = new HelloMaven();
    
    @Test
    @DisplayName("Should return 'Hello, World!' when name is null")
    void testGreetWithNullName() {
        String result = helloMaven.greet(null);
        assertEquals("Hello, World!", result);
    }
    
    @Test
    @DisplayName("Should return 'Hello, World!' when name is empty")
    void testGreetWithEmptyName() {
        String result = helloMaven.greet("");
        assertEquals("Hello, World!", result);
    }
    
    @Test
    @DisplayName("Should return 'Hello, World!' when name is whitespace")
    void testGreetWithWhitespaceName() {
        String result = helloMaven.greet("   ");
        assertEquals("Hello, World!", result);
    }
    
    @Test
    @DisplayName("Should return personalized greeting when name is provided")
    void testGreetWithValidName() {
        String result = helloMaven.greet("Alice");
        assertEquals("Hello, Alice!", result);
    }
    
    @Test
    @DisplayName("Should handle names with special characters")
    void testGreetWithSpecialCharacters() {
        String result = helloMaven.greet("José");
        assertEquals("Hello, José!", result);
    }
}
