# Maven Properties - Simple Example

This example demonstrates comprehensive Maven properties usage, including built-in properties, custom properties, environment variables, resource filtering, and property inheritance through profiles.

## 📋 What You'll Learn

- **Built-in Maven Properties**: Access project, system, and environment properties
- **Custom Properties**: Define and use custom properties in POM
- **Resource Filtering**: Substitute properties in resource files during build
- **Property Types**: Understand different property sources and precedence
- **Profile Properties**: Override properties based on active profiles
- **Environment Variables**: Integration with system environment
- **Property Inheritance**: How properties cascade and override

## 🏗 Project Structure

```
07-properties/
├── pom.xml                                    # Property definitions and filtering config
├── src/main/java/com/example/
│   └── PropertiesDemo.java                   # Main demonstration class
├── src/main/resources/
│   ├── application.properties                # Filtered properties file
│   ├── config/database.properties           # Environment-specific config
│   ├── build-info.properties               # Build metadata
│   └── logback.xml                         # Filtered XML configuration
├── src/test/java/com/example/
│   └── PropertiesDemoTest.java             # Comprehensive tests
└── README.md                               # This file
```

## 🔧 Properties Configuration

### POM Properties Section
```xml
<properties>
    <!-- Standard properties -->
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>11</maven.compiler.source>
    <maven.compiler.target>11</maven.compiler.target>

    <!-- Custom application properties -->
    <app.name>Maven Properties Demo</app.name>
    <app.version>${project.version}</app.version>

    <!-- Dependency version management -->
    <junit.version>5.8.2</junit.version>
    <slf4j.version>2.0.7</slf4j.version>

    <!-- Environment-specific properties -->
    <environment>development</environment>
    <log.level>INFO</log.level>
    <database.host>localhost</database.host>

    <!-- Feature flags -->
    <feature.debug.enabled>true</feature.debug.enabled>
</properties>
```

### Resource Filtering Configuration
```xml
<build>
    <resources>
        <resource>
            <directory>src/main/resources</directory>
            <filtering>true</filtering>
            <includes>
                <include>**/*.properties</include>
                <include>**/*.xml</include>
                <include>**/*.yml</include>
            </includes>
        </resource>
    </resources>
</build>
```

## 🚀 Running the Example

### Basic Execution
```bash
# Compile and run with default properties
mvn clean compile exec:java -Dexec.mainClass="com.example.PropertiesDemo"

# Or compile and run the JAR
mvn clean package
java -jar target/maven-properties-1.0.0.jar
```

### Profile-based Execution
```bash
# Run with production profile
mvn clean compile exec:java -Dexec.mainClass="com.example.PropertiesDemo" -Pproduction

# Run with testing profile
mvn clean compile exec:java -Dexec.mainClass="com.example.PropertiesDemo" -Ptesting

# Run with development profile (default)
mvn clean compile exec:java -Dexec.mainClass="com.example.PropertiesDemo" -Pdevelopment
```

### Custom Property Overrides
```bash
# Override specific properties at runtime
mvn clean compile exec:java \
  -Dexec.mainClass="com.example.PropertiesDemo" \
  -Dlog.level=DEBUG \
  -Denvironment=custom \
  -Ddatabase.host=custom-db.example.com

# Override multiple properties
mvn clean compile exec:java \
  -Dexec.mainClass="com.example.PropertiesDemo" \
  -Dapp.name="Custom App Name" \
  -Dfeature.debug.enabled=false
```

## 📊 Expected Output

The demo will display comprehensive information about various property types:

```
INFO  [Maven Properties Demo] [development] c.example.PropertiesDemo - === Maven Properties Demonstration ===

INFO  [Maven Properties Demo] [development] c.example.PropertiesDemo -
1. Application Information:
INFO  [Maven Properties Demo] [development] c.example.PropertiesDemo -    Name: Maven Properties Demo
INFO  [Maven Properties Demo] [development] c.example.PropertiesDemo -    Environment: development
INFO  [Maven Properties Demo] [development] c.example.PropertiesDemo -    Log Level: DEBUG
INFO  [Maven Properties Demo] [development] c.example.PropertiesDemo -    Build Timestamp: 2024-12-20 10:30:45

INFO  [Maven Properties Demo] [development] c.example.PropertiesDemo -
2. Relevant System Properties:
INFO  [Maven Properties Demo] [development] c.example.PropertiesDemo -    app.name: Maven Properties Demo
INFO  [Maven Properties Demo] [development] c.example.PropertiesDemo -    environment: development
INFO  [Maven Properties Demo] [development] c.example.PropertiesDemo -    java.version: 11.0.16
INFO  [Maven Properties Demo] [development] c.example.PropertiesDemo -    user.home: /home/user

INFO  [Maven Properties Demo] [development] c.example.PropertiesDemo -
3. Environment Variables:
INFO  [Maven Properties Demo] [development] c.example.PropertiesDemo -    JAVA_HOME: /usr/lib/jvm/java-11-openjdk
INFO  [Maven Properties Demo] [development] c.example.PropertiesDemo -    MAVEN_HOME: /opt/maven
```

