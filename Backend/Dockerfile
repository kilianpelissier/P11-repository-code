FROM openjdk:11-jre-slim
COPY ./api/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]