# Use an official OpenJDK runtime as a parent image
FROM eclipse-temurin:21-jdk

# Set the working directory in the container
WORKDIR /app

# Copy the jar file from the target directory to the container
COPY target/video-store-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the port the application runs on
EXPOSE 3001

# Run the jar file
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
