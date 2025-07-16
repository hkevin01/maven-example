# Maven Packaging Examples

This example demonstrates different Maven packaging strategies and their use cases, from simple JARs to complex web applications.

## What You'll Learn

- **Standard JAR Packaging**: Traditional Java archives with external dependencies
- **Fat/Uber JAR**: Single JAR files containing all dependencies 
- **Executable JAR**: Self-contained JARs with embedded servers (Spring Boot style)
- **WAR Packaging**: Web applications for servlet containers
- **Custom Assembly**: Tailored packaging for specific deployment needs
- **Manifest Configuration**: JAR metadata and execution settings

## Key Features

### 1. Multiple Packaging Types
The same application can be packaged in different ways using Maven profiles:
- **JAR**: `mvn clean package` - Standard library/application JAR
- **Fat JAR**: `mvn clean package -Pfat-jar` - All dependencies included
- **Executable JAR**: `mvn clean package -Pexecutable` - Spring Boot style packaging
- **WAR**: `mvn clean package -Pwar` - Web application archive
- **Assembly**: `mvn clean package assembly:single` - Custom packaging

### 2. Runtime Detection
The application can detect and display information about how it was packaged and is being executed.

### 3. Web Interface
When packaged as a WAR, the application includes a web interface accessible through a servlet container.

## Project Structure

```
06-packaging/
├── pom.xml                              # Maven configuration with multiple packaging options
├── src/main/java/com/example/
│   ├── PackagingDemo.java               # Main application demonstrating packaging
│   └── PackagingServlet.java            # Web servlet for WAR deployment
├── src/main/resources/
│   └── application.properties           # Application configuration
├── src/main/webapp/
│   ├── index.html                       # Web application home page
│   └── WEB-INF/
│       └── web.xml                      # Web application descriptor
└── src/test/java/com/example/
    └── PackagingDemoTest.java           # Comprehensive tests
```

## Packaging Strategies

### 1. Standard JAR (Default)
```bash
mvn clean package
```
- **Output**: `packaging-demo-1.0.0.jar`
- **Use Case**: Libraries, applications with external classpath management
- **Execution**: `java -cp "lib/*" -jar packaging-demo-1.0.0.jar`
- **Dependencies**: Managed separately, typically in a `lib/` directory

### 2. Fat/Uber JAR
```bash
mvn clean package -Pfat-jar
```
- **Output**: `packaging-demo-1.0.0-fat.jar`
- **Use Case**: Standalone applications, microservices, easy distribution
- **Execution**: `java -jar packaging-demo-1.0.0-fat.jar`
- **Dependencies**: All included in single JAR file

### 3. Executable JAR (Spring Boot Style)
```bash
mvn clean package -Pexecutable
```
- **Output**: `packaging-demo-1.0.0-executable.jar`
- **Use Case**: Microservices, cloud deployments, containerized applications
- **Execution**: `java -jar packaging-demo-1.0.0-executable.jar`
- **Dependencies**: Nested JAR structure with custom classloader

### 4. WAR File
```bash
mvn clean package -Pwar
```
- **Output**: `packaging-demo-1.0.0.war`
- **Use Case**: Traditional web applications, enterprise application servers
- **Deployment**: Deploy to Tomcat, WebLogic, WebSphere, etc.
- **Dependencies**: Included in `WEB-INF/lib/`

### 5. Assembly JAR
```bash
mvn clean package assembly:single
```
- **Output**: `packaging-demo-1.0.0-jar-with-dependencies.jar`
- **Use Case**: Custom packaging requirements, specific file layouts
- **Execution**: `java -jar packaging-demo-1.0.0-jar-with-dependencies.jar`
- **Dependencies**: Customizable inclusion/exclusion rules

## Running the Examples

### Basic Execution
```bash
# Compile and run with Maven
mvn compile exec:java

# Run tests
mvn test

# Package and run JAR
mvn package
java -jar target/packaging-demo-1.0.0.jar
```

### Profile-Specific Packaging
```bash
# Create fat JAR and run
mvn clean package -Pfat-jar
java -jar target/packaging-demo-1.0.0-fat.jar

# Create executable JAR and run
mvn clean package -Pexecutable
java -jar target/packaging-demo-1.0.0-executable.jar

# Create WAR (deploy to servlet container)
mvn clean package -Pwar
# Deploy target/packaging-demo-1.0.0.war to Tomcat/etc.

# Create assembly JAR
mvn clean package assembly:single
java -jar target/packaging-demo-1.0.0-jar-with-dependencies.jar
```

### Comparing Packaging Results
```bash
# Package all types and compare
mvn clean package -Pfat-jar
mvn package -Pexecutable
mvn package assembly:single

# Compare file sizes
ls -lh target/*.jar
```

