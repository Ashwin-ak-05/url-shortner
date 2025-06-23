# Use official Java 17 image
FROM openjdk:17-jdk-slim

# Set app directory
WORKDIR /app

# Copy jar file into image
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Expose port (Spring Boot default)
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
