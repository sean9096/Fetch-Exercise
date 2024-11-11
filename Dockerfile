FROM openjdk:21-jdk

# Copy the jar file into the docker image
COPY target/*.jar app.jar

# Run the Jar file
ENTRYPOINT [ "java", "-jar","/app.jar" ]