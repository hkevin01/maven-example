# Maven Profiles - Simple Example

This example demonstrates comprehensive Maven profiles usage for environment management, feature flags, build customization, and automated activation based on various conditions.

## 📋 What You'll Learn

- **Environment Profiles**: Separate configurations for local, dev, test, staging, and production
- **Feature Flag Profiles**: Enable/disable features using profiles
- **Build Configuration Profiles**: Fast builds, full builds, and CI-specific settings
- **Profile Activation**: Automatic activation based on JDK, OS, properties, and files
- **Property Inheritance**: How profiles override and inherit properties
- **Profile Combinations**: Using multiple profiles simultaneously
- **Real-world Scenarios**: Practical profile usage patterns

## 🏗 Project Structure

```
08-profiles/
├── pom.xml                                    # Comprehensive profile definitions
├── src/main/java/com/example/
│   └── ProfileDemo.java                      # Profile demonstration class
├── src/main/resources/
│   ├── database.properties                   # Database config (filtered)
│   ├── api.properties                       # API config (filtered)
│   └── environments/                        # Environment-specific configs
│       ├── local.properties                 # Local development
│       ├── development.properties           # Shared development
│       ├── testing.properties              # QA/testing environment
│       ├── staging.properties              # Pre-production staging
│       └── production.properties           # Live production
├── src/test/java/com/example/
│   └── ProfileDemoTest.java                # Comprehensive tests
└── README.md                               # This file
```

## 🔧 Profile Categories

### 1. Environment Profiles

**Local Development Profile (active by default):**
```xml
<profile>
    <id>local</id>
    <activation>
        <activeByDefault>true</activeByDefault>
    </activation>
    <properties>
        <environment>local</environment>
        <log.level>DEBUG</log.level>
        <database.host>localhost</database.host>
        <feature.debug.enabled>true</feature.debug.enabled>
    </properties>
</profile>
```

**Available Environment Profiles:**
- `local` - Local development (H2 database, debug enabled)
- `development` - Shared dev environment (PostgreSQL, metrics enabled)
- `testing` - QA environment (integration tests, strict security)
- `staging` - Pre-production (production-like, monitoring enabled)
- `production` - Live environment (optimized, error-only logging)

### 2. Feature Flag Profiles

```xml
<!-- Debug Features -->
<profile>
    <id>debug</id>
    <properties>
        <feature.debug.enabled>true</feature.debug.enabled>
        <log.level>DEBUG</log.level>
    </properties>
</profile>

<!-- Performance Monitoring -->
<profile>
    <id>metrics</id>
    <properties>
        <feature.metrics.enabled>true</feature.metrics.enabled>
    </properties>
    <dependencies>
        <dependency>
            <groupId>io.micrometer</groupId>
            <artifactId>micrometer-core</artifactId>
        </dependency>
    </dependencies>
</profile>
```

### 3. Build Configuration Profiles

```xml
<!-- Fast Build (Skip Tests) -->
<profile>
    <id>fast</id>
    <properties>
        <skip.tests>true</skip.tests>
        <skip.integration.tests>true</skip.integration.tests>
    </properties>
</profile>

<!-- Full Build (All Tests) -->
<profile>
    <id>full</id>
    <properties>
        <skip.tests>false</skip.tests>
        <skip.integration.tests>false</skip.integration.tests>
    </properties>
</profile>
```

### 4. Activation-Based Profiles

**JDK Version Activation:**
```xml
<profile>
    <id>java11plus</id>
    <activation>
        <jdk>[11,)</jdk>
    </activation>
    <properties>
        <feature.modern.java>true</feature.modern.java>
    </properties>
</profile>
```

**Operating System Activation:**
```xml
<profile>
    <id>windows</id>
    <activation>
        <os><family>windows</family></os>
    </activation>
    <properties>
        <script.extension>.bat</script.extension>
    </properties>
</profile>
```

**Property-based Activation:**
```xml
<profile>
    <id>ci-build</id>
    <activation>
        <property><name>ci</name></property>
    </activation>
    <properties>
        <build.type>ci</build.type>
    </properties>
</profile>
```

## 🚀 Running with Different Profiles

### Environment-Specific Execution

```bash
# Local development (default)
mvn clean compile exec:java -Dexec.mainClass="com.example.ProfileDemo"

# Development environment
mvn clean compile exec:java -Dexec.mainClass="com.example.ProfileDemo" -Pdevelopment

# Testing environment
mvn clean compile exec:java -Dexec.mainClass="com.example.ProfileDemo" -Ptesting

# Staging environment
mvn clean compile exec:java -Dexec.mainClass="com.example.ProfileDemo" -Pstaging

# Production environment
mvn clean compile exec:java -Dexec.mainClass="com.example.ProfileDemo" -Pproduction
```

### Feature Flag Combinations

```bash
# Enable debug and metrics together
mvn clean compile exec:java -Dexec.mainClass="com.example.ProfileDemo" -Pdebug,metrics

# Development environment with security hardening
mvn clean compile exec:java -Dexec.mainClass="com.example.ProfileDemo" -Pdevelopment,security

# Testing with full test suite
mvn clean test -Ptesting,full
```

### Build Configuration

```bash
# Fast build (skip tests)
mvn clean package -Pfast

# Full build with all tests
mvn clean package -Pfull

# CI build with specific flags
mvn clean package -Pci-build -Dci=true

# Production build with optimizations
mvn clean package -Pproduction,security
```

### Property Overrides

```bash
# Override specific properties
mvn clean compile exec:java \
  -Dexec.mainClass="com.example.ProfileDemo" \
  -Pdevelopment \
  -Ddatabase.host=custom-db.example.com \
  -Dlog.level=WARN

# Multiple property overrides
mvn clean compile exec:java \
  -Dexec.mainClass="com.example.ProfileDemo" \
  -Pstaging \
  -Dfeature.debug.enabled=true \
  -Dapi.timeout=60000
```

## 📊 Expected Output Examples

### Local Environment Output
```
INFO  [Maven Profiles Demo] [local] c.example.ProfileDemo - === Maven Profiles Demonstration ===

INFO  [Maven Profiles Demo] [local] c.example.ProfileDemo -
1. Active Configuration Summary:
INFO  [Maven Profiles Demo] [local] c.example.ProfileDemo -    Environment: local
INFO  [Maven Profiles Demo] [local] c.example.ProfileDemo -    Log Level: DEBUG
INFO  [Maven Profiles Demo] [local] c.example.ProfileDemo -    Debug Enabled: true
INFO  [Maven Profiles Demo] [local] c.example.ProfileDemo -    Metrics Enabled: false

INFO  [Maven Profiles Demo] [local] c.example.ProfileDemo -
7. Profile Effects Demonstration:
INFO  [Maven Profiles Demo] [local] c.example.ProfileDemo -    LOCAL environment effects:
INFO  [Maven Profiles Demo] [local] c.example.ProfileDemo -      - Debug logging enabled
INFO  [Maven Profiles Demo] [local] c.example.ProfileDemo -      - Uses H2 database
INFO  [Maven Profiles Demo] [local] c.example.ProfileDemo -      - Relaxed security
INFO  [Maven Profiles Demo] [local] c.example.ProfileDemo -      - Development features enabled
```

### Production Environment Output
```
ERROR [Maven Profiles Demo] [production] c.example.ProfileDemo - === Maven Profiles Demonstration ===

ERROR [Maven Profiles Demo] [production] c.example.ProfileDemo -
1. Active Configuration Summary:
ERROR [Maven Profiles Demo] [production] c.example.ProfileDemo -    Environment: production
ERROR [Maven Profiles Demo] [production] c.example.ProfileDemo -    Log Level: ERROR
ERROR [Maven Profiles Demo] [production] c.example.ProfileDemo -    Debug Enabled: false
ERROR [Maven Profiles Demo] [production] c.example.ProfileDemo -    Metrics Enabled: true

ERROR [Maven Profiles Demo] [production] c.example.ProfileDemo -
7. Profile Effects Demonstration:
ERROR [Maven Profiles Demo] [production] c.example.ProfileDemo -    PRODUCTION environment effects:
ERROR [Maven Profiles Demo] [production] c.example.ProfileDemo -      - Error level logging only
ERROR [Maven Profiles Demo] [production] c.example.ProfileDemo -      - Full optimization enabled
ERROR [Maven Profiles Demo] [production] c.example.ProfileDemo -      - Strict security policies
ERROR [Maven Profiles Demo] [production] c.example.ProfileDemo -      - Minimal timeouts
```

## 🎯 Key Features Demonstrated

### 1. Environment Configuration Management

Each environment has tailored settings:

| Environment | Database | Logging | Security | Features |
|-------------|----------|---------|----------|----------|
| Local | H2 in-memory | DEBUG | Relaxed | All enabled |
| Development | PostgreSQL | DEBUG | Basic | Metrics + Debug |
| Testing | PostgreSQL | INFO | Strict | Integration tests |
| Staging | PostgreSQL | WARN | Production-like | Monitoring |
| Production | PostgreSQL | ERROR | Maximum | Optimized |

