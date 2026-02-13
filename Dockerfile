# ===== STAGE 1 - BUILD =====
FROM eclipse-temurin:17-jdk-jammy AS build

WORKDIR /build

COPY gradlew .
COPY gradle ./gradle
COPY build.gradle settings.gradle ./
COPY src ./src

RUN chmod +x gradlew
RUN ./gradlew clean build -x test

# ===== STAGE 2 - RUNTIME =====
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

COPY --from=build /build/build/libs/*jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
