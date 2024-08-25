FROM openjdk:8-jdk-alpine

EXPOSE 8761

VOLUME /tmp

ADD target/DemoProjectEureka-1.0-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]