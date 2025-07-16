# Dependency Management - Simple Example

This example demonstrates comprehensive Maven dependency management concepts including different dependency scopes, version management, and working with various external libraries.

## 📁 Project Structure

```
02-dependencies/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── example/
│                   └── DependencyExample.java
├── pom.xml
└── README.md
```

## 🎯 Learning Objectives

By studying this example, you will learn:

1. **Dependency Scopes**: Understanding compile, runtime, test, and provided scopes
2. **Version Management**: Using properties for consistent versioning
3. **Popular Libraries**: Working with commonly used Java libraries
4. **Transitive Dependencies**: How Maven resolves dependencies automatically
5. **Dependency Analysis**: Using Maven tools to analyze your dependencies

## 🛠 Maven Concepts Demonstrated

### 1. Dependency Scopes

#### Compile Scope (Default)
```xml
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>${jackson.version}</version>
    <!-- No scope specified = compile scope -->
</dependency>
```
- Available during compilation and runtime
- Included in the final artifact
- Most common scope

#### Runtime Scope
```xml
<dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-classic</artifactId>
    <version>1.2.11</version>
    <scope>runtime</scope>
</dependency>
```
- Not needed for compilation but required at runtime
- Common for logging implementations

#### Test Scope
```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.8.2</version>
    <scope>test</scope>
</dependency>
```
- Only available during test compilation and execution
- Not included in the final artifact

#### Provided Scope
```xml
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <version>4.0.1</version>
    <scope>provided</scope>
</dependency>
```
- Available during compilation but not runtime
- Expected to be provided by the runtime environment
- Common for APIs provided by application servers

### 2. Version Management with Properties

```xml
<properties>
    <jackson.version>2.13.3</jackson.version>
    <slf4j.version>1.7.36</slf4j.version>
</properties>

<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>${jackson.version}</version>
</dependency>
```

**Benefits:**
- Centralized version management
- Easy to update multiple related dependencies
- Reduces version conflicts
- Improves maintainability

### 3. Library Categories Shown

#### JSON Processing (Jackson)
- **jackson-databind**: Object serialization/deserialization
- **jackson-datatype-jsr310**: Java 8 time support

#### Logging (SLF4J + Logback)
- **slf4j-api**: Logging facade
- **logback-classic**: Logging implementation

#### Utility Libraries
- **commons-lang3**: String and array utilities
- **httpclient**: HTTP client functionality

#### Testing Libraries
- **junit-jupiter**: Unit testing framework
- **mockito-core**: Mocking framework
- **assertj-core**: Fluent assertions

## 🚀 How to Run

### Prerequisites
- Java 11 or higher
- Maven 3.6+

### Commands

1. **Compile the project:**
   ```bash
   mvn clean compile
   ```

2. **Run tests:**
   ```bash
   mvn test
   ```

3. **Run the application:**
   ```bash
   mvn exec:java -Dexec.mainClass="com.example.DependencyExample"
   ```

4. **Analyze dependencies:**
   ```bash
   # Show dependency tree
   mvn dependency:tree
   
   # Analyze for unused/undeclared dependencies
   mvn dependency:analyze
   
   # Show all dependencies (including transitive)
   mvn dependency:list
   ```

5. **Package with dependencies:**
   ```bash
   mvn package
   ```

## 📋 Expected Output

When you run the application, you should see output like:

```
16:34:01.969 [main] INFO com.example.DependencyExample - Starting Maven Dependency Example
16:34:02.144 [main] INFO com.example.DependencyExample - Demonstrating JSON processing with Jackson
16:34:02.165 [main] INFO com.example.DependencyExample - Serialized JSON: {"name":"Maven Dependency Example","active":true,"version":"1.0.0","timestamp":[2025,7,15,16,34,2,147941075]}
16:34:02.187 [main] INFO com.example.DependencyExample - Deserialized data: {name=Maven Dependency Example, active=true, version=1.0.0, timestamp=[2025, 7, 15, 16, 34, 2, 147941075]}
16:34:02.188 [main] INFO com.example.DependencyExample - Demonstrating Apache Commons Lang utilities
16:34:02.191 [main] INFO com.example.DependencyExample - Original: '  Maven Dependency Management  '
16:34:02.194 [main] INFO com.example.DependencyExample - Trimmed: 'Maven Dependency Management'
16:34:02.194 [main] INFO com.example.DependencyExample - Capitalized: 'Maven dependency management'
16:34:02.194 [main] INFO com.example.DependencyExample - Is empty: false
16:34:02.194 [main] INFO com.example.DependencyExample - Is blank (whitespace): true
16:34:02.198 [main] INFO com.example.DependencyExample - Words: Maven, Dependency, Management
16:34:02.204 [main] INFO com.example.DependencyExample - Joined: Maven | Dependency | Management
16:34:02.204 [main] INFO com.example.DependencyExample - Demonstrating HTTP client
```

