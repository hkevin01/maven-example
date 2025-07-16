# Maven Resource Handling Example

This example demonstrates Maven's powerful resource processing capabilities, including property filtering, resource copying, and environment-specific configuration.

## What You'll Learn

- **Resource Filtering**: How Maven can substitute properties in resource files during build
- **Property Management**: Using Maven properties to configure application behavior
- **Profile-Specific Resources**: Different configurations for development, testing, and production
- **Resource Access**: Various ways to load resources from the classpath at runtime
- **Build-Time Configuration**: How to inject build information into your application

## Key Features

### 1. Property Filtering
Maven automatically replaces placeholders like `${app.name}` in resource files with actual values from:
- POM properties
- System properties  
- Maven built-in properties
- Profile-specific properties

### 2. Resource Processing
- **Filtered Resources**: Properties, XML, JSON files with placeholder substitution
- **Static Resources**: Binary files and text files copied without modification
- **Custom Resource Directories**: Additional resource locations and processing rules

### 3. Environment Profiles
Three pre-configured profiles demonstrate different environments:
- **dev**: Development settings with debug logging
- **test**: Testing configuration with in-memory database
- **prod**: Production settings with external database

## Project Structure

```
05-resources/
├── pom.xml                              # Maven configuration with resource processing
├── src/main/java/com/example/
│   └── ResourceDemo.java                # Main demonstration class
├── src/main/resources/
│   ├── application.properties           # Filtered application config
│   ├── database.properties             # Filtered database config  
│   ├── environment.properties          # Environment-specific settings
│   ├── messages.properties             # Static message resources
│   ├── config/
│   │   ├── logback.xml                 # Logging configuration
│   │   └── app-config.json             # JSON configuration file
│   └── data/
│       └── sample.txt                  # Sample static resource
└── src/test/java/com/example/
    └── ResourceDemoTest.java           # Comprehensive tests
```

## Resource Files

### Filtered Resources (Property Substitution)
- `application.properties` - Application metadata with build information
- `database.properties` - Database configuration with environment-specific values
- `environment.properties` - Runtime settings that vary by profile
- `config/app-config.json` - JSON configuration with filtered values
- `config/logback.xml` - Logging configuration with variable log levels

### Static Resources (No Filtering)
- `messages.properties` - Localization and user messages
- `data/sample.txt` - Sample data file for testing resource access

## Running the Example

### Basic Execution
```bash
# Compile and run with default profile
mvn compile exec:java

# Run tests
mvn test
```

### Profile-Specific Execution
```bash
# Development profile (debug logging, file database)
mvn compile exec:java -Pdev

# Testing profile (debug logging, in-memory database)  
mvn compile exec:java -Ptest

# Production profile (warn logging, external database)
mvn compile exec:java -Pprod
```

### Resource Processing
```bash
# See filtered resources in target/classes
mvn process-resources

# Copy additional resources to target/extra-resources
mvn validate

# Clean and rebuild to see fresh filtering
mvn clean compile
```

## What Happens During Build

1. **Property Resolution**: Maven resolves all `${property}` placeholders in filtered resources
2. **Resource Copying**: Files are copied to `target/classes` with appropriate filtering
3. **Profile Activation**: Profile-specific properties override default values
4. **Additional Processing**: Custom resource processing rules are applied

## Key Maven Concepts

### Resource Filtering Configuration
```xml
<resources>
    <resource>
        <directory>src/main/resources</directory>
        <filtering>true</filtering>
        <includes>
            <include>**/*.properties</include>
            <include>**/*.xml</include>
            <include>**/*.json</include>
        </includes>
    </resource>
</resources>
```

### Property Definition
```xml
<properties>
    <app.name>${project.name}</app.name>
    <database.url>jdbc:h2:mem:testdb</database.url>
    <log.level>INFO</log.level>
</properties>
```

### Profile-Specific Overrides
```xml
<profile>
    <id>dev</id>
    <properties>
        <log.level>DEBUG</log.level>
        <database.url>jdbc:h2:file:./target/devdb</database.url>
    </properties>
</profile>
```

## Output Examples

When you run the example, you'll see:

```
=== Maven Resource Handling Demo ===

1. Filtered Properties Demonstration:
=====================================
Application Information (filtered by Maven):
- Name: Resource Handling Example
- Version: 1.0.0
- Description: Demonstrates Maven resource processing, filtering, and management
- Author: Maven Learning Tool
- Build Timestamp: 2024-01-15T10:30:45Z

2. Configuration Files Demonstration:
====================================
Database Configuration:
- URL: jdbc:h2:mem:testdb
- Username: sa
- Pool Size: 10
- Timeout: 5000

3. Resource Access Methods:
===========================
Via ClassLoader: ✓ Found
Via Class.getResource(): ✓ Found at file:/path/to/target/classes/config/logback.xml
Messages file loaded with 12 entries
- Welcome message: Welcome to the Maven Resource Handling Example!
- Error message: An unexpected error occurred

4. Environment-Specific Configuration:
=====================================
Current Environment Settings:
- Log Level: INFO
- Environment: default
- Debug Mode: false
- Feature Flags: disabled
- Active Profile: default
```

## Advanced Features

### Custom Resource Processing
The POM includes advanced resource processing features:
- **Non-filtered file extensions**: Binary files are copied without filtering
- **Additional resource copying**: Extra resources are copied to custom locations
- **Resource validation**: Ensures all required resources are present

### Runtime Resource Access
The demo shows multiple ways to access resources:
- `ClassLoader.getResourceAsStream()` - Standard approach
- `Class.getResource()` - URL-based access  
- `Properties.load()` - Configuration file loading
- Custom resource enumeration and listing

## Best Practices Demonstrated

1. **Separate Filtered and Static Resources**: Use filtering only when needed
2. **Profile-Specific Configuration**: Environment-specific settings without code changes
3. **Build Information Injection**: Include build metadata in applications
4. **Resource Organization**: Logical folder structure for different resource types
5. **Error Handling**: Graceful handling of missing or invalid resources

## Next Steps

After mastering resource handling, explore:
- **06-packaging**: Different packaging types (JAR, WAR, executable)
- **07-properties**: Advanced Maven property usage and inheritance
- **08-profiles**: Complex profile configurations and activation
- **Advanced Examples**: Multi-module projects with shared resources
