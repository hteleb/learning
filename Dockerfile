FROM alpine:3

FROM openjdk:17-jdk-slim

EXPOSE 9000

COPY . /app

WORKDIR /app

CMD ["java", "-jar", "backendify"]

# This Dockerfile is provided for empty as you will need to have a different
# kind of file depending on the language you use to solve this challenge.
# 
# Feel free to change it as much as needed so it is your solution the one that
# launches.

ENTRYPOINT [ "/bin/echo", "implement", "me" ]