## 🔍 Code Explanation

### DependencyExample.java
The main class demonstrates practical usage of various dependencies:

1. **Jackson ObjectMapper**: JSON serialization/deserialization
2. **Apache Commons Lang**: String manipulation utilities
3. **Apache HttpClient**: HTTP request handling
4. **SLF4J Logging**: Structured logging throughout the application

### Key Methods

#### `demonstrateJsonProcessing()`
Shows how to:
- Configure Jackson with JSR310 module for Java 8 time support
- Serialize objects to JSON
- Deserialize JSON back to objects

#### `demonstrateCommonsLang()`
Shows how to:
- Use StringUtils for common string operations
- Handle null and whitespace safely
- Split and join strings

#### `demonstrateHttpClient()`
Shows how to:
- Create HTTP clients
- Make HTTP requests
- Handle responses and errors

## 🔧 Dependency Analysis Tools

### Maven Dependency Plugin Goals

```bash
# Show complete dependency tree
mvn dependency:tree

# Show dependency tree for specific dependency
mvn dependency:tree -Dincludes=com.fasterxml.jackson.core:*

# Analyze for problems
mvn dependency:analyze

# Show effective POM (including inherited dependencies)
mvn help:effective-pom

# Copy dependencies to a directory
mvn dependency:copy-dependencies

# Resolve sources for dependencies
mvn dependency:sources
```

### Understanding Dependency Tree Output

```
[INFO] com.example:dependency-management:jar:1.0.0
[INFO] +- com.fasterxml.jackson.core:jackson-databind:jar:2.13.3:compile
[INFO] |  +- com.fasterxml.jackson.core:jackson-annotations:jar:2.13.3:compile
[INFO] |  \- com.fasterxml.jackson.core:jackson-core:jar:2.13.3:compile
[INFO] +- com.fasterxml.jackson.datatype:jackson-datatype-jsr310:jar:2.13.3:compile
[INFO] +- org.slf4j:slf4j-api:jar:1.7.36:compile
```

This shows:
- Direct dependencies (first level)
- Transitive dependencies (indented)
- Dependency scopes
- Version resolution

## 🤔 Common Issues & Solutions

### Issue: Version Conflicts
**Problem**: Different versions of the same dependency
**Solution**: Use dependency management or exclusions
```xml
<dependency>
    <groupId>some.group</groupId>
    <artifactId>some-artifact</artifactId>
    <version>1.0</version>
    <exclusions>
        <exclusion>
            <groupId>conflicting.group</groupId>
            <artifactId>conflicting-artifact</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```

### Issue: ClassNotFoundException at Runtime
**Problem**: Dependency not available at runtime
**Solution**: Check dependency scope (should be compile or runtime, not test or provided)

### Issue: NoSuchMethodError
**Problem**: Version incompatibility between dependencies
**Solution**: Use `mvn dependency:tree` to identify version conflicts and resolve them

### Issue: Dependency Not Found
**Problem**: Repository configuration or typo in coordinates
**Solution**: 
- Check spelling of groupId/artifactId/version
- Verify repository accessibility
- Try `mvn clean install -U` to force update

## 📚 Best Practices Demonstrated

1. **Use Properties for Versions**: Centralize version management
2. **Group Related Dependencies**: Organize by functionality
3. **Appropriate Scopes**: Use correct scopes for each dependency
4. **Document Dependencies**: Comment why each dependency is needed
5. **Regular Updates**: Keep dependencies current for security and features
6. **Minimal Dependencies**: Only include what you actually use

## 📖 Next Steps

After understanding this example:

1. Try adding a new dependency (e.g., Apache Commons IO)
2. Experiment with excluding transitive dependencies
3. Practice using different dependency scopes
4. Move on to the next example: `03-plugins`

## 🔗 Additional Resources

- [Maven Dependency Mechanism](https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html)
- [Dependency Scopes](https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html#Dependency_Scope)
- [Jackson Documentation](https://github.com/FasterXML/jackson-docs)
- [Apache Commons Lang](https://commons.apache.org/proper/commons-lang/)
- [SLF4J Manual](http://www.slf4j.org/manual.html)
