# Maven Learning Tool 🚀

A comprehensive Maven learning repository with examples ranging from simple to advanced use cases. This project serves as a practical guide for understanding Apache Maven build automation and project management.

## 📚 Table of Contents

- [Overview](#overview)
- [Prerequisites](#prerequisites)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [Examples](#examples)
- [Documentation](#documentation)
- [Contributing](#contributing)

## 🎯 Overview

This repository contains practical Maven examples designed to help developers learn Maven concepts progressively. From basic project setup to advanced multi-module builds, dependency management, and CI/CD integration.

## 🛠 Prerequisites

- Java 8 or higher
- Apache Maven 3.6+
- Git
- IDE of your choice (IntelliJ IDEA, Eclipse, VS Code)

## 📁 Project Structure

```
maven-example/
├── docs/                    # Project documentation
├── simple-examples/         # Basic Maven concepts
├── advanced-examples/       # Complex Maven scenarios
├── .github/                 # GitHub workflows and templates
├── .copilot/               # GitHub Copilot configuration
├── pom.xml                 # Main project POM
├── README.md               # This file
└── .gitignore             # Git ignore rules
```

## 🚀 Getting Started

1. **Clone the repository:**
   ```bash
   git clone git@github.com:hkevin01/maven-example.git
   cd maven-example
   ```

2. **Verify Maven installation:**
   ```bash
   mvn --version
   ```

3. **Build the project:**
   ```bash
   mvn clean compile
   ```

4. **Run tests:**
   ```bash
   mvn test
   ```

5. **Package the application:**
   ```bash
   mvn package
   ```

## 📖 Examples

### Simple Examples
- Basic project structure
- Dependency management
- Plugin configuration
- Testing with JUnit
- Resource handling

### Advanced Examples
- Multi-module projects
- Custom plugin development
- Profile management
- Integration testing
- Deployment strategies

## 📋 Documentation

Visit the [docs](./docs/) folder for detailed guides and tutorials.

### Key Documents
- [Getting Started Guide](./docs/getting-started.md) - Setup and quick start
- [Project Plan](./docs/project-plan.md) - Complete project roadmap and timeline
- [**Progress Tracking**](./docs/progress-tracking.md) - Comprehensive project progress analysis and status

## 🤝 Contributing

Contributions are welcome! Please read our contributing guidelines in the docs folder.

## 📜 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🏗 Current Project

This repository includes a JavaFX-based example application with:
- JSON processing capabilities
- Image I/O operations
- Logging with Log4j
- Unit testing with JUnit
- Assembly plugin for fat JAR creation