FROM alpine:3

FROM openjdk:11-jdk-slim

VOLUME /tmp

FROM maven:latest

ENV APP_HOME=/tmp/

COPY pom.xml $APP_HOME
COPY src $APP_HOME
WORKDIR $APP_HOME


ENV JAR_FILE=target/backendify-1.0.jar

COPY ${JAR_FILE} /backendify.jar

EXPOSE 9000

ENTRYPOINT ["java", "-jar", "/backendify.jar"]


# This Dockerfile is provided for empty as you will need to have a different
# kind of file depending on the language you use to solve this challenge.
# 
# Feel free to change it as much as needed so it is your solution the one that
# launches.

#ENTRYPOINT [ "/bin/echo", "implement", "me" ]
