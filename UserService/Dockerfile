FROM openjdk:8-jdk-alpine

EXPOSE 8081

VOLUME /tmp

ADD target/UserService-1.0-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]