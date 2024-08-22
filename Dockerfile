FROM openjdk:8-jdk-alpine

EXPOSE 8080

VOLUME /tmp

ADD target/DemoProject-1.0-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]