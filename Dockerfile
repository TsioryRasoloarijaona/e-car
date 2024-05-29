FROM maven:3.8.5-openjdk-17 AS build
COPY .. .
RUN mvn clean package -Dskiptests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/e-commerce-back-0.0.1-SNAPSHOT.jar e-commerce-back.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","e-commerce-back.jar"]