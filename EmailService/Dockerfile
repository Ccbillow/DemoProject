FROM openjdk:8-jdk-alpine

EXPOSE 8082

VOLUME /tmp

ADD target/EmailService-1.0-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]