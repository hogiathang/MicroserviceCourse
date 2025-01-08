FROM openjdk:21-jdk-slim

# Set maintainer of this image to hogiathang
LABEL maintainer="hogiathang"

COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "accounts-0.0.1-SNAPSHOT.jar"]

# Why use ENTRYPOINT instead of RUN?
# ENTRYPOINT is used to specify a command that will always be executed when the container starts.
# RUN is used to run a command when building a container image.
