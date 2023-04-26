FROM alpine:3

# Fetching latest version of Java
FROM openjdk:11-jdk-slim

# Fetching latest version of maven
FROM maven:latest


VOLUME /tmp

# Setting up work directory
ENV APP_HOME=/tmp
WORKDIR $APP_HOME

# Copy the jar file into our workdir
#ENV JAR_FILE=target/backendify-1.0.jar
#COPY ${JAR_FILE} /backendify.jar
COPY ./target/backendify-1.0.jar $WORKDIR


# Exposing port 9000
ENV port=9000
EXPOSE $port


# Starting the application
#CMD ["java", "-jar", "backendify-1.0.jar"]
ENTRYPOINT ["java", "-jar", "/tmp/backendify-1.0.jar"]


# This Dockerfile is provided for empty as you will need to have a different
# kind of file depending on the language you use to solve this challenge.
# 
# Feel free to change it as much as needed so it is your solution the one that
# launches.

#ENTRYPOINT [ "/bin/echo", "implement", "me" ]
