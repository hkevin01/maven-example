# Plugin Configuration - Simple Example

This example demonstrates comprehensive Maven plugin configurations and how different plugins interact during the build lifecycle. It showcases quality assurance, code coverage, resource filtering, and build automation plugins.

## 📁 Project Structure

```
03-plugins/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── PluginDemo.java
│   │   └── resources/
│   │       └── build.properties
│   └── test/
│       └── java/
│           └── com/
│               └── example/
│                   └── PluginDemoTest.java
├── checkstyle.xml
├── pom.xml
└── README.md
```

## 🎯 Learning Objectives

By studying this example, you will learn:

1. **Plugin Configuration**: How to configure and customize Maven plugins
2. **Build Lifecycle Integration**: How plugins integrate with Maven phases
3. **Code Quality Tools**: Setting up Checkstyle for code style enforcement
4. **Code Coverage**: Using JaCoCo for test coverage analysis
5. **Resource Filtering**: How Maven can filter and process resources
6. **JAR Manifest Customization**: Adding custom entries to JAR manifests
7. **Parallel Test Execution**: Configuring Surefire for parallel testing
8. **Build Automation**: Combining multiple plugins for comprehensive builds

## 🛠 Plugins Demonstrated

### 1. Maven Compiler Plugin
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <version>3.8.1</version>
    <configuration>
        <source>11</source>
        <target>11</target>
        <compilerArgs>
            <arg>-Xlint:all</arg>
            <arg>-parameters</arg>
        </compilerArgs>
    </configuration>
</plugin>
```

**Features shown:**
- Java version configuration
- Compiler arguments for enhanced warnings
- Parameter names preservation

### 2. Maven Surefire Plugin (Testing)
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <configuration>
        <parallel>methods</parallel>
        <threadCount>4</threadCount>
        <includes>
            <include>**/*Test.java</include>
        </includes>
        <systemPropertyVariables>
            <test.environment>unit</test.environment>
        </systemPropertyVariables>
    </configuration>
</plugin>
```

**Features shown:**
- Parallel test execution
- Test inclusion/exclusion patterns
- System property configuration

### 3. Maven JAR Plugin (Packaging)
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-jar-plugin</artifactId>
    <configuration>
        <archive>
            <manifest>
                <addClasspath>true</addClasspath>
                <mainClass>com.example.PluginDemo</mainClass>
            </manifest>
            <manifestEntries>
                <Built-By>${user.name}</Built-By>
                <Build-Timestamp>${maven.build.timestamp}</Build-Timestamp>
            </manifestEntries>
        </archive>
    </configuration>
</plugin>
```

**Features shown:**
- Executable JAR configuration
- Custom manifest entries
- Build information injection

### 4. Exec Maven Plugin (Execution)
```xml
<plugin>
    <groupId>org.codehaus.mojo</groupId>
    <artifactId>exec-maven-plugin</artifactId>
    <configuration>
        <mainClass>com.example.PluginDemo</mainClass>
        <arguments>
            <argument>maven</argument>
            <argument>plugins</argument>
        </arguments>
    </configuration>
</plugin>
```

**Features shown:**
- Java program execution from Maven
- Command-line argument passing
- Lifecycle phase binding

### 5. JaCoCo Plugin (Code Coverage)
```xml
<plugin>
    <groupId>org.jacoco</groupId>
    <artifactId>jacoco-maven-plugin</artifactId>
    <executions>
        <execution>
            <id>prepare-agent</id>
            <goals>
                <goal>prepare-agent</goal>
            </goals>
        </execution>
        <execution>
            <id>report</id>
            <phase>test</phase>
            <goals>
                <goal>report</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

**Features shown:**
- Code coverage instrumentation
- Coverage report generation
- Coverage thresholds enforcement

### 6. Checkstyle Plugin (Code Quality)
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-checkstyle-plugin</artifactId>
    <configuration>
        <configLocation>checkstyle.xml</configLocation>
        <consoleOutput>true</consoleOutput>
        <failsOnError>false</failsOnError>
    </configuration>
</plugin>
```

**Features shown:**
- Code style validation
- Custom Checkstyle configuration
- Build integration

### 7. Maven Resources Plugin (Resource Processing)
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-resources-plugin</artifactId>
    <executions>
        <execution>
            <id>copy-resources</id>
            <phase>validate</phase>
            <goals>
                <goal>copy-resources</goal>
            </goals>
            <configuration>
                <resources>
                    <resource>
                        <directory>src/main/resources</directory>
                        <filtering>true</filtering>
                    </resource>
                </resources>
            </configuration>
        </execution>
    </executions>
</plugin>
```

**Features shown:**
- Resource filtering with Maven properties
- Custom resource copying
- Phase-specific execution

## 🚀 How to Run

### Prerequisites
- Java 11 or higher
- Maven 3.6+

### Commands

1. **Full build with all plugins:**
   ```bash
   mvn clean compile test
   ```

2. **Run the application:**
   ```bash
   mvn exec:java
   # or with custom arguments:
   mvn exec:java -Dexec.args="custom arguments here"
   ```

3. **Generate coverage report:**
   ```bash
   mvn clean test jacoco:report
   # View report at: target/site/jacoco/index.html
   ```

4. **Run Checkstyle analysis:**
   ```bash
   mvn checkstyle:check
   ```

5. **Package with custom manifest:**
   ```bash
   mvn package
   java -jar target/plugin-configuration-1.0.0.jar
   ```

6. **Analyze dependencies:**
   ```bash
   mvn dependency:tree
   mvn dependency:analyze
   ```

7. **Show effective POM (see plugin inheritance):**
   ```bash
   mvn help:effective-pom
   ```

## 📋 Expected Output

