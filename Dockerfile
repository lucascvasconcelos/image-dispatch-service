FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY target/image-dispatch-service.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]