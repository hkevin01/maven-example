# Hello Maven - Simple Example

This is a basic "Hello World" Maven project that demonstrates the fundamental Maven project structure and basic concepts.

## 📁 Project Structure

```
01-hello-maven/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── example/
│   │               └── HelloMaven.java
│   └── test/
│       └── java/
│           └── com/
│               └── example/
│                   └── HelloMavenTest.java
├── pom.xml
└── README.md
```

## 🎯 Learning Objectives

By studying this example, you will learn:

1. **Maven Standard Directory Layout**: Understanding the conventional directory structure
2. **Basic POM Configuration**: Essential elements of a Maven Project Object Model
3. **Dependency Management**: How to include external libraries (JUnit 5)
4. **Plugin Configuration**: Basic plugin usage for compilation and testing
5. **Maven Lifecycle**: Understanding compile, test, and package phases

## 🛠 Key Maven Concepts Demonstrated

### 1. Project Coordinates
```xml
<groupId>com.example</groupId>
<artifactId>hello-maven</artifactId>
<version>1.0.0</version>
<packaging>jar</packaging>
```

### 2. Properties
```xml
<properties>
    <maven.compiler.source>11</maven.compiler.source>
    <maven.compiler.target>11</maven.compiler.target>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
</properties>
```

### 3. Dependencies
```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.8.2</version>
    <scope>test</scope>
</dependency>
```

### 4. Build Plugins
- **maven-compiler-plugin**: Compiles Java source code
- **maven-surefire-plugin**: Runs unit tests

## 🚀 How to Run

### Prerequisites
- Java 11 or higher
- Maven 3.6+

### Commands

1. **Compile the project:**
   ```bash
   mvn compile
   ```

2. **Run tests:**
   ```bash
   mvn test
   ```

3. **Package the JAR:**
   ```bash
   mvn package
   ```

4. **Clean build artifacts:**
   ```bash
   mvn clean
   ```

5. **Complete build cycle:**
   ```bash
   mvn clean compile test package
   ```

6. **Run the application:**
   ```bash
   java -cp target/classes com.example.HelloMaven
   # or with an argument:
   java -cp target/classes com.example.HelloMaven "Your Name"
   ```

## 📋 Expected Output

When you run the tests:
```
[INFO] Tests run: 5, Failures: 0, Errors: 0, Skipped: 0
```

When you run the main class:
```
Hello, World!
Hello, Maven!
Hello, Developer!
```

## 🔍 Code Explanation

### HelloMaven.java
- Simple class with a `greet()` method
- Demonstrates basic Java functionality
- Includes a main method for execution

### HelloMavenTest.java
- JUnit 5 test class
- Tests various scenarios of the `greet()` method
- Demonstrates test-driven development principles

## 📚 Next Steps

After understanding this example, you can:

1. Try modifying the source code and running tests
2. Add more dependencies to the POM
3. Experiment with different Maven phases
4. Move on to the next example: `02-dependencies`

## 🤔 Common Issues & Solutions

### Issue: "Package does not exist" errors in IDE
**Solution**: Import the project as a Maven project in your IDE or run `mvn compile` first.

### Issue: Tests not running
**Solution**: Ensure you have the correct JUnit 5 dependency and Surefire plugin configuration.

### Issue: Java version mismatch
**Solution**: Verify your JAVA_HOME points to Java 11+ and update the compiler plugin configuration if needed.

## 📖 Additional Resources

- [Maven Getting Started Guide](https://maven.apache.org/guides/getting-started/)
- [Maven Standard Directory Layout](https://maven.apache.org/guides/introduction/introduction-to-the-standard-directory-layout.html)
- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)
