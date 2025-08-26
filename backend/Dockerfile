FROM eclipse-temurin:17-jre-alpine AS build

WORKDIR /app

COPY gradlew .
COPY build.gradle .
COPY settings.gradle .
COPY gradle gradle
COPY src src

RUN chmod +x ./gradlew
RUN ./gradlew bootJar

FROM eclipse-temurin:17-jre-alpine AS runtime

WORKDIR /app

EXPOSE 8080

COPY --from=build build/libs/*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]