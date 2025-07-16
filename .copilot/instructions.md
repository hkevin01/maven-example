# GitHub Copilot Instructions for Maven Learning Tool

## Project Context
This is a Maven learning tool repository designed to teach Apache Maven concepts through practical examples ranging from simple to advanced use cases.

## Code Style and Conventions
- **Language**: Java 11+
- **Build Tool**: Apache Maven 3.6+
- **Testing**: JUnit 5, Mockito, AssertJ
- **Code Style**: Google Java Style Guide
- **Documentation**: Comprehensive README files for each example

## Project Structure
```
maven-example/
├── simple-examples/          # Basic Maven concepts
├── advanced-examples/        # Complex Maven scenarios  
├── docs/                     # Project documentation
├── .github/                  # GitHub workflows and templates
└── .copilot/                # GitHub Copilot configuration
```

## When suggesting code:

### Maven POM Files
- Always include clear comments explaining configuration choices
- Use properties for version management
- Pin dependency versions (avoid LATEST or version ranges)
- Group dependencies logically (compile, runtime, test, provided)
- Include plugin configuration with explanatory comments

Example POM structure:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0">
    <modelVersion>4.0.0</modelVersion>
    
    <!-- Project coordinates -->
    <groupId>com.example</groupId>
    <artifactId>example-name</artifactId>
    <version>1.0.0</version>
    <packaging>jar</packaging>
    
    <!-- Project metadata -->
    <name>Descriptive Name</name>
    <description>Clear description of what this example demonstrates</description>
    
    <!-- Build properties -->
    <properties>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>
</project>
```

### Java Code
- Include comprehensive Javadoc comments
- Use descriptive variable and method names
- Follow single responsibility principle
- Include unit tests for all public methods
- Use modern Java features appropriately (streams, lambdas, etc.)

### Documentation
- Every example must have a README.md with:
  - Learning objectives
  - Project structure explanation
  - Maven concepts demonstrated
  - Step-by-step instructions
  - Expected output
  - Troubleshooting section
  - Links to relevant resources

### Testing
- Write comprehensive unit tests
- Use descriptive test method names
- Include both positive and negative test cases
- Use appropriate test annotations (@DisplayName, @Test, etc.)
- Aim for meaningful test coverage

## Example Categories

### Simple Examples
Focus on one Maven concept per example:
- Basic project structure
- Dependency management
- Plugin configuration
- Resource handling
- Testing strategies
- Packaging options

### Advanced Examples
Demonstrate complex real-world scenarios:
- Multi-module projects
- Custom plugin development
- Integration testing
- CI/CD integration
- Performance optimization
- Security practices

## Common Maven Concepts to Emphasize
1. **Project Object Model (POM)**: Structure, inheritance, aggregation
2. **Dependency Management**: Scopes, exclusions, conflicts resolution
3. **Build Lifecycle**: Phases, goals, plugin binding
4. **Plugin Configuration**: Built-in plugins, custom configuration
5. **Profiles**: Environment-specific builds
6. **Multi-module Projects**: Parent POM, module dependencies
7. **Repository Management**: Local, central, custom repositories

## Code Quality Standards
- All examples must compile and run successfully
- Include error handling where appropriate
- Use logging instead of System.out.println for non-example output
- Follow Maven naming conventions
- Include proper exception handling

## Learning-Focused Approach
- Prioritize clarity over cleverness
- Include explanatory comments for Maven-specific configurations
- Show multiple approaches when relevant
- Explain trade-offs and best practices
- Connect examples to real-world use cases

## When creating new examples:
1. Start with learning objectives
2. Create minimal working example
3. Add comprehensive documentation
4. Include unit tests
5. Verify everything works independently
6. Add troubleshooting section

## Avoid:
- Complex examples in simple-examples directory
- Examples requiring external services (unless clearly documented)
- Deprecated Maven features or practices
- Examples without proper documentation
- Code without tests

## Preferred Dependencies for Examples:
- **Testing**: JUnit 5, Mockito, AssertJ
- **Logging**: SLF4J with Logback
- **JSON Processing**: Jackson
- **HTTP Clients**: Apache HttpClient
- **Utilities**: Apache Commons Lang
- **Spring**: Spring Boot for web examples
- **Build Plugins**: Standard Maven plugins with recent versions

This configuration helps ensure all generated code and suggestions align with the educational goals and quality standards of the Maven Learning Tool.
