# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY . .

# Build the Spring Boot application
RUN ./gradlew build

# Run the application
CMD ["java", "-jar", "build/libs/premier-construction.jar"]
