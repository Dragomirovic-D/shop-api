# Use an official OpenJDK runtime as a parent image
FROM maven:3.9.6-eclipse-temurin-17

# Set the working directory inside the container
WORKDIR /app

# Copy the built jar file into the container
COPY  . .
#target/shop-api-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the app runs on
EXPOSE 8080

# Run the jar file
CMD ["mvn", "spring-boot:run"]

