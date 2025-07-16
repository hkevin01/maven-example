# Getting Started with Maven Learning Tool

Welcome to the Maven Learning Tool! This guide will help you get started with Apache Maven using practical examples.

## 📋 Prerequisites

### Required Software
- **Java Development Kit (JDK)**: Version 11 or higher
- **Apache Maven**: Version 3.6 or higher
- **Git**: For cloning the repository
- **IDE** (recommended): IntelliJ IDEA, Eclipse, or VS Code

### Verification Commands
Before starting, verify your setup:

```bash
# Check Java version
java -version

# Check Maven version  
mvn --version

# Check Git version
git --version
```

Expected output examples:
```bash
$ java -version
openjdk version "11.0.12" 2021-07-20

$ mvn --version
Apache Maven 3.8.6
Maven home: /usr/share/maven
Java version: 11.0.12, vendor: Eclipse Adoptium
```

## 🚀 Quick Start

### 1. Clone the Repository
```bash
git clone git@github.com:hkevin01/maven-example.git
cd maven-example
```

### 2. Verify the Main Project
```bash
# Compile the main project
mvn clean compile

# Run tests
mvn test

# Create JAR package
mvn package
```

### 3. Try Your First Example
```bash
# Navigate to the first simple example
cd simple-examples/01-hello-maven

# Compile and test
mvn clean test

# Run the application
java -cp target/classes com.example.HelloMaven
```

## 📚 Learning Path

### For Beginners (New to Maven)
Start with these examples in order:

1. **01-hello-maven**: Basic project structure and lifecycle
2. **02-dependencies**: Dependency management fundamentals
3. **03-plugins**: Plugin configuration and usage
4. **04-testing**: Testing strategies with Maven
5. **05-resources**: Resource handling and filtering

### For Intermediate Users
After completing beginner examples:

1. **06-packaging**: Different packaging types
2. **07-properties**: Maven properties and profiles
3. **08-multi-module**: Multi-module project basics
4. **Advanced examples**: Complex real-world scenarios

### For Advanced Users
Focus on advanced examples:

1. **Multi-module projects**: Enterprise-level project structure
2. **Custom plugins**: Creating your own Maven plugins
3. **CI/CD integration**: Automated builds and deployments
4. **Performance optimization**: Large-scale build optimization

## 🏗 Project Structure Overview

```
maven-example/
├── docs/                    # Project documentation
│   ├── getting-started.md   # This file
│   ├── project-plan.md      # Project roadmap
│   └── best-practices.md    # Maven best practices
├── simple-examples/         # Basic Maven concepts
│   ├── 01-hello-maven/      # Basic project structure
│   ├── 02-dependencies/     # Dependency management
│   └── ...                  # More simple examples
├── advanced-examples/       # Complex scenarios
│   ├── 01-multi-module/     # Multi-module projects
│   └── ...                  # More advanced examples
├── .github/                 # GitHub workflows and templates
├── .copilot/               # GitHub Copilot configuration
├── pom.xml                 # Main project POM
├── README.md               # Project overview
└── .gitignore             # Git ignore rules
```

## 🎯 How to Use Each Example

### Step-by-Step Process
1. **Read the README**: Each example has detailed documentation
2. **Examine the POM**: Understand the configuration
3. **Study the code**: Look at the Java classes and tests
4. **Run the example**: Follow the provided commands
5. **Experiment**: Try modifying the code or configuration
6. **Check understanding**: Can you explain what each part does?

### Example Workflow
```bash
# Navigate to an example
cd simple-examples/02-dependencies

# Read the documentation
cat README.md

# Examine the project structure
tree . # or ls -la

# Look at the POM file
cat pom.xml

# Compile and test
mvn clean compile test

# Try running it
mvn exec:java -Dexec.mainClass="com.example.MainClass"
```

## 🔧 Common Commands

### Essential Maven Commands
```bash
# Clean build artifacts
mvn clean

# Compile source code
mvn compile

# Run tests
mvn test

# Package as JAR/WAR
mvn package

# Install to local repository
mvn install

# Full clean build cycle
mvn clean install

# Skip tests (use sparingly)
mvn clean install -DskipTests

# Run specific test class
mvn test -Dtest=TestClassName

# Debug information
mvn clean compile -X

# Offline mode (use local repository only)
mvn clean install -o
```

