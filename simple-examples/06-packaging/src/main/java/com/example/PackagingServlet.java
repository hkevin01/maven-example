package com.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Simple servlet demonstration for WAR packaging
 * This servlet shows how the same application can be packaged as a WAR
 * and deployed to a servlet container like Tomcat
 */
@WebServlet(name = "PackagingServlet", urlPatterns = {"/packaging", "/demo"})
public class PackagingServlet extends HttpServlet {
    
    private PackagingDemo packagingDemo;
    
    @Override
    public void init() throws ServletException {
        super.init();
        this.packagingDemo = new PackagingDemo();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("    <title>Maven Packaging Demo - Web Interface</title>");
            out.println("    <style>");
            out.println("        body { font-family: Arial, sans-serif; margin: 40px; }");
            out.println("        .section { margin: 20px 0; padding: 15px; border: 1px solid #ddd; }");
            out.println("        .success { color: green; }");
            out.println("        .info { color: blue; }");
            out.println("        pre { background: #f5f5f5; padding: 10px; }");
            out.println("    </style>");
            out.println("</head>");
            out.println("<body>");
            
            out.println("<h1>Maven Packaging Demonstration - Web Interface</h1>");
            
            out.println("<div class='section'>");
            out.println("<h2>Application Information</h2>");
            out.println("<p><strong>Packaging Type:</strong> <span class='info'>" + 
                       packagingDemo.getPackagingType() + "</span></p>");
            out.println("<p><strong>Execution Mode:</strong> " + packagingDemo.getExecutionMode() + "</p>");
            out.println("<p><strong>Web Context:</strong> <span class='success'>✓ Running in Servlet Container</span></p>");
            out.println("<p><strong>JAR Location:</strong> " + packagingDemo.getJarLocation() + "</p>");
            out.println("</div>");
            
            out.println("<div class='section'>");
            out.println("<h2>Runtime Environment</h2>");
            out.println("<p><strong>Java Version:</strong> " + System.getProperty("java.version") + "</p>");
            out.println("<p><strong>Servlet Container:</strong> " + getServletContext().getServerInfo() + "</p>");
            out.println("<p><strong>Context Path:</strong> " + request.getContextPath() + "</p>");
            out.println("<p><strong>Servlet Path:</strong> " + request.getServletPath() + "</p>");
            out.println("</div>");
            
            out.println("<div class='section'>");
            out.println("<h2>Packaging Comparison</h2>");
            out.println("<ul>");
            out.println("<li><strong>JAR:</strong> Standalone application, run with 'java -jar'</li>");
            out.println("<li><strong>WAR:</strong> Web application, deployed to servlet container</li>");
            out.println("<li><strong>Fat JAR:</strong> All dependencies included, single file deployment</li>");
            out.println("<li><strong>Executable JAR:</strong> Self-contained with embedded server</li>");
            out.println("</ul>");
            out.println("</div>");
            
            out.println("<div class='section'>");
            out.println("<h2>JSON Processing Test</h2>");
            String testJson = "{\"message\":\"Hello from WAR deployment!\",\"timestamp\":\"" + 
                             new java.util.Date() + "\"}";
            String processedJson = packagingDemo.processJson(testJson);
            out.println("<p><strong>Input JSON:</strong></p>");
            out.println("<pre>" + testJson + "</pre>");
            out.println("<p><strong>Processed JSON:</strong></p>");
            out.println("<pre>" + processedJson + "</pre>");
            out.println("</div>");
            
            out.println("<div class='section'>");
            out.println("<h2>Maven Profiles</h2>");
            out.println("<p>This application can be built with different Maven profiles:</p>");
            out.println("<ul>");
            out.println("<li><code>mvn clean package</code> - Standard JAR</li>");
            out.println("<li><code>mvn clean package -Pwar</code> - WAR file</li>");
            out.println("<li><code>mvn clean package -Pfat-jar</code> - Fat JAR</li>");
            out.println("<li><code>mvn clean package -Pexecutable</code> - Executable JAR</li>");
            out.println("</ul>");
            out.println("</div>");
            
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}