### Test Execution
```
[INFO] Tests run: 16, Failures: 0, Errors: 0, Skipped: 0
```

### Application Output
When running `mvn exec:java`:
```
16:42:50.116 [main] INFO com.example.PluginDemo - Starting Plugin Configuration Demo
16:42:50.120 [main] INFO com.example.PluginDemo - === Build Information ===
16:42:50.122 [main] INFO com.example.PluginDemo - Implementation Title: Plugin Configuration Example
16:42:50.123 [main] INFO com.example.PluginDemo - === Filtered Build Properties ===
16:42:50.124 [main] INFO com.example.PluginDemo - project.name: Plugin Configuration Example
16:42:50.125 [main] INFO com.example.PluginDemo - build.timestamp: 2025-07-15T20:42:49Z
```

### Coverage Report
- Generated at `target/site/jacoco/index.html`
- Shows line, branch, and method coverage
- Enforces minimum 70% instruction coverage

### Checkstyle Report
- Validates code style according to `checkstyle.xml`
- Reports violations in console
- Can be configured to fail build on violations

## 🔍 Code Explanation

### PluginDemo.java
Demonstrates various Maven plugin integrations:

1. **Resource Filtering**: Loads `build.properties` with filtered Maven properties
2. **JAR Manifest**: Reads implementation details from JAR manifest
3. **Logging Integration**: Uses SLF4J with Logback (runtime dependency)
4. **Testable Methods**: Provides methods with different complexity for coverage analysis

### PluginDemoTest.java
Comprehensive test suite showing:

1. **Test Organization**: Using JUnit 5 with descriptive names
2. **Coverage Patterns**: Tests designed to achieve good code coverage
3. **Edge Cases**: Testing null inputs, empty collections, error conditions
4. **AssertJ Fluent Assertions**: Modern assertion style

### Resource Filtering
The `build.properties` file demonstrates Maven property substitution:
```properties
# Before filtering:
project.name=${project.name}
build.timestamp=${maven.build.timestamp}

# After filtering:
project.name=Plugin Configuration Example
build.timestamp=2025-07-15T20:42:49Z
```

## 📊 Plugin Execution Flow

The plugins execute in this order during `mvn clean compile test`:

1. **validate phase**:
   - Checkstyle validation
   - Resource copying

2. **generate-sources phase**:
   - Build Helper adds source directories

3. **process-resources phase**:
   - Resources plugin filters properties

4. **compile phase**:
   - Compiler plugin compiles Java sources

5. **test phase**:
   - JaCoCo prepares test agent
   - Surefire runs tests
   - JaCoCo generates coverage report

## 🔧 Plugin Analysis Commands

### Understanding Plugin Goals
```bash
# List all plugin goals
mvn help:describe -Dplugin=compiler -Ddetail

# Show plugin configuration
mvn help:describe -Dplugin=org.jacoco:jacoco-maven-plugin -Ddetail

# Show effective plugin configuration
mvn help:effective-pom | grep -A 20 "maven-compiler-plugin"
```

### Coverage Analysis
```bash
# Generate coverage report
mvn jacoco:report

# Check coverage thresholds
mvn jacoco:check

# Offline instrumentation (alternative approach)
mvn jacoco:instrument
```

### Checkstyle Analysis
```bash
# Run checkstyle check
mvn checkstyle:check

# Generate checkstyle report
mvn checkstyle:checkstyle

# Use different checkstyle config
mvn checkstyle:check -Dcheckstyle.config.location=custom-checkstyle.xml
```

## 🤔 Common Issues & Solutions

### Issue: JaCoCo Class Format Exception
**Problem**: JaCoCo version incompatible with Java version
**Solution**: Update JaCoCo version or exclude system classes:
```xml
<configuration>
    <excludes>
        <exclude>sun/**</exclude>
        <exclude>java/**</exclude>
    </excludes>
</configuration>
```

### Issue: Checkstyle Violations
**Problem**: Code doesn't meet style guidelines
**Solution**: 
- Fix code to match guidelines
- Modify `checkstyle.xml` rules
- Set `<failsOnError>false</failsOnError>` to warn only

### Issue: Test Coverage Below Threshold
**Problem**: JaCoCo coverage check fails
**Solution**:
- Add more tests
- Lower threshold temporarily
- Exclude utility classes from coverage

### Issue: Resource Filtering Not Working
**Problem**: Properties not substituted in resources
**Solution**:
- Ensure `<filtering>true</filtering>`
- Check property names match exactly
- Verify resource directory configuration

## 📚 Best Practices Demonstrated

1. **Plugin Version Management**: Use properties for plugin versions
2. **Phase Binding**: Bind plugin goals to appropriate lifecycle phases
3. **Configuration Externalization**: Use separate files for complex configuration
4. **Quality Gates**: Set up automated quality checks
5. **Resource Processing**: Leverage Maven's resource filtering capabilities
6. **Test Optimization**: Configure parallel test execution for faster builds
7. **Documentation**: Include comprehensive plugin documentation

## 📖 Next Steps

After understanding this example:

1. Experiment with different plugin configurations
2. Add additional quality plugins (SpotBugs, PMD)
3. Set up custom plugin executions
4. Move on to the next example: `04-testing`

## 🔗 Additional Resources

- [Maven Plugin Development Guide](https://maven.apache.org/guides/plugin/guide-java-plugin-development.html)
- [JaCoCo Documentation](https://www.jacoco.org/jacoco/trunk/doc/)
- [Checkstyle Configuration](https://checkstyle.sourceforge.io/config.html)
- [Surefire Plugin Documentation](https://maven.apache.org/surefire/maven-surefire-plugin/)
- [Maven Resources Plugin](https://maven.apache.org/plugins/maven-resources-plugin/)