## Key Maven Concepts

### JAR Plugin Configuration
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-jar-plugin</artifactId>
    <configuration>
        <archive>
            <manifest>
                <addClasspath>true</addClasspath>
                <mainClass>com.example.PackagingDemo</mainClass>
            </manifest>
        </archive>
    </configuration>
</plugin>
```

### Shade Plugin for Fat JARs
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-shade-plugin</artifactId>
    <executions>
        <execution>
            <goals>
                <goal>shade</goal>
            </goals>
            <configuration>
                <shadedClassifierName>fat</shadedClassifierName>
            </configuration>
        </execution>
    </executions>
</plugin>
```

### WAR Plugin Configuration
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-war-plugin</artifactId>
    <configuration>
        <failOnMissingWebXml>false</failOnMissingWebXml>
    </configuration>
</plugin>
```

## Output Examples

When you run the packaging demo, you'll see:

```
=== Maven Packaging Demonstration ===

1. Application Information:
===========================
Application: Maven Packaging Examples
Version: 1.0.0
Java Version: 11.0.16
Maven Packaging Type: Standard JAR

2. Packaging Types Demonstration:
=================================
1. Standard JAR - Dependencies managed externally
2. Fat/Uber JAR - All dependencies included in single file
3. Executable JAR - Self-contained with embedded server
4. WAR File - Web application archive for servlet containers
5. Custom Assembly - Tailored packaging with specific structure

Current Runtime Configuration:
- Classpath entries: 15
- JAR file location: file:/path/to/target/packaging-demo-1.0.0.jar
- Execution mode: JAR Execution

3. Dependency Verification:
===========================
✓ SLF4J Logging - Available
✓ Logback Classic - Available
✓ Jackson JSON - Available
✗ Servlet API - Not available

4. Runtime Environment Analysis:
================================
- Working Directory: /path/to/maven-example/simple-examples/06-packaging
- Java Home: /usr/lib/jvm/java-11-openjdk
- OS: Linux 5.15.0
- Architecture: amd64
- Available Processors: 8
- Max Memory: 1024 MB
- Free Memory: 512 MB

5. JAR Manifest Information:
============================
- Main-Class: com.example.PackagingDemo
- Implementation-Title: packaging-demo
- Implementation-Version: 1.0.0
- Built-By: developer
- Build-Timestamp: 2024-01-15T10:30:45Z
- Class-Path: lib/slf4j-api-1.7.36.jar lib/logback-classic-1.2.11.jar ...
```

## Use Cases by Packaging Type

### Standard JAR
- **Libraries**: Maven dependencies, shared components
- **Enterprise Applications**: Where classpath is managed by application server
- **Modular Applications**: When dependencies are shared across multiple applications

### Fat/Uber JAR
- **Microservices**: Self-contained services for container deployment
- **Command-line Tools**: Easy distribution and execution
- **Lambda Functions**: AWS Lambda, Azure Functions deployment
- **Standalone Applications**: No external dependency management needed

### Executable JAR
- **Spring Boot Applications**: Modern microservice architecture
- **Cloud-native Applications**: 12-factor app compliance
- **Container Deployment**: Docker, Kubernetes deployments
- **Development Tools**: Self-contained development utilities

### WAR Files
- **Traditional Web Applications**: JSP, Servlet-based applications
- **Enterprise Applications**: WebLogic, WebSphere deployment
- **Legacy Systems**: Existing application server infrastructure
- **Multi-tenant Applications**: Shared application server resources

### Assembly Packaging
- **Custom Distributions**: Specific file layouts and structures
- **Installation Packages**: Applications with configuration files
- **Documentation Bundles**: Applications with bundled documentation
- **Multi-artifact Packages**: Applications with multiple executable components

## Best Practices

### 1. Choose the Right Packaging
- **Fat JAR**: For microservices and standalone applications
- **Standard JAR**: For libraries and shared components
- **WAR**: For traditional web applications
- **Executable JAR**: For cloud-native applications

### 2. Manifest Configuration
- Always specify `Main-Class` for executable JARs
- Include build information in manifest
- Configure classpath for standard JARs

### 3. Dependency Management
- Use `provided` scope for container-provided dependencies
- Exclude unnecessary dependencies in fat JARs
- Consider dependency conflicts in shade plugin

### 4. Testing Different Packages
- Test each packaging type in CI/CD pipeline
- Verify classpath and dependency resolution
- Test deployment in target environments

## Next Steps

After mastering packaging strategies, explore:
- **07-properties**: Advanced Maven property usage and inheritance  
- **08-profiles**: Complex profile configurations and activation
- **Advanced Examples**: Multi-module projects with different packaging types
- **Deployment Strategies**: CI/CD pipelines for different package types