## 🎯 Key Features Demonstrated

### 1. Property Types and Sources

**Built-in Maven Properties:**
- `${project.groupId}` - Project group ID
- `${project.artifactId}` - Project artifact ID
- `${project.version}` - Project version
- `${maven.build.timestamp}` - Build timestamp
- `${project.build.sourceEncoding}` - Source encoding

**System Properties:**
- `${java.version}` - Java version
- `${user.home}` - User home directory
- `${os.name}` - Operating system name

**Environment Variables:**
- `${env.JAVA_HOME}` - Java installation path
- `${env.MAVEN_HOME}` - Maven installation path

### 2. Resource Filtering

Files that get filtered during build:
- `application.properties` - Application configuration
- `config/database.properties` - Database settings
- `build-info.properties` - Build metadata
- `logback.xml` - Logging configuration

### 3. Profile-based Property Overrides

**Development Profile (default):**
```xml
<properties>
    <environment>development</environment>
    <log.level>DEBUG</log.level>
    <feature.debug.enabled>true</feature.debug.enabled>
</properties>
```

**Production Profile:**
```xml
<properties>
    <environment>production</environment>
    <log.level>WARN</log.level>
    <feature.debug.enabled>false</feature.debug.enabled>
    <feature.metrics.enabled>true</feature.metrics.enabled>
</properties>
```

### 4. Alternative Property Delimiters

The example demonstrates both standard `${...}` and alternative `@...@` delimiters:

```properties
# Standard delimiter
app.name=${app.name}

# Alternative delimiter (useful to avoid conflicts)
alternative.app.name=@app.name@
```

### 5. Generated Properties

Using the `properties-maven-plugin` to generate a complete properties file with all Maven properties:

```xml
<plugin>
    <groupId>org.codehaus.mojo</groupId>
    <artifactId>properties-maven-plugin</artifactId>
    <executions>
        <execution>
            <goals>
                <goal>write-project-properties</goal>
            </goals>
            <configuration>
                <outputFile>${project.build.outputDirectory}/generated.properties</outputFile>
            </configuration>
        </execution>
    </executions>
</plugin>
```

## 🧪 Testing

Run the comprehensive test suite:

```bash
# Run all tests
mvn test

# Run tests with specific profile
mvn test -Pproduction

# Run tests with verbose output
mvn test -Dtest.verbose=true
```

### Test Coverage

The tests verify:
- ✅ Property loading from filtered resources
- ✅ System property access and defaults
- ✅ Property info object creation
- ✅ Alternative delimiter processing
- ✅ Feature flag property handling
- ✅ Error handling for missing properties

## 🔍 Property Precedence

Maven properties follow a specific precedence order (highest to lowest):

1. **Command line properties** (`-Dproperty=value`)
2. **System properties** (`System.getProperty()`)
3. **Environment variables** (`${env.VARIABLE}`)
4. **Profile properties** (active profile)
5. **Project properties** (POM `<properties>`)
6. **Parent POM properties**
7. **Settings properties** (Maven settings.xml)

## 🛠 Troubleshooting

### Common Issues

**Properties not being filtered:**
```bash
# Verify resource filtering is enabled
mvn help:effective-pom | grep -A 10 -B 10 filtering

# Check if file extensions are included in filtering
# Ensure <filtering>true</filtering> is set for the resource directory
```

**Property resolution errors:**
```bash
# Check if property is defined
mvn help:evaluate -Dexpression=your.property.name

# List all available properties
mvn help:system

# Check active profiles
mvn help:active-profiles
```

**Generated properties file missing:**
```bash
# Verify properties plugin execution
mvn generate-resources -X

# Check target directory
ls -la target/classes/generated.properties
```

### Debugging Property Values

```bash
# Display effective POM with resolved properties
mvn help:effective-pom

# Show specific property value
mvn help:evaluate -Dexpression=app.name -q -DforceStdout

# List all system properties
mvn help:system
```

## 📚 Best Practices Demonstrated

1. **Centralized Version Management**: Use properties for dependency versions
2. **Environment Separation**: Different property values per environment
3. **Resource Filtering**: Automatic property substitution in resources
4. **Property Inheritance**: Leverage profile-based property overrides
5. **Validation**: Test property resolution and filtering
6. **Documentation**: Clear property documentation and examples
7. **Error Handling**: Graceful handling of missing properties

## 📖 Next Steps

After understanding this example:

1. Experiment with different property sources and precedence
2. Create custom profiles with different property sets
3. Add property validation using Maven Enforcer Plugin
4. Move on to the next example: `08-profiles`

## 🔗 Related Documentation

- [Maven Properties Guide](https://maven.apache.org/guides/introduction/introduction-to-the-pom.html#Properties)
- [Resource Filtering](https://maven.apache.org/plugins/maven-resources-plugin/examples/filter.html)
- [Properties Maven Plugin](https://www.mojohaus.org/properties-maven-plugin/)
- [Maven Profiles](https://maven.apache.org/guides/introduction/introduction-to-profiles.html)
