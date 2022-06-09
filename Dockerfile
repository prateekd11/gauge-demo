FROM openjdk:12-jdk-alpine

COPY app.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]