### Useful Plugin Goals
```bash
# Show dependency tree
mvn dependency:tree

# Analyze dependencies
mvn dependency:analyze

# Show effective POM
mvn help:effective-pom

# List available plugins
mvn help:describe -Dcmd

# Run specific plugin goal
mvn plugin-name:goal-name
```

## 🏃‍♂️ Running Examples

### Method 1: Using Maven Exec Plugin
```bash
# If configured in POM
mvn exec:java

# With main class specified
mvn exec:java -Dexec.mainClass="com.example.MainClass"

# With arguments
mvn exec:java -Dexec.mainClass="com.example.MainClass" -Dexec.args="arg1 arg2"
```

### Method 2: Direct Java Execution
```bash
# After mvn compile
java -cp target/classes com.example.MainClass

# With dependencies (after mvn package)
java -cp target/classes:target/lib/* com.example.MainClass
```

### Method 3: Executable JAR (if configured)
```bash
# After mvn package
java -jar target/example-name-1.0.0.jar
```

## 🐛 Troubleshooting

### Common Issues and Solutions

#### Maven Command Not Found
```bash
# Check if Maven is installed
which mvn

# If not found, install Maven:
# - Ubuntu/Debian: sudo apt install maven
# - macOS: brew install maven  
# - Windows: Download from https://maven.apache.org/
```

#### Java Version Issues
```bash
# Check Java version
java -version

# Set JAVA_HOME (example for Linux/macOS)
export JAVA_HOME=/path/to/your/java/installation

# Check Maven is using correct Java
mvn --version
```

#### Dependency Resolution Problems
```bash
# Clear local repository cache
rm -rf ~/.m2/repository

# Try offline mode
mvn clean install -o

# Force update dependencies
mvn clean install -U
```

#### Compilation Errors
```bash
# Check Java source/target versions in POM
# Ensure they match your Java installation

# Clean and recompile
mvn clean compile

# Check for IDE-specific issues
# Import project as Maven project in your IDE
```

#### Test Failures
```bash
# Run tests with more details
mvn test -Dtest=FailingTestClass

# Skip tests temporarily
mvn clean install -DskipTests

# Run tests in debug mode
mvn test -Dmaven.surefire.debug
```

## 📖 Learning Resources

### Official Documentation
- [Maven Getting Started Guide](https://maven.apache.org/guides/getting-started/)
- [Maven Users Centre](https://maven.apache.org/users/index.html)
- [Maven Plugin Documentation](https://maven.apache.org/plugins/)

### Recommended Reading Order
1. [Maven in 5 Minutes](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html)
2. [Standard Directory Layout](https://maven.apache.org/guides/introduction/introduction-to-the-standard-directory-layout.html)
3. [POM Reference](https://maven.apache.org/pom.html)
4. [Dependency Mechanism](https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html)

### Video Tutorials
- Search for "Maven Tutorial" on your preferred learning platform
- Look for recent tutorials (Maven 3.6+)
- Focus on practical examples rather than theory-only content

## 🎓 Next Steps

After completing the getting started guide:

1. **Work through simple examples**: Complete all examples in order
2. **Try modifications**: Change dependencies, add features, experiment
3. **Read best practices**: Review the best practices documentation
4. **Tackle advanced examples**: Move to complex scenarios
5. **Contribute**: Consider adding your own examples or improvements

## 💬 Getting Help

### Community Resources
- **Stack Overflow**: Tag your questions with `maven`
- **Maven Users Mailing List**: [users@maven.apache.org](mailto:users@maven.apache.org)
- **GitHub Issues**: For problems specific to this learning tool

### Asking Good Questions
1. Include your environment details (OS, Java version, Maven version)
2. Provide the complete error message
3. Share relevant parts of your POM file
4. Describe what you were trying to achieve
5. Mention what you've already tried

Happy learning with Maven! 🚀