### 2. Property Precedence and Inheritance

```
Command Line (-Dprop=value)     [Highest Priority]
    ↓
Profile Properties
    ↓
Project Properties (POM)
    ↓
Parent POM Properties           [Lowest Priority]
```

### 3. Conditional Dependencies

Different environments include different dependencies:

```xml
<!-- Only included in development/testing/staging/production profiles -->
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <scope>runtime</scope>
</dependency>

<!-- Only included when metrics profile is active -->
<dependency>
    <groupId>io.micrometer</groupId>
    <artifactId>micrometer-core</artifactId>
</dependency>
```

### 4. Build Plugin Configuration

Profiles can modify plugin behavior:

```xml
<!-- Security profile adds dependency scanning -->
<profile>
    <id>security</id>
    <build>
        <plugins>
            <plugin>
                <groupId>org.owasp</groupId>
                <artifactId>dependency-check-maven</artifactId>
                <executions>
                    <execution>
                        <goals><goal>check</goal></goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
</profile>
```

## 🧪 Testing with Profiles

### Environment-Specific Testing

```bash
# Test local configuration
mvn test

# Test development configuration
mvn test -Pdevelopment

# Test with integration tests enabled
mvn test -Ptesting,full

# Test production-like configuration
mvn test -Pstaging
```

### Profile Validation Tests

The test suite validates:
- ✅ Profile configuration object creation
- ✅ Property loading with filtering
- ✅ Environment-specific property files
- ✅ Feature flag handling
- ✅ Profile detection and activation
- ✅ Property precedence and overrides

## 🔍 Profile Information Commands

### List Available Profiles

```bash
# Show all profiles defined in POM
mvn help:all-profiles

# Show currently active profiles
mvn help:active-profiles

# Show active profiles for specific environment
mvn help:active-profiles -Pproduction
```

### Evaluate Profile Properties

```bash
# Check specific property value
mvn help:evaluate -Dexpression=environment -Pproduction -q -DforceStdout

# Show effective POM with active profiles
mvn help:effective-pom -Pstaging,security

# List all properties for a profile
mvn help:system -Pdevelopment
```

### Debug Profile Activation

```bash
# Verbose profile activation logging
mvn compile -Pproduction -X | grep -i profile

# Test profile activation conditions
mvn help:active-profiles -Dci=true  # Activates ci-build profile
```

## 🛠 Troubleshooting

### Common Profile Issues

**Profile not activating:**
```bash
# Check profile definition
mvn help:describe -Dplugin=help -Ddetail=true

# Verify activation conditions
mvn help:active-profiles -Pdebug -X
```

**Properties not being substituted:**
```bash
# Verify resource filtering is enabled
mvn help:effective-pom | grep -A 5 -B 5 filtering

# Check property resolution
mvn help:evaluate -Dexpression=database.host -Pproduction
```

**Conflicting profile properties:**
```bash
# Show effective properties after profile merging
mvn help:effective-pom -Plocal,debug

# Test specific property precedence
mvn help:evaluate -Dexpression=log.level -Pproduction -Ddebug.override=true
```

### Profile Validation

```bash
# Validate all environment profiles work
for profile in local development testing staging production; do
  echo "Testing profile: $profile"
  mvn compile -P$profile
done

# Test profile combinations
mvn compile -Pdevelopment,debug,metrics
mvn test -Ptesting,full,security
```

## 📚 Best Practices Demonstrated

1. **Environment Separation**: Clear separation of environment-specific configurations
2. **Default Activation**: Sensible defaults with local profile active by default
3. **Property Inheritance**: Logical property override hierarchy
4. **Feature Toggles**: Profile-based feature flag management
5. **Conditional Dependencies**: Include dependencies only when needed
6. **Build Optimization**: Different build configurations for different scenarios
7. **Activation Logic**: Smart profile activation based on various conditions
8. **Documentation**: Clear profile purpose and usage documentation

## 📖 Next Steps

After understanding this example:

1. Create custom profiles for your specific environments
2. Experiment with profile activation conditions
3. Set up CI/CD pipelines with appropriate profile usage
4. Move on to advanced examples: `01-multi-module`

## 🔗 Related Documentation

- [Maven Profiles Guide](https://maven.apache.org/guides/introduction/introduction-to-profiles.html)
- [Profile Activation](https://maven.apache.org/guides/introduction/introduction-to-profiles.html#profile-activation)
- [Settings Profiles](https://maven.apache.org/guides/introduction/introduction-to-profiles.html#profiles-in-settings-xml)
- [Build Portability](https://maven.apache.org/guides/introduction/introduction-to-profiles.html#build-portability)
