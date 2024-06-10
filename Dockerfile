FROM jelastic/maven:3.9.5-openjdk-21 AS build
COPY .. .
RUN mvn clean package -Dskiptests

FROM openjdk:21-jdk
COPY --from=build /target/e-commerce-back-0.0.1-SNAPSHOT.jar e-commerce-back.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","e-commerce-back.jar"]