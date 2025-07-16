package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import static org.assertj.core.api.Assertions.*;

/**
 * Unit tests for PluginDemo class.
 * These tests demonstrate how Maven Surefire plugin runs tests
 * and how JaCoCo plugin measures code coverage.
 */
class PluginDemoTest {
    
    private PluginDemo pluginDemo;
    
    @BeforeEach
    void setUp() {
        pluginDemo = new PluginDemo();
    }
    
    @Test
    @DisplayName("Should process null arguments correctly")
    void testProcessArgumentsWithNull() {
        String result = pluginDemo.processArguments(null);
        assertThat(result).isEqualTo("No arguments provided");
    }
    
    @Test
    @DisplayName("Should process empty arguments correctly")
    void testProcessArgumentsWithEmpty() {
        String result = pluginDemo.processArguments(new String[]{});
        assertThat(result).isEqualTo("No arguments provided");
    }
    
    @Test
    @DisplayName("Should process single argument correctly")
    void testProcessArgumentsWithSingle() {
        String[] args = {"maven"};
        String result = pluginDemo.processArguments(args);
        assertThat(result).isEqualTo("Processing arguments: maven");
    }
    
    @Test
    @DisplayName("Should process multiple arguments correctly")
    void testProcessArgumentsWithMultiple() {
        String[] args = {"maven", "plugins", "demo"};
        String result = pluginDemo.processArguments(args);
        assertThat(result).isEqualTo("Processing arguments: maven, plugins, demo");
    }
    
    @Test
    @DisplayName("Should calculate factorial of 0 correctly")
    void testFactorialOfZero() {
        long result = pluginDemo.factorial(0);
        assertThat(result).isEqualTo(1);
    }
    
    @Test
    @DisplayName("Should calculate factorial of 1 correctly")
    void testFactorialOfOne() {
        long result = pluginDemo.factorial(1);
        assertThat(result).isEqualTo(1);
    }
    
    @Test
    @DisplayName("Should calculate factorial of positive numbers correctly")
    void testFactorialOfPositiveNumbers() {
        assertThat(pluginDemo.factorial(3)).isEqualTo(6);
        assertThat(pluginDemo.factorial(4)).isEqualTo(24);
        assertThat(pluginDemo.factorial(5)).isEqualTo(120);
    }
    
    @Test
    @DisplayName("Should throw exception for negative factorial")
    void testFactorialOfNegative() {
        assertThatThrownBy(() -> pluginDemo.factorial(-1))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Factorial is not defined for negative numbers");
    }
    
    @Test
    @DisplayName("Should return false for null palindrome check")
    void testPalindromeWithNull() {
        boolean result = pluginDemo.isPalindrome(null);
        assertThat(result).isFalse();
    }
    
    @Test
    @DisplayName("Should identify simple palindromes correctly")
    void testSimplePalindromes() {
        assertThat(pluginDemo.isPalindrome("racecar")).isTrue();
        assertThat(pluginDemo.isPalindrome("level")).isTrue();
        assertThat(pluginDemo.isPalindrome("noon")).isTrue();
    }
    
    @Test
    @DisplayName("Should identify non-palindromes correctly")
    void testNonPalindromes() {
        assertThat(pluginDemo.isPalindrome("hello")).isFalse();
        assertThat(pluginDemo.isPalindrome("maven")).isFalse();
        assertThat(pluginDemo.isPalindrome("plugin")).isFalse();
    }
    
    @Test
    @DisplayName("Should handle palindromes with spaces and punctuation")
    void testComplexPalindromes() {
        assertThat(pluginDemo.isPalindrome("A man a plan a canal Panama")).isTrue();
        assertThat(pluginDemo.isPalindrome("race a car")).isFalse();
        assertThat(pluginDemo.isPalindrome("Was it a car or a cat I saw?")).isTrue();
    }
    
    @Test
    @DisplayName("Should handle case insensitive palindromes")
    void testCaseInsensitivePalindromes() {
        assertThat(pluginDemo.isPalindrome("RaceCar")).isTrue();
        assertThat(pluginDemo.isPalindrome("Level")).isTrue();
        assertThat(pluginDemo.isPalindrome("NOON")).isTrue();
    }
    
    @Test
    @DisplayName("Should handle empty string palindrome")
    void testEmptyStringPalindrome() {
        assertThat(pluginDemo.isPalindrome("")).isTrue();
    }
    
    @Test
    @DisplayName("Should handle single character palindrome")
    void testSingleCharacterPalindrome() {
        assertThat(pluginDemo.isPalindrome("a")).isTrue();
        assertThat(pluginDemo.isPalindrome("Z")).isTrue();
    }
    
    @Test
    @DisplayName("Should handle numeric palindromes")
    void testNumericPalindromes() {
        assertThat(pluginDemo.isPalindrome("12321")).isTrue();
        assertThat(pluginDemo.isPalindrome("12345")).isFalse();
    }
}
