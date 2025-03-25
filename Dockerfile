# Use OpenJDK 21 as the base image
FROM openjdk:21-slim

# Set working directory
WORKDIR /app

# Copy the Gradle files first to leverage Docker cache
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle

# Copy the source code
COPY src ./src

# Build the application
RUN ./gradlew build -x test

# Expose the port the app runs on
EXPOSE 8080

# Run the Spring Boot application
CMD ["java", "-jar", "/app/build/libs/*.jar"] 