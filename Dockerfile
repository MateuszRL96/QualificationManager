# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the project files to the container
COPY . .

# Package the application using Maven
RUN ./mvnw package

# Expose the port the application runs on
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "target/qulificationRecomendation-0.0.1-SNAPSHOT.jar"]