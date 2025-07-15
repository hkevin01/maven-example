package com.example;

/**
 * A simple Hello World application demonstrating basic Maven project structure.
 */
public class HelloMaven {
    
    /**
     * Returns a greeting message.
     * 
     * @param name the name to greet
     * @return a greeting message
     */
    public String greet(String name) {
        if (name == null || name.trim().isEmpty()) {
            return "Hello, World!";
        }
        return "Hello, " + name + "!";
    }
    
    /**
     * Main method to run the application.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        HelloMaven app = new HelloMaven();
        
        if (args.length > 0) {
            System.out.println(app.greet(args[0]));
        } else {
            System.out.println(app.greet(null));
        }
        
        // Demonstrate some basic functionality
        System.out.println(app.greet("Maven"));
        System.out.println(app.greet("Developer"));
    }
}
