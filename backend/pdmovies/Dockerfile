# Use the official Maven image to build the project
FROM maven:3.8.4-openjdk-17 as builder

# Set the working directory in the Maven container
WORKDIR /pdmovies

# Copy the pom.xml file and other Maven config files
COPY pom.xml .
# Optionally, copy the settings.xml if you have any custom Maven settings
# COPY settings.xml /root/.m2/

# Copy the application resource files (including application.yml)
COPY src/main/resources/ /pdmovies/src/main/resources/

# Copy the source code
COPY src /pdmovies/src

# Build the application and skip tests
RUN mvn clean install -DskipTests

# Use OpenJDK 17 to run the built application
FROM openjdk:17-alpine

# Set the deployment directory
WORKDIR /pdmovies

# Copy the built artifact from the builder stage
COPY --from=builder /pdmovies/target/*.jar pdmovies.jar

# Expose the port on which your app runs
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "pdmovies.jar"]
