FROM openjdk:8-jdk-alpine

EXPOSE 8080

WORKDIR /app

COPY . .

RUN chmod +x ./mvnw
RUN ./mvnw package

ARG JAR_FILE=target/chains-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "target/chains-0.0.1-SNAPSHOT.jar"]