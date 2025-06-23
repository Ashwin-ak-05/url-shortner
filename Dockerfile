## Use official Java 17 image
#FROM openjdk:17-jdk-slim
#
## Set app directory
#WORKDIR /app
#
## Copy jar file into image
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
#
## Expose port (Spring Boot default)
#EXPOSE 8080
#
## Run the app
#ENTRYPOINT ["java", "-jar", "/app/app.jar"]


# Stage 1: Build the application
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Create the runtime image
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

