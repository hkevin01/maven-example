# Contributing to Maven Learning Tool

Thank you for your interest in contributing to the Maven Learning Tool! This document provides guidelines and information for contributors.

## 🎯 How to Contribute

### Types of Contributions

1. **Examples**: Add new Maven examples (simple or advanced)
2. **Documentation**: Improve existing docs or add new guides
3. **Bug Fixes**: Fix issues in existing examples
4. **Enhancements**: Improve existing examples or project structure
5. **Testing**: Add tests or improve test coverage

### Getting Started

1. **Fork the repository**
2. **Clone your fork**:
   ```bash
   git clone git@github.com:YOUR_USERNAME/maven-example.git
   cd maven-example
   ```
3. **Create a feature branch**:
   ```bash
   git checkout -b feature/your-feature-name
   ```

## 📝 Contribution Guidelines

### For New Examples

#### Simple Examples (`/simple-examples/`)
- Focus on one Maven concept per example
- Include comprehensive README.md
- Provide clear comments in code
- Follow the naming convention: `XX-concept-name/`
- Include working tests

#### Advanced Examples (`/advanced-examples/`)
- Demonstrate complex real-world scenarios
- Show integration between multiple concepts
- Include performance considerations
- Document best practices

### Example Structure Requirements

Each example must include:

```
example-name/
├── README.md           # Comprehensive documentation
├── pom.xml            # Well-commented POM file
├── src/
│   ├── main/java/     # Source code with comments
│   └── test/java/     # Unit tests
└── .gitignore         # If needed for specific artifacts
```

### Code Standards

#### Java Code
- Use Java 11+ features appropriately
- Follow standard Java naming conventions
- Include Javadoc for public methods
- Keep classes focused and cohesive
- Write comprehensive unit tests

#### Maven POM Files
- Use clear, descriptive names and descriptions
- Include comments explaining configuration choices
- Pin dependency versions (avoid version ranges)
- Use properties for version management
- Organize dependencies logically

#### Documentation
- Write clear, concise README files
- Include learning objectives
- Provide step-by-step instructions
- Add troubleshooting section
- Link to relevant resources

## 🧪 Testing Guidelines

### Requirements
- All examples must compile successfully
- Unit tests must pass
- Examples should run without external dependencies when possible
- Test coverage should be reasonable (aim for 70%+)

### Running Tests
```bash
# Test specific example
cd simple-examples/01-hello-maven
mvn clean test

# Test all examples
./scripts/test-all-examples.sh  # (if script exists)
```

## 📋 Pull Request Process

### Before Submitting
1. **Test your changes**: Ensure all examples compile and tests pass
2. **Update documentation**: Update relevant README files
3. **Check formatting**: Ensure consistent code formatting
4. **Verify structure**: Follow the established project structure

### Pull Request Guidelines
1. **Clear title**: Use descriptive title (e.g., "Add Spring Boot integration example")
2. **Detailed description**: Explain what the PR adds/changes and why
3. **Link issues**: Reference any related issues
4. **Small, focused changes**: Keep PRs focused on a single feature/fix
5. **Update CHANGELOG**: Add entry if applicable

### PR Template
```markdown
## Description
Brief description of changes

## Type of Change
- [ ] New example
- [ ] Documentation update
- [ ] Bug fix
- [ ] Enhancement
- [ ] Other (specify)

## Testing
- [ ] All tests pass locally
- [ ] New tests added (if applicable)
- [ ] Example compiles and runs successfully

## Checklist
- [ ] Code follows project style guidelines
- [ ] README.md updated (if applicable)
- [ ] Comments added to explain complex code
- [ ] No breaking changes (or clearly documented)
```

## 🎨 Style Guidelines

### Code Style
- **Indentation**: 4 spaces (no tabs)
- **Line length**: 120 characters max
- **Naming**: Use descriptive names
- **Comments**: Explain why, not what

### Markdown Style
- Use consistent heading levels
- Include code blocks with language specification
- Use bullet points for lists
- Include links to external resources

## 🏗 Project Structure Guidelines

### Directory Naming
- Use lowercase with hyphens: `multi-module-project`
- Number simple examples: `01-hello-maven`, `02-dependencies`
- Use descriptive names for advanced examples

### File Naming
- Use standard Maven conventions
- README files in UPPERCASE
- Java classes in PascalCase
- Package names in lowercase

## 🤝 Community Guidelines

### Code of Conduct
- Be respectful and inclusive
- Provide constructive feedback
- Help others learn and grow
- Follow the project's code of conduct

### Communication
- Use clear, professional language
- Be patient with newcomers
- Provide helpful feedback on PRs
- Ask questions if something is unclear

## 🐛 Reporting Issues

### Bug Reports
Include:
- Clear description of the issue
- Steps to reproduce
- Expected vs actual behavior
- Environment details (OS, Java version, Maven version)
- Error messages or logs

### Feature Requests
Include:
- Clear description of the proposed feature
- Use case or problem it solves
- Proposed implementation approach
- Any alternatives considered

## 📚 Resources

### Maven Documentation
- [Maven Official Documentation](https://maven.apache.org/guides/)
- [Maven Best Practices](https://maven.apache.org/guides/development/guide-development.html)
- [Maven Plugin Development](https://maven.apache.org/guides/plugin/guide-java-plugin-development.html)

### Development Tools
- **IDEs**: IntelliJ IDEA, Eclipse, VS Code
- **Build Tools**: Maven 3.6+
- **Testing**: JUnit 5, TestNG, Mockito
- **Documentation**: Markdown, PlantUML

## 🙏 Recognition

Contributors will be recognized in:
- CONTRIBUTORS.md file
- Release notes
- Project documentation

Thank you for contributing to the Maven Learning Tool! 🚀